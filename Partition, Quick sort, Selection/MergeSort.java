package cs6301.g29;

public class MergeSort {

	public <T extends Comparable<? super T>> void insertionSort(T[] A, int p, int r) {

		T temp;
		for (int i = 1; i < A.length; i++) {
			for (int j = i; j > 0; j--) {
				if (A[j].compareTo(A[j - 1]) < 0) {
					temp = A[j];
					A[j] = A[j - 1];
					A[j - 1] = temp;
				}
			}
		}

	}

	public <T extends Comparable<? super T>> void merge(T[] A, T[] B, int p, int q, int r) {

		/**
		 * Merge A[p...q] and A[q+1..r] into A[p...r], in sorted order Per- Condition
		 * A[p...q] and A[q+1.... r] are in sorted order
		 */

		int i = p;
		int j = q + 1;

		for (int k = p; k < r; k++) {
			if (j > r || (i <= q && A[i].compareTo(A[j]) < 0))
				B[k] = A[i++];

			else
				B[k] = A[j++];
		}

	}

	public <T extends Comparable<? super T>> void mergeSort(T[] A, T[] B, int p, int r) {
		/**
		 * Sort A[p....r] or B[p...r] into A[p....r] Pre-Condition: A[p...r] and
		 * B[p...r] have the same elements
		 */

		// for more efficiency
		if (r - p < 27)
			insertionSort(A, p, r);
		else

		{
			int q = (p + r) / 2;
			mergeSort(B, A, p, q);
			mergeSort(B, A, q + 1, r);
			merge(B, A, p, q, r);
		}

	}

	public <T extends Comparable<? super T>> void mergeSort(T[] A) {
		T[] B = (T[]) new Comparable[A.length];
		// copying all the elements of array A into B
		B = A.clone();
		mergeSort(A, B, 0, A.length - 1);
	}

}