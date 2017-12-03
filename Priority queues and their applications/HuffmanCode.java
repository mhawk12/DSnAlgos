/**
 * Created by 
 * Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */
package cs6301.g29;

import java.util.PriorityQueue;

abstract class HuffmanTree implements Comparable<HuffmanTree> {
	//The frequency of this tree 
	public final double frequency;
	public HuffmanTree(double freq) {
		frequency = freq;
	}
	
	//compares  on the frequency
	public int compareTo(HuffmanTree tree) {
//		return frequency - tree.frequency;
		if(this.frequency < tree.frequency)
			return -1;
		else if (tree.frequency < this.frequency)
			return 1; 
		else
			return 0;
	}
}


class HuffmanLeaf extends HuffmanTree{
	public final char value;//the character of this Leaf represents
	
	public HuffmanLeaf(double freq, char val) {
		super(freq);
		value = val;
	}
}


class HuffmanNode extends HuffmanTree{
	public final HuffmanTree left, right; //subtrees
	
	public HuffmanNode(HuffmanTree l, HuffmanTree r) {
	super(l.frequency + r.frequency);	
	left = l;
	right = r;
	}
}


public class HuffmanCode{
	//input  is an array of frequencies , indexed by character code
	public static HuffmanTree buildTree(double[] charFreqs, char[] characters) {
		
		PriorityQueue<HuffmanTree> trees  = new PriorityQueue<HuffmanTree>();
		/**
		 * initially , we  have a forest of leaves
		 * one for each non-empty character
		 */
		
		for(int  i = 0 ; i < charFreqs.length; i++) {
			 if(charFreqs[i] > 0)
				 trees.offer(new HuffmanLeaf(charFreqs[i], characters[i]));
		}
		
		//loop until there is only one tree
		while(trees.size() > 1) {
			
			HuffmanTree a = trees.poll();
			HuffmanTree b = trees.poll();
			
			//put into new node and re-insert into queue
			trees.offer(new HuffmanNode(a, b));
		}
		
		
		return trees.poll();
		
	}
	
	
	
	
	
	public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
		assert tree != null;
		
		if(tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf) tree;
			
			
			//print out character , frequency, add code for this Leaf (which is just the prefix)
			System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);
		}
		
		else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode)tree;
			
			
			
			//traverse left
		 prefix.append('0');
		 printCodes(node.left, prefix);
		 prefix.deleteCharAt(prefix.length() - 1);
		 
		 
			//traverse right
		 prefix.append('1');
		 printCodes(node.right , prefix);
		 prefix.deleteCharAt(prefix.length() - 1);
				
		}
		

	}
	
	
	public static void main(String[] args) {
 
        // build tree
		char characters[] = { 'a', 'b', 'c', 'd', 'e'};
	    double charFreqs[] = { .2, .1, .15, .3, .25};
		
        HuffmanTree tree = buildTree(charFreqs, characters);
 
        // print out results
        System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
        printCodes(tree, new StringBuffer());
    }
}