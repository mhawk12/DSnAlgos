/**
* Created by
*  Nishant Shekhar (nxs167130)
*  Anusha Agasthi  (nxa162430)
*  Prince Patel    (pap160930)
*/

package cs6301.g29;

import cs6301.g00.Shuffle;
import java.util.Comparator;
import java.util.Scanner;

public class BinaryHeapDriver {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		int MAXSIZE = 10000000;
//		Integer[] arr = new Integer[MAXSIZE];
		System.out.println("Enter the number of elements (N) :");
		int n = in.nextInt();
		Integer[] arr = new Integer[n];
		System.out.println("Enter 0 for Heap sort in asending order");
		System.out.println("Enter 1 for Heap sort in descending order");
		int options = in.nextInt();
		in.close();
		
		//Input array
		for (int i = 0; i < n; i++)
			arr[i] = new Integer(i);

		
		
		switch (options) {
		
		/**
		 * The uncommented code is left in intentionally in order to check input
		 * and shuffled data 
		 */
		
		case 0:

			System.out.println("Heap sort in asending order");
			// System.out.println("Before Shuffling");
			// for (Integer x : arr)
			// System.out.println(x);

			// Shuffling the array elements
			Shuffle.shuffle(arr);
			// System.out.println("After Shuffling");
			// for(Integer x : arr)
			// System.out.println(x);

			 Comparator<Integer> comparator_asc = new Comparator<Integer>() {
		            @Override
		            public int compare(Integer o1, Integer o2) {
		                if(o1.compareTo(o2) == -1) return 1;
		                else if (o1.compareTo(o2) == 1) return -1;
		                else return o1.compareTo(o2);
		                
		            }
		        };
		        
			BinaryHeap.heapSort(arr, comparator_asc);
			System.out.println("After Heap Sort");
			for(Integer x : arr)
			System.out.println(x);
			break;

		case 1:
			System.out.println("Heap sort in descending order");


			// System.out.println("Before Shuffling");
			// for (Integer x : arr)
			// System.out.println(x);

			// Shuffling the array elements
			Shuffle.shuffle(arr);
			// System.out.println("After Shuffling");
			// for(Integer x : arr)
			// System.out.println(x);

			Comparator<Integer> comparator_dsc = Integer::compareTo;
			BinaryHeap.heapSort(arr, comparator_dsc);
			System.out.println("After Heap Sort");
			for(Integer x : arr)
			System.out.println(x);
			break;
			
			
		default:
			System.out.println("Invalid Option");
			System.out.println("Please choose the correct option");

		}
	}
}
