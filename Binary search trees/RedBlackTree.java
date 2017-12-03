package cs6301.g29;


/**
* Modified by Nishant Shekhar (nxs167130)
*            Anusha Agasthi  (nxa162430)
*            Prince Patel    (pap160930)
*/

public class RedBlackTree<T extends Comparable<? super T>> extends BST<T> {


    static class Entry<T> extends BST.Entry<T> {
        boolean isRed;
        Entry(T x, Entry<T> left, Entry<T> right) {
            super(x, left, right);
            isRed = true;
        }


        public boolean isLeaf(){
             return  this.left.element == null && this.right.element == null;
        }
    }



    RedBlackTree() {
	super();
    }

    @Override
    public boolean add(T x){

        Entry<T> newChild = null;

        if(root.element == null){
            root = new Entry<>(x, new Entry<>(null,null,null), new Entry<>(null,null,null));
            ((Entry<T>)root).isRed = false;
            size = 1;
            repair((Entry<T>)root);
            return true;
        }

        Entry<T> t = (Entry<T>)find(x);
        if(x.equals(t.element)){
            t.element = x; // replace if already exists
            return false;
        }
        else if( x.compareTo(t.element) < 0){
            t.left = new Entry<>(x, new Entry<>(null,null,null), new Entry<>(null,null,null));
            
            newChild = (Entry<T>)t.left;
        }
        else{
            t.right = new Entry<>(x, new Entry<>(null,null,null), new Entry<>(null,null,null));
           
            newChild = (Entry<T>) t.right;
        }
        if (t.isRed && newChild.isRed) {
        repair(newChild);
        }

        size++;
        return true;
    }


    @Override
    public T remove(T x) {
        if ( root.element == null)  return null;

        Entry<T> t = (Entry<T>)find(x);

        if(!t.element.equals(x)) return null;

        T result = t.element;

        Entry<T> bypassedNode;

        if(t.left.element == null || t.right.element == null) {
            bypass(t);
            bypassedNode = t;

        }
        else{//t has 2 children
            stack.push(t);
            Entry<T> minRight = (Entry<T>)find(t.right , t.element);
            t.element = minRight.element;
            bypass(minRight);
            bypassedNode = minRight;

        }

        if(bypassedNode.isRed){
            size--;
            return result;
        }

        if(bypassedNode.isLeaf()){
            bypassedNode.isRed = false;
            size--;
            return result;
        }

        Entry<T> c =  bypassedNode.left.element == null ? (Entry<T>)bypassedNode.right : (Entry<T>)bypassedNode.left;
        fix(c);
        size--;
        return result;
    }

    /**
     * t      Current Node (red)
     * p_t    parent of node t
     * g_t    parent of p_t, grandparent of t
     * u_t    sibling of p_t, uncle of t
     * @param t Node which needs to be repaired
     */
    private void repair(Entry<T> t){
        if(t == root) return;
        Entry<T> p_t = (Entry<T>)stack.pop();


        if(p_t.element ==  null){
            t.isRed = false;
            return;
        }

        if(p_t == root)
            return;

        if(p_t.isRed == false) return;

        //Grand Parent of t
        Entry<T> g_t = getParent(p_t);
        Entry<T> u_t = getUncle(g_t, p_t);

            while (t.isRed) {

                if (t == root || p_t == root || p_t.isRed == false) return;

                if (u_t.isRed){
                    p_t.isRed = false;
                    u_t.isRed = false;
                    g_t.isRed = true;
                    t = g_t;
                    continue;
                }

                if(!u_t.isRed && p_t.isRed){
                    p_t.isRed = false;
                    g_t.isRed = true;
                        if(t == p_t.left && p_t ==g_t.left) {
                            rotateRight(g_t);
                            return;
                        }

                        else if (t == p_t.right  && p_t == g_t.right) {
                            rotateLeft(g_t);
                            return;
                        }
                    }

                if(!u_t.isRed  && p_t.isRed){
                    if(t == p_t.right && p_t == g_t.left){
                        rotateLeft(p_t);
                        rotateRight(g_t);
                        return;
                    }
                    else if (t == p_t.left && p_t == g_t.right){
                         rotateRight(p_t);
                         rotateLeft(g_t);
                         return;
                    }

                }
            }

    }

    /**
     * s_t sibling of t
     * @param t
     */

