/** @author
 *  Binary search tree (starter code)
 **/

package cs6301.g29;

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

	// Constructor
	public BST() {
		root = null;
		size = 0;
	}

	/**
	 * InOrder traversal of the BST InOrder traversal of the BST . This will get the
	 * elements in sorted order (ascending order).
	 * 
	 * @return List of all the elements in sorted order
	 */
	LinkedList<T> inOrder() {
		LinkedList<T> entryLinkedList = new LinkedList<>();
		morrisInorderTraversal(entryLinkedList);
		return entryLinkedList;
	}

	/**
	 * Iterative InOrder traversal of the BST , without recursion or stack
	 * 
	 * @param entryLinkedList
	 *            LinkedList having elements of the tree in sorted order
	 */
	public void morrisInorderTraversal(LinkedList<T> entryLinkedList) {
		Entry<T> cur = root;
		int counter = 0;
		while (cur != null) {
			if (cur.left == null) {
				entryLinkedList.add(cur.element);
				cur = cur.right;
			} else {
				Entry<T> pre = cur.left;
				while (pre.right != null & pre.right != cur) {
					pre = pre.right;
				}
				if (pre.right == cur) {
					pre.right = null;
					entryLinkedList.add(cur.element);
					cur = cur.right;
				} else {
					pre.right = cur;
					cur = cur.left;
				}
			}

		}
	}

	Entry<T> find(T x) {
		// class object stack for stack of ancestors
		stack = new Stack<>();
		stack.push(null);
		return find(root, x);
	}

	Entry<T> find(Entry<T> t, T x) {
		// LI : stack.peek() is the parent of node t
		if (t == null || t.element.equals(x))
			return t;

		while (true) {
			// if the element is smaller than current
			// element , traverse left elements
			if (x.compareTo(t.element) < 0) {
				if (t.left == null)
					break;
				else {
					stack.push(t);
					t = t.left;
				}
			} else if (x.equals(t.element))
				break;

			else { // x > t.element
				if (t.right == null)
					break;
				else {
					stack.push(t);
					t = t.right;
				}

			}
		}

		return t;
	}

	/**
	 * returns x if Entry with element x is there in tree.
	 */
	public boolean contains(T x) {
		Entry<T> t = find(x);
		return t != null && t.element.equals(x);
	}

	/**
	 * Finds Element in tree that is equal to x is returned, null otherwise.
	 */
	public T get(T x) {
		Entry<T> t = find(x);
		if (t != null && t.element.equals(x))
			return t.element;
		else
			return null;
	}

	/**
	 * Adds x to tree. If tree contains a node with same key, replace element by x.
	 * Returns true if x is a new element added to tree.
	 */
	public boolean add(T x) {
		if (root == null) {
			root = new Entry<>(x, null, null);
			size = 1;
			return true;
		}

		Entry<T> t = find(x);

		if (x.equals(t.element)) {
			t.element = x; // replace if already exists
			return false;
		} else if (x.compareTo(t.element) < 0) {
			t.left = new Entry<>(x, null, null);
		} else
			t.right = new Entry<>(x, null, null);

		size++;
		return true;
	}

	/**
	 * Removes x from tree. Return x if found, otherwise return null
	 */
	public T remove(T x) {
		if (root == null)
			return null;

		Entry<T> t = find(x);

		if (!t.element.equals(x))
			return null;

		T result = t.element;

		if (t.left == null || t.right == null)
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
		Entry<T> c = t.left == null ? t.right : t.left;
		if (pt == null) // t is root
			root = c;
		else if (pt.left.equals(t))
			pt.left = c;
		else
			pt.right = c;
	}

	/**
	 * Iterates elements in sorted order of keys
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

		
	}

	// Creates an array with the elements using in-order traversal of tree
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

	// InOrder traversal of tree
	void printTree(Entry<T> node) {
		if (node != null) {
			printTree(node.left);
			System.out.print(" " + node.element);
			printTree(node.right);
		}
	}

}

/*
 * Sample input: 1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0
 * 
 * Output: A1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0
Add 1 : [1] 1
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
