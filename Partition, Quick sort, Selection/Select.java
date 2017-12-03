package cs6301.g29;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by Nishant Shekhar (nxs167130) 
 *            Anusha Agasthi (nxa162430) 
 *            Prince Patel (pap160930)
 */

public class Select {

////////////////////////////////////////////////////////////////////////////////////////
	//finding K largest elements using maxHeap  O(k * log n).
	public <T extends Comparable<? super T>> PriorityQueue<T> maxHeapKLargest(T[] arr, int k) {
		if (arr.length == 0)
			return null;

		PriorityQueue<T> maxHeap = new PriorityQueue<>(arr.length, Collections.reverseOrder());

		for (T num : arr)
			maxHeap.add(num);

		return maxHeap;

	}
	
////////////////////////////////////////////////////////////////////////////////////////
//finding K largest elements using minHeap O((n - k) log k))

	public <T extends Comparable<? super T>> PriorityQueue<T> minHeapKLargest(T[] arr, int k) {
		if (arr.length == 0)
			return null;

		PriorityQueue<T> maxHeap = new PriorityQueue<>(k);

		for (T num : arr) {
			if (maxHeap.size() < k)
				maxHeap.add(num);
			else {
				if (num.compareTo(maxHeap.peek()) > 0) {
					maxHeap.poll();
					maxHeap.add(num);
				}
			}

		}
		
		return maxHeap;

	}

	
////////////////////////////////////////////////////////////////////////////////////////
//finding K largest elements using  select algorithm O(n)
	/**
	 * swaps two elements of an array at index1 and index2
	 * 
	 * @param arr
	 *            input array
	 * @param index1
	 *            first index
	 * @param index2
	 *            second index
	 * @param <T>
	 *            input type Type
	 */
	public <T extends Comparable<? super T>> void swap(T[] arr, int index1, int index2) {

		T temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	public <T extends Comparable<? super T>> int partition(T[] arr, int p, int r) {
		Random random = new Random();
		int i = random.nextInt(r - p + 1) + p;
		swap(arr, i, r);
		T x = arr[r]; // pivot element
		i = p - 1;

		// LI : arr[p...i]<= x, arr[i+1...j-1]>x
		// arr[j...r-1] is unprocessed , arr[r] = x
		for (int j = p; j <= r - 1; j++) {
			if (arr[j].compareTo(x) <= 0) {
				i++;
				swap(arr, i, j);
			}
		}
		// Bring pivot back to the middle
		swap(arr, i + 1, r);
		// arr[p...1] <=x, ar[i+1] = x, arr[i+2...r] > x
		return i + 1;
	}

	public <T extends Comparable<? super T>> int selectKLargest(T[] arr, int p, int r, int k) {

		int pivot = partition(arr, p, r);
		if (pivot == k)
			return k;

		if (pivot > k)
			return selectKLargest(arr, p, pivot - 1, k);

		else
			return selectKLargest(arr, pivot + 1, r, k);

	}

	public <T extends Comparable<? super T>> int selectKLargest(T[] arr, int k) {

		return selectKLargest(arr, 0, arr.length - 1, arr.length - k);
		
	}

}