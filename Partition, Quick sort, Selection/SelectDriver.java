/**
* Created by Nishant Shekhar (nxs167130)
*            Anusha Agasthi  (nxa162430)
*            Prince Patel    (pap160930)
*/

package cs6301.g29;

import cs6301.g00.Timer;
import cs6301.g00.Shuffle;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SelectDriver {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Select select = new Select();
		Timer timer = new Timer();
		System.out.println("Enter the number of elements (N) :");
		int n = in.nextInt();
		Integer[] arr = new Integer[n];
		System.out.println("Enter the valule of K :");
		int k = in.nextInt();
		System.out.println("Enter 1 to find K largest elements using maxHeap");
		System.out.println("Enter 2 to find K largest elements using minHeap");
		System.out.println("Enter 3 to find K largest elements using  select algorithm");
		int options = in.nextInt();
		in.close();
		switch (options) {
		case 1:
			/**
			 * K largest elements using maxHeap
			 */
			System.out.println("K largest elements using maxHeap");

			for (int i = 0; i < n; i++)
				arr[i] = new Integer(i);

			// System.out.println("Before Shuffling");
			// for (Integer x : arr)
			// System.out.println(x);

			// Shuffling the array elements
			Shuffle.shuffle(arr);
			// System.out.println("After Shuffling");
			// for(Integer x : arr)
			// System.out.println(x);
			// System.out.println("___________________________");
			if (k <= 0)
				System.out.println("No elements");
			else if (k > arr.length)
				System.out.println("All the elements of the input arrya");
			else {
				timer.start();
				PriorityQueue<Integer> maxHeap = select.maxHeapKLargest(arr, k);
				System.out.println(timer);
				for (int i = 0; i < k; i++) {
					System.out.println(maxHeap.poll());
				}
			}
			break;

		case 2:
			/**
			 * K largest elements using minHeap
			 */
			System.out.println("K largest elements using minHeap");

			for (int i = 0; i < n; i++)
				arr[i] = new Integer(i);

			// System.out.println("Before Shuffling");
			// for (Integer x : arr)
			// System.out.println(x);

			// Shuffling the array elements
			Shuffle.shuffle(arr);
			// System.out.println("After Shuffling");
			// for(Integer x : arr)
			// System.out.println(x);
			if (k <= 0)
				System.out.println("No elements");
			else if (k > arr.length)
				System.out.println("All the elements of the input arrya");
			else {
				timer.start();
				PriorityQueue<Integer> maxHeap = select.minHeapKLargest(arr, k);
				System.out.println(timer);
				for (Integer num : maxHeap)
					System.out.println(num);
			}

			break;

		case 3:
			/**
			 * K largest elements using select algorithm
			 */
			System.out.println("K largest elements using  select algorithm");

			for (int i = 0; i < n; i++)
				arr[i] = new Integer(i);

			// System.out.println("Before Shuffling");
			// for (Integer x : arr)
			// System.out.println(x);

			// Shuffling the array elements
			Shuffle.shuffle(arr);
			// System.out.println("After Shuffling");
			// for(Integer x : arr)
			// System.out.println(x);
			//
			// System.out.println(x);
			if (k <= 0)
				System.out.println("No elements");
			else if (k > arr.length)
				System.out.println("All the elements of the input arrya");
			else {
				timer.start();
				int K = select.selectKLargest(arr, k);
				timer.end();
				System.out.println(timer);
				for (int i = K; i <= arr.length - 1; i++)
					System.out.println(arr[i]);
				break;
			}
		default:
			System.out.println("Invalid Option");
			System.out.println("Please choose the correct option");

		}
	}
}
