/**
 * Created by 
 * Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */
package cs6301.g29;
import java.util.Comparator;

public class BinaryHeap<T> {
	T[] pq;
	Comparator<T> c;
	int size;

	/**
	 * Build a priority queue with a given array q, using q[0..n-1]. It is not
	 * necessary that n == q.length. Extra space available can be used to add new
	 * elements.
	 */
	public BinaryHeap(T[] q, Comparator<T> comp, int n) {
		pq = q;
		c = comp;
		size = n;
	}
    
	public void insert(T x) {
		add(x);
	}

	public T deleteMin() {
		return remove();
	}

	public T min() {
		return peek();
	}

	public void add(T x) {
		if (size == pq.length - 1)
			throw new RuntimeException("Can not add any more element");

		pq[size] = x;
		percolateUp(size);
		size++;

	}

	public T remove() {
		T min = pq[0];
		//Exchanging the top value with the value at the end of heap
		pq[0] = pq[--size];
		//Reforming the structure of the heap
		percolateDown(0);
		return min;
	}

	public T peek() {
		return pq[0];
	}

	public void replace(T x) {
		/*
		 * Replaces root of binary heap by x if x has higher priority (smaller) than
		 * root, and restore heap order. Otherwise do nothing. This operation is used in
		 * finding largest k elements in a stream.
		 */
		if (c.compare(peek(), x) == 0) {
			pq[0] = x;
			percolateDown(0);

		}

	}

	/** pq[i] may violate heap order with parents */
	void percolateUp(int i) {

		T X = pq[i];
		while (i > 0 && c.compare(X, pq[parent(i)]) < 0) {
			move(pq, i, pq[parent(i)]);
			i = parent(i);

		}
		move(pq, i, X);
	}

	/** pq[i] may violate heap order with children */
	void percolateDown(int i) {
		T X = pq[i];
		int C = 2 * i + 1;
		while (C <= size - 1) {
			if (C < size - 1 && c.compare(pq[C], pq[C + 1]) > 0)
				C++;

			if (c.compare(X, pq[C]) <= 0)
				break;
			move(pq, i, pq[C]);
			i = C;
			C = 2 * i + 1;

		}

		move(pq, i, X);
	}

	public int parent(int i) {
		return (i - 1) / 2;
	}

	public void move(T[] pq, int i, T X) {
		pq[i] = X;
		
	}

	/** Create a heap. Precondition: none. */
	void buildHeap() {
		for (int i = (size - 1) / 2; i >= 0; i--)
			percolateDown(i);
	}
	
	
	
	public boolean isEmpty() {
       if( size == 0) return  true;
       else return false;
	}

	/*
	 * sort array A[]. Sorted order depends on comparator used to buid heap. min
	 * heap ==> descending order max heap ==> ascending order
	 */
	
	public static <T> void heapSort(T[] A, Comparator<T> comp) {
		BinaryHeap<T> binaryHeap = new BinaryHeap<T>(A, comp, A.length);
		binaryHeap.buildHeap();

			for (int i = A.length - 1; i >= 0; i--)
				A[i] = (T) binaryHeap.deleteMin();
	}
}