    private void  fix(Entry<T> t){

        while ( t != root){

            if(t.isRed) {
                t.isRed = false;
                return;
            }

            Entry<T> s_t = getSibling(t);
            if(!s_t.isRed && !((Entry<T>)s_t.left).isRed && !((Entry<T>)s_t.right).isRed){
                s_t.isRed = true;
                t = (Entry<T>)stack.pop();
                continue;
            }


            caseThree(t,s_t);

            if(!s_t.isRed){

                if(t == stack.pop().left && ((Entry<T>) s_t.left).isRed  && !((Entry<T>)s_t.right).isRed){
                    rotateRight(s_t);
                    exchangeColors (s_t,(Entry<T>) s_t.left);
                    caseThree(t,s_t);
                    //apply case 3 and return

                }
                else if(t == stack.pop().right && !((Entry<T>) s_t.left).isRed  && ((Entry<T>)s_t.right).isRed){
                    rotateLeft(s_t);
                    exchangeColors (s_t,(Entry<T>) s_t.left);
                    caseThree(t,s_t);

                }
                //apply case 3 and return
                
                return ;

            }



            if(s_t.isRed){
                if(t == stack.peek().left){
                    rotateLeft((Entry<T>)stack.peek());
                    exchangeColors((Entry<T>)stack.pop(),s_t);
                    //apply one of the cases from 1  to 4;
                    return;
                }
                return;
            }

        }
       return;
    }

private void caseThree(Entry<T> t,Entry<T> s_t){
	if(!s_t.isRed){

        if(t == stack.peek().left && !((Entry<T>) s_t.left).isRed  && ((Entry<T>)s_t.right).isRed){
            rotateRight((Entry<T>)stack.peek());
            exchangeColors ((Entry<T>)stack.pop(),s_t);
            ((Entry<T>)s_t.right).isRed = false;
        }
        else if(t == stack.peek().right && ((Entry<T>) s_t.left).isRed  && !((Entry<T>)s_t.right).isRed){
            rotateRight((Entry<T>)stack.peek());
            exchangeColors ((Entry<T>)stack.pop(),s_t);
            ((Entry<T>)s_t.left).isRed = false;
        }

        return;
    }
	
}

    private Entry<T> rotateLeft(Entry<T> a) {

        Entry<T> b = (Entry<T>)a.right;

        a.right = b.left;

        b.left = a;

        if (stack.peek().element != null) {
            if (stack.peek().right == a) {
                stack.peek().right = b;
            } else {
                stack.peek().left = b;
            }
        }

        return b;
    }

    private Entry<T> rotateRight(Entry<T> a) {

        Entry<T> b = (Entry<T>)a.left;

        a.left = b.right;

        b.right = a;

        if (stack.peek().element != null) {
            if (stack.peek().right == a) {
                stack.peek().right = b;
            } else {
                stack.peek().left = b;
            }
        }

        return b;
    }

    private Entry<T> getParent(Entry<T> p_t){

        if(p_t.element == null || stack.peek().element == null ) return  new Entry<>(null, null,null);
        return (Entry<T>)stack.pop();
    }


    private Entry<T> getUncle(Entry<T> g_t , Entry<T> p_t) {
        if (g_t.element == null) return new Entry<>(null, null,null);
        if (g_t.left.element != null && g_t.left == p_t) {
            return (Entry<T>) g_t.left;
        } else if (g_t.right != null && g_t.right == p_t) {
            return (Entry<T>) g_t.left;
        }
        return new Entry<>(null, null,null);
    }

    private Entry<T> getSibling(Entry<T> t) {
        Entry<T> p_t = (Entry<T>)stack.peek();

        if (p_t.element == null)
            return new Entry<>(null, null,null);
        if ( p_t.left == t ) {
            return  (Entry<T>)p_t.left;
        } else if (p_t.right == t) {
            return  (Entry<T>)p_t.left;
        }
        return new Entry<>(null, null,null);
    }


    private void exchangeColors(Entry<T> a, Entry<T> b){
        boolean temp = a.isRed;
        b.isRed = a.isRed;
        a.isRed = temp;
    }
    
 
    @Override
    public void printTree() {
		System.out.print("[" + size + "]");
		printTree((Entry<T>)root);
		System.out.println();
	}
    
    // Inorder traversal of tree
	void printTree(Entry<T> node) {
		if (node.element != null) {
			printTree((Entry<T>)node.left);
			System.out.print(" " + node.element+" color "+node.isRed);
			printTree((Entry<T>)node.right);
		}
	}

}


