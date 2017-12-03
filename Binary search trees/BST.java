package cs6301.g29;

/**
* Modified by Nishant Shekhar (nxs167130)
*            Anusha Agasthi  (nxa162430)
*            Prince Patel    (pap160930)
*/

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class BST<T extends Comparable<? super T>> implements Iterable<T> {
	static class Entry<T> {
		T element;
		Entry<T> left, right;

		Entry(T x, Entry<T> left, Entry<T> right) {
			this.element = x;
			this.left = left;
			this.right = right;
		}
	}

	Entry<T> root;
	int size;
	Stack<Entry<T>> stack;

	public BST() {
		root = new Entry<>(null, null, null);
		size = 0;
	}

	LinkedList<T> inOrder() {
		LinkedList<T> entryLinkedList = new LinkedList<>();
		morrisInorderTraversal(entryLinkedList);
		return entryLinkedList;
	}

	// Iterative InOrder traversal algorithm using Morris algorithm
	public void morrisInorderTraversal(LinkedList<T> entryLinkedList) {
		Entry<T> cur = root;
		while (cur.element != null) {
			if (cur.left.element == null) {
				entryLinkedList.add(cur.element);
				cur = cur.right;
			} else {
				Entry<T> pre = cur.left;
				while (pre.right.element != null & pre.right != cur) {
					pre = pre.right;
				}
				if (pre.right == cur) {
					pre.right = new Entry<>(null, null, null);
					entryLinkedList.add(cur.element);
					cur = cur.right;
				} else {
					pre.right = cur;
					cur = cur.left;
				}
			}

		}
	}

	/**
	 * To find if x is contained in the tree or not
	 */

	Entry<T> find(T x) {
		// class object stack for stack of ancestors
		stack = new Stack<>();
		stack.push(new Entry<>(null, null, null));
		return find(root, x);
	}

	Entry<T> find(Entry<T> t, T x) {
		// LI : stack.peek() is the parent of node t
		if (t.element == null || t.element.equals(x))
			return t;

		while (true) {
			// if the element is smaller than current element , traverse left elements

			if (x.compareTo(t.element) < 0) {
				if (t.left.element == null)
					break;
				else {
					stack.push(t);
					t = t.left;
				}
			} else if (x.equals(t.element))
				break;

			else { // x > t.element
				if (t.right.element == null)
					break;
				else {
					stack.push(t);
					t = t.right;
				}

			}
		}

		return t;
	}

	public boolean contains(T x) {
		Entry<T> t = find(x);
		return t != null && t.element.equals(x);
	}

	/**
	 * TO DO: Is there an element that is equal to x in the tree? Element in tree
	 * that is equal to x is returned, null otherwise.
	 */
	public T get(T x) {
		Entry<T> t = find(x);
		if (t.element != null && t.element.equals(x))
			return t.element;
		else
			return null;
	}

	/**
	 * TO DO: Add x to tree. If tree contains a node with same key, replace element
	 * by x. Returns true if x is a new element added to tree.
	 */
	public boolean add(T x) {
		if (root.element == null) {
			root = new Entry<>(x, new Entry<>(null, null, null), new Entry<>(null, null, null));
			size = 1;
			return true;
		}

		Entry<T> t = find(x);

		if (x.equals(t.element)) {
			t.element = x; // replace if already exists
			return false;
		} else if (x.compareTo(t.element) < 0) {
			t.left = new Entry<>(x, new Entry<>(null, null, null), new Entry<>(null, null, null));
		} else
			t.right = new Entry<>(x, new Entry<>(null, null, null), new Entry<>(null, null, null));

		size++;
		return true;
	}

	/**
	 * Return x if found, otherwise return null
	 */
	public T remove(T x) {
		if (root.element == null)
			return null;

		Entry<T> t = find(x);

		if (!t.element.equals(x))
			return null;

		T result = t.element;

		if (t.left.element == null || t.right.element == null)
			bypass(t);

		else {// t has 2 children
			stack.push(t);
			Entry<T> minRight = find(t.right, t.element);
			t.element = minRight.element;
			bypass(minRight);
		}

		size--;
		return result;
	}

	void bypass(Entry<T> t) {// called when t has at most one child
		Entry<T> pt = stack.peek();
		Entry<T> c = t.left.element == null ? t.right : t.left;
		if (pt.element == null) // t is root
			root = c;
		else if (pt.left.equals(t))
			pt.left = c;
		else
			pt.right = c;
	}

	/**
	 * TO DO: Iterate elements in sorted order of keys
	 */
	public Iterator<T> iterator() {
		return inOrder().iterator();
	}

	public static void main(String[] args) {
		BST<Integer> t = new BST<>();
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			int x = in.nextInt();
			if (x > 0) {
				System.out.print("Add " + x + " : ");
				t.add(x);
				t.printTree();
			} else if (x < 0) {
				System.out.print("Remove " + x + " : ");
				t.remove(-x);
				t.printTree();
			} else {
				Comparable[] arr = t.toArray();
				System.out.print("Final: ");
				for (int i = 0; i < t.size; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
				return;
			}
		}
		in.close();
	}

	// TODO: Create an array with the elements using in-order traversal of tree
	public Comparable[] toArray() {
		Comparable[] arr = new Comparable[size];
		Iterator<T> elementsIterator = iterator();
		for (int i = 0; i < size; i++)
			arr[i] = elementsIterator.next();
		return arr;
	}

	public void printTree() {
		System.out.print("[" + size + "]");
		printTree(root);
		System.out.println();
	}

	// Inorder traversal of tree
	void printTree(Entry<T> node) {
		if (node.element != null) {
			printTree(node.left);
			System.out.print(" " + node.element);
			printTree(node.right);
		}
	}

}
/*
 * Sample input: 1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0
 * 
 * Output:Add 1 : [1] 1
Add 3 : [2] 1 3
Add 5 : [3] 1 3 5
Add 7 : [4] 1 3 5 7
Add 9 : [5] 1 3 5 7 9
Add 2 : [6] 1 2 3 5 7 9
Add 4 : [7] 1 2 3 4 5 7 9
Add 6 : [8] 1 2 3 4 5 6 7 9
Add 8 : [9] 1 2 3 4 5 6 7 8 9
Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
Remove -3 : [9] 1 2 4 5 6 7 8 9 10
Remove -6 : [8] 1 2 4 5 7 8 9 10
Remove -3 : [8] 1 2 4 5 7 8 9 10
Final: 1 2 4 5 7 8 9 10 
 * 
 */
