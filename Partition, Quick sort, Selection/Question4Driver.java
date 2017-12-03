package cs6301.g29;

import cs6301.g00.Timer;
import cs6301.g00.Shuffle;
import java.util.Scanner;

public class Question4Driver {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		QuickSort quickSort = new QuickSort();
		MergeSort mergeSort = new MergeSort();
		Timer timer = new Timer();
		System.out.println("Enter the number of elements (N) :");
		int n = in.nextInt();
		Integer[] arr = new Integer[n];
		System.out.println("Enter 1 for Comparision of Quick and Merge Sort on distinct elements");
		System.out.println("Enter 2 for Comparision of Quick and Merge Sort on array with duplicates");
		int options = in.nextInt();
		in.close();
		switch (options) {
		case 1:
			/**
			 * Quick Sort on arrays that are randomly ordered (by shuffle)
			 */
			System.out.println("Enter 1 for Comparision of Quick and Merge Sort on distinct elements");

			for (int i = 0; i < n; i++)
				arr[i] = new Integer(i);

			// System.out.println("Before Shuffling");
			// for (Integer x : arr)
			// System.out.println(x);

			// Shuffling the array elements
			Shuffle.shuffle(arr);
//			 System.out.println("After Shuffling");
//			 for(Integer x : arr)
//			 System.out.println(x);

			// performing quickSort on the shuffled array
			System.out.println("Quick Sort time on distinct elements");
			timer.start();
			quickSort.dualPivotQuickSort(arr);
			timer.end();
			System.out.println(timer);
//			 System.out.println("After QuickSort");
//			 for(Integer x : arr)
//			 System.out.println(x);

			for (int i = n - 1; i > 0; i--)
				arr[i] = new Integer(i);

//			 System.out.println("Before Shuffling");
//			 for (Integer x : arr)
//			 System.out.println(x);

			// Shuffling the array elements
			Shuffle.shuffle(arr);
//			 System.out.println("After Shuffling");
//			 for(Integer x : arr)
//			 System.out.println(x);

			// performing quickSort on the shuffled array
			System.out.println();
			System.out.println("Merge Sort time on distinct elements");
			timer.start();
			mergeSort.mergeSort(arr);
			timer.end();
			System.out.println(timer);
//			 System.out.println("After MergeSort");
//			 for(Integer x : arr)
//			 System.out.println(x);

			break;

		case 2:
			/**
			 * Quick Sort on arrays that are randomly ordered (by shuffle)
			 */
			System.out.println("Enter 1 for Comparision of Quick and Merge Sort on array with duplicates");
			int k = 1;
			for (int i = 0; i < n; i++) {
				if (i % 50 == 0) {
					k++;
					arr[i] = new Integer(k);
				} else 
					arr[i] = new Integer(k);
				

			}

//			 System.out.println("Before Shuffling");
//			 for (Integer x : arr)
//			 System.out.println(x);

			// Shuffling the array elements
			Shuffle.shuffle(arr);
//			 System.out.println("After Shuffling");
//			 for(Integer x : arr)
//			 System.out.println(x);

			// performing quickSort on the shuffled array
			System.out.println("Quick Sort time on array with duplicate elements");
			timer.start();
			quickSort.dualPivotQuickSort(arr);
			timer.end();
			System.out.println(timer);
//			 System.out.println("After QuickSort");
//			 for(Integer x : arr)
//			 System.out.println(x);

	
			for (int i = 0; i < n; i++) {
				if (i % 50 == 0) {
					k++;
					arr[i] = new Integer(k);
				} else 
					arr[i] = new Integer(k);
				

			}

			// System.out.println("Before Shuffling");
			// for (Integer x : arr)
			// System.out.println(x);

			// Shuffling the array elements
			Shuffle.shuffle(arr);
			// System.out.println("After Shuffling");
			// for(Integer x : arr)
			// System.out.println(x);

			// performing quickSort on the shuffled array
			System.out.println();
			System.out.println("Merge Sort time on array with duplicate elements");
			timer.start();
			mergeSort.mergeSort(arr);
			timer.end();
			System.out.println(timer);
			// System.out.println("After QuickSort");
			// for(Integer x : arr)
			// System.out.println(x);

			break;
			
			
        default:
    	    System.out.println("Invalid Option"); 
			System.out.println("Please choose the correct option"); 

		}

	}
}
