/**
* Created by Nishant Shekhar (nxs167130)
*            Anusha Agasthi  (nxa162430)
*            Prince Patel    (pap160930)
*/

package cs6301.g29;
import cs6301.g00.Timer;

import cs6301.g00.Shuffle;
import java.util.Scanner;
public class QuickSortDriver {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		QuickSort quickSort = new QuickSort();
		Timer timer = new Timer();
        System.out.println("Enter the number of elements (N) :");
        int n = in.nextInt();
        Integer[] arr = new Integer[n];
        System.out.println("Enter 1 for QuickSort with first partition(NOT Hoare algorithm");
        System.out.println("Enter 2 for QuickSort with first partition(Hoare algorithm");
        System.out.println("Enter 3 for QuickSort with dual pivot");
        int options = in.nextInt();
        in.close();
        switch(options) {
        case 1:
        /**
         * Quick Sort on arrays that are randomly ordered (by shuffle)
         */
   		 System.out.println("Quick sort with partion 1 (NOT Hoare algorithm)");
		
		for (int i = 0; i < n; i++) 
			arr[i] = new Integer(i);
		
//		System.out.println("Before Shuffling");
//		for (Integer x : arr)
//			System.out.println(x);

		//Shuffling the array elements
		 Shuffle.shuffle(arr);
//		 System.out.println("After Shuffling");
//		 for(Integer x : arr)
//		 System.out.println(x);
		 
		 //performing quickSort on the shuffled array
		 timer.start();
		 quickSort.quickSort(arr);
		 timer.end();
		 System.out.println("Time taken to sort random , distinct elements ");
		 System.out.println(timer);
//		 System.out.println("After QuickSort");
//		 for(Integer x : arr)
//		 System.out.println(x);
		 
		 
		 
		 /**
		  * Part 2 of Question 1
		  * arrays in descending order.  
		  */
		 
		 for(int i = n-1 ; i > 0 ; i--) 
			 arr[i] = new Integer(i);
		 
		
//			System.out.println("Before Shuffling");
//			for (Integer x : arr)
//				System.out.println(x);

			//Shuffling the array elements
			 Shuffle.shuffle(arr);
//			 System.out.println("After Shuffling");
//			 for(Integer x : arr)
//			 System.out.println(x);
			 
			 //performing quickSort on the shuffled array
			 timer.start();
			 quickSort.quickSort(arr);
			 timer.end();
			 System.out.println();
			 System.out.println("Time taken to sort elements of an array in descending order");
			 System.out.println(timer);
//			 System.out.println("After QuickSort");
//			 for(Integer x : arr)
//			 System.out.println(x);
			 
			 break;
			 
			 
        case 2:
            /**
             * Quick Sort on arrays that are randomly ordered (by shuffle)
             */
     		 System.out.println("Quick sort with partion 1 (Hoare algorithm)");
    		
    		for (int i = 0; i < n; i++) 
    			arr[i] = new Integer(i);
    		
//    		System.out.println("Before Shuffling");
//    		for (Integer x : arr)
//    			System.out.println(x);

    		//Shuffling the array elements
    		 Shuffle.shuffle(arr);
//    		 System.out.println("After Shuffling");
//    		 for(Integer x : arr)
//    		 System.out.println(x);
    		 
    		 //performing quickSort on the shuffled array
    		 timer.start();
    		 quickSort.quickSort2(arr);
    		 timer.end();
    		 System.out.println("Time taken to sort random , distinct elements ");
    		 System.out.println(timer);
//    		 System.out.println("After QuickSort");
//    		 for(Integer x : arr)
//    		 System.out.println(x);
    		 
    		 
    		 
    		 /**
    		  * Part 2 of Question 1
    		  * arrays in descending order.  
    		  */
    		 
    		 for(int i = n-1 ; i > 0 ; i--) 
    			 arr[i] = new Integer(i);
    		 
    		
//    			System.out.println("Before Shuffling");
//    			for (Integer x : arr)
//    				System.out.println(x);

    			//Shuffling the array elements
    			 Shuffle.shuffle(arr);
//    			 System.out.println("After Shuffling");
//    			 for(Integer x : arr)
//    			 System.out.println(x);
    			 
    			 //performing quickSort on the shuffled array
    			 timer.start();
    			 quickSort.quickSort2(arr);
    			 timer.end();
    			 System.out.println();
    			 System.out.println("Time taken to sort elements of an array in descending order");
    			 System.out.println(timer);
//    			 System.out.println("After QuickSort");
//    			 for(Integer x : arr)
//    			 System.out.println(x);
    			 
    			 break;
    			 
    			 
        	
        case 3:
            /**
             * Quick Sort on arrays that are randomly ordered (by shuffle)
             */
     		 System.out.println("Quick sort with dual pivot partition");
    		
    		for (int i = 0; i < n; i++) 
    			arr[i] = new Integer(i);
    		
//    		System.out.println("Before Shuffling");
//    		for (Integer x : arr)
//    			System.out.println(x);

    		//Shuffling the array elements
    		 Shuffle.shuffle(arr);
//    		 System.out.println("After Shuffling");
//    		 for(Integer x : arr)
//    		 System.out.println(x);
//    		 
    		 //performing quickSort on the shuffled array
    		 timer.start();
    		 quickSort.dualPivotQuickSort(arr);
    		 timer.end();
    		 System.out.println("Time taken to sort random , distinct elements ");
    		 System.out.println(timer);
//    		 System.out.println("After QuickSort");
//    		 for(Integer x : arr)
//    		 System.out.println(x);
    		 
    		 
    		 
    		 /**
    		  * Part 2 of Question 1
    		  * arrays in descending order.  
    		  */
    		 
    		 for(int i = n-1 ; i > 0 ; i--) 
    			 arr[i] = new Integer(i);
    		 
    		
//    			System.out.println("Before Shuffling");
//    			for (Integer x : arr)
//    				System.out.println(x);

    			//Shuffling the array elements
    			 Shuffle.shuffle(arr);
//    			 System.out.println("After Shuffling");
//    			 for(Integer x : arr)
//    			 System.out.println(x);
    			 
    			 //performing quickSort on the shuffled array
    			 timer.start();
    			 quickSort.dualPivotQuickSort(arr);
    			 timer.end();
    			 System.out.println();
    			 System.out.println("Time taken to sort elements of an array in descending order");
    			 System.out.println(timer);
//    			 System.out.println("After QuickSort");
//    			 for(Integer x : arr)
//    			 System.out.println(x);
    			 
    			 break;
    			 
        default:
        	    System.out.println("Invalid Option"); 
    			System.out.println("Please choose the correct option"); 
    			 
        	
        }
	}
}
