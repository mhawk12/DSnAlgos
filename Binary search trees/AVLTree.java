package cs6301.g29;

/**
 * Modified by Nishant Shekhar (nxs167130) Anusha Agasthi (nxa162430) Prince
 * Patel (pap160930)
 */

public class AVLTree<T extends Comparable<? super T>> extends BST<T> {
	static class Entry<T> extends BST.Entry<T> {
		int height;
		int balance;

		Entry(T x, Entry<T> left, Entry<T> right) {
			super(x, left, right);
			height = 0;
		}
	}

	AVLTree() {
		super();
	}

	@Override
	public boolean add(T x) {
		if (root.element == null) {
			root = new Entry<>(x, new Entry<>(null, null, null), new Entry<>(null, null, null));
			size = 1;
			return true;
		}

		Entry<T> t = (Entry<T>) find(x);

		if (x.equals(t.element)) {
			t.element = x; // replace if already exists
			return false;
		} else if (x.compareTo(t.element) < 0) {
			t.left = new Entry<>(x, new Entry<>(null, null, null), new Entry<>(null, null, null));
		} else
			t.right = new Entry<>(x, new Entry<>(null, null, null), new Entry<>(null, null, null));
		rebalance(t);

		size++;
		return true;
	}

	@Override
	public T remove(T x) {
		if (root.element == null)
			return null;

		Entry<T> t = (Entry<T>) find(x);

		if (!t.element.equals(x))
			return null;

		T result = t.element;

		if (t.left.element == null || t.right.element == null) {
			bypass(t);
			rebalance((Entry<T>) stack.pop());
		}

		else {// t has 2 children
			stack.push(t);
			Entry<T> minRight = (Entry<T>) find(t.right, t.element);
			t.element = minRight.element;

			bypass(minRight);
			rebalance((Entry<T>) stack.pop());

		}

		size--;
		return result;
	}

	private void rebalance(Entry<T> t) {
		setBalance(t);
		if (t.balance == -2) {
			if (height((Entry<T>) t.left.left) >= height((Entry<T>) t.left.right))
				t = rotateRight(t);
			else
				t = rotateLeftThenRight(t);

		} else if (t.balance == 2) {
			if (height((Entry<T>) t.right.right) >= height((Entry<T>) t.right.left))
				t = rotateLeft(t);
			else
				t = rotateRightThenLeft(t);
		}

		// rebalance starting from parent node of the current node till the root of the
		// tree
		if (stack.peek().element != null) {
			rebalance((Entry<T>) stack.pop());
		} else {
			root = t;
		}
	}

	private int height(Entry<T> t) {
		if (t.element == null)
			return -1;
		return t.height;
	}

	private void setBalance(Entry<T>... nodes) {
		for (Entry<T> t : nodes) {
			reheight(t);
			t.balance = height((Entry<T>) t.right) - height((Entry<T>) t.left);
		}
	}

	private void reheight(Entry<T> node) {

		if (node.element != null) {
			node.height = 1 + Math.max(height((Entry<T>) node.left), height((Entry<T>) node.right));

		}

	}

	// for zig zag rebalance
	private Entry<T> rotateLeftThenRight(Entry<T> t) {
		t.left = rotateLeft((Entry<T>) t.left);
		return rotateRight(t);
	}

	// for zig zag rebalance
	private Entry<T> rotateRightThenLeft(Entry<T> t) {
		t.right = rotateRight((Entry<T>) t.right);
		return rotateLeft(t);
	}

	private Entry<T> rotateLeft(Entry<T> a) {

		Entry<T> b = (Entry<T>) a.right;

		a.right = b.left;

		b.left = a;

		if (stack.peek().element != null) {
			if (stack.peek().right == a) {
				stack.peek().right = b;
			} else {
				stack.peek().left = b;
			}
		}

		setBalance(a, b);

		return b;
	}

	// print the balance at each node
	public void printBalance() {
		printBalance((Entry<T>) root);
	}

	public void printBalance(Entry<T> n) {
		if (n.element != null) {
			printBalance((Entry<T>) n.left);
			System.out.printf("%s ", n.balance);
			printBalance((Entry<T>) n.right);
		}
	}

	private Entry<T> rotateRight(Entry<T> a) {

		Entry<T> b = (Entry<T>) a.left;

		a.left = b.right;

		b.right = a;

		if (stack.peek().element != null) {
			if (stack.peek().right == a) {
				stack.peek().right = b;
			} else {
				stack.peek().left = b;
			}
		}

		setBalance(a, b);
		return b;
	}

}
