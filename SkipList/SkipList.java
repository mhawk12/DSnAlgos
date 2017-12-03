/**
 * Created by
 * Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */

package cs6301.g29;

import java.util.Iterator;
import java.util.Random;

public class SkipList<T extends Comparable<? super T>> {
	private static final int maxLevel = 32;
	int size;
	Entry<T> head;
	Entry<T> tail;

	public static class Entry<T> {
		T element;
		Entry<T>[] next;
		int[] length;

		public Entry(T element, int levels) {
			this.element = element;
			this.length = new int[levels + 1];
			this.next = new Entry[levels + 1];
			for (int i = 0; i <= levels; i++)
				this.next[i] = null;
		}
	}

	// Constructor
	public SkipList() {
		this.head = new Entry<>(null, maxLevel);
		// extra
		this.tail = new Entry<>(null, maxLevel);
		for (int i = 0; i <= maxLevel; i++)
			this.head.next[i] = tail;

	}

	// SkipList iterator class
	public static class SkipListIterator<T> implements Iterator<T> {

		private Entry<T> nextEntry;

		public SkipListIterator(Entry<T> head) {
			nextEntry = head;
		}

		@Override
		public boolean hasNext() {
			return nextEntry.next[0] != null;
		}

		@Override
		public T next() {
			if (!hasNext())
				return null;

			T result = nextEntry.element;
			nextEntry = nextEntry.next[0];

			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	// Helper function
	public Entry<T>[] find(T x) {
		// return prev[0...maxLevel] of nodes at which search went down one level,
		// looking for x
		Entry<T>[] prev = (Entry<T>[]) new Entry[maxLevel + 1];
		Entry<T> p = head;
		for (int i = maxLevel; i >= 0; i--) {
			while (p.next[i] != tail && p.next[i].element.compareTo(x) < 0) {
				p = p.next[i];
			}
			prev[i] = p;
		}
		return prev;

	}

	public int chooseLevel(int lev) {
		// Choose number of levels for a new node randomly
		// Prob(choosing level i) = 1/2 Prob(choosing level i - 1)
		Random random = new Random();
		int i = 0;
		while (i < lev) {

			boolean b = random.nextBoolean();

			if (b)
				i++;
			else
				break;

		}
		return i;
	}

	// Add x to list. If x already exists, replace it. Returns true if new node is
	// added to list
	public boolean add(T x) {
		Entry<T>[] prev = find(x);

		if (prev[0].next[0].element != null && prev[0].next[0].element.compareTo(x) == 0) {
			prev[0].next[0].element = x;
			return false;
		} else {
			int level = chooseLevel(maxLevel);
			Entry<T> n = new Entry<>(x, level);

			for (int i = 0; i <= level; i++) {
				n.next[i] = prev[i].next[i];
				prev[i].next[i] = n;

			}
			size++;
		}
		return true;
	}

	// Find smallest element that is greater or equal to x
	public T ceiling(T x) {
		Entry<T>[] prev = find(x);
		return prev[0].next[0].element;
	}

	// Does list contain x?
	public boolean contains(T x) {
		Entry<T>[] prev = find(x);

		if (prev[0].next[0] == null)
			return false;

		return prev[0].next[0].element.compareTo(x) == 0 ? true : false;
	}

	// Return first element of list
	public T first() {
		return head.next[0].element;
	}

	// Find largest element that is less than or equal to x
	public T floor(T x) {
		Entry<T>[] prev = find(x);
		if (prev[0].next[0].element == null)
			return prev[0].element;
		return prev[0].next[0].element.compareTo(x) == 0 ? prev[0].next[0].element : prev[0].element;
	}

	// Is the list empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// Iterate through the elements of list in sorted order
	public Iterator<T> iterator() {
		return new SkipListIterator<T>(head.next[0]);
	}

	// Return last element of list
	public T last() {

		if (isEmpty())
			return null;

		Entry<T> p = head;
		for (int i = maxLevel; i >= 0; i--) {
			while (p.next[i].element != null)
				p = p.next[i];
		}

		return (T) p.element;
	}

	// Remove x from list. Removed element is returned. Return null if x not in list
	public T remove(T x) {
		Entry<T>[] prev = find(x);
		Entry<T> n = prev[0].next[0];

		if (n.element.compareTo(x) != 0)
			return null;

		else {
			for (int i = 0; i <= maxLevel; i++) {
				if (prev[i].next[i] == n) {
					prev[i].next[i] = n.next[i];
					break;
				} else
					break;
			}
			size--;
			return n.element;
		}
	}

	// Return the number of elements in the list
	public int size() {
		return size;
	}

}
