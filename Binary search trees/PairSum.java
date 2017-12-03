package cs6301.g29;

/**
* Created by Nishant Shekhar (nxs167130)
*            Anusha Agasthi  (nxa162430)
*            Prince Patel    (pap160930)
*/

import java.util.Arrays;
import java.util.Scanner;

public class PairSum {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		System.out.println("Enter the size of array");
		int N = in.nextInt();
		int[] A = new int[N];

		System.out.println("Enter the array elements");
		for (int i = 0; i < N; i++)
			A[i] = in.nextInt();

		System.out.println("Enter the value of Sum");
		int X = in.nextInt();
        
		System.out.println("Number of pairs: ");
		System.out.println(howMany(A, X));
		in.close();

	}

	/**
	 * function to count pairs to elements of array A which sum to X
	 * 
	 * @param A
	 *            input array
	 * @param X
	 *            Sum
	 * @return count of pairs of elements of array A which sum to X
	 */
	static int howMany(int[] A, int X) {
		// if there is just a single element we can not have pairs
		if (A.length < 2) {
			return 0;
		}

		int countOfPairs = 0;
		int left = 0;
		int right = A.length - 1;

		// Sorting the input Array
		Arrays.sort(A);
		// if the sum can be formed from the element that starts from beginning
		if (X == A[0] * 2) {
			int countZeros = countZeros(A);
			countOfPairs += (countZeros * (countZeros - 1)) / 2;
			left += countOfPairs;
		}

		// boolean to indicate whether to traverse in right direction or left
		// to find the duplicate elements
		int RIGHT = 0;
		int LEFT = 1;

		while (left < right) {
			int sum = A[left] + A[right];
			if (sum == X) {
				int duplicateRight = countDuplicates(A, left, RIGHT);
				left += duplicateRight;
				int duplicatesLeft = 1;

				// counting of duplicates has crossed right index
				// then these elements need not be counted as the
				// contiguous, so the pairs will no of duplicates
				// from left divided by 2
				if (left < right) {
					duplicatesLeft = countDuplicates(A, right, LEFT);
					right -= duplicatesLeft;
				} else if (X == A[left] * 2) {
					int countZeros = countZeros(A);
					countOfPairs += (countZeros * (countZeros - 1)) / 2;
					return countOfPairs;
				}

				countOfPairs += ((duplicateRight) * (duplicatesLeft));
			}
			// if sum of present pairs is less than the given sum
			// move the left pointer to one right
			else if (sum < X) {
				left += countDuplicates(A, left, RIGHT);

			}

			// if sum of present pairs is greater than the given sum
			// move the right pointer to one left
			else if (sum > X) {
				right -= countDuplicates(A, right, LEFT);
			}
		}

		return countOfPairs;
	}

	/**
	 * function to count the number of duplicates elements in right or left
	 * direction in an array
	 * 
	 * @param A
	 *            input array
	 * @param index
	 *            index from where the checking of duplicates should start
	 * @param direction
	 *            0 for left .. 1 for right
	 * @return no of duplicates
	 */
	static int countDuplicates(int[] A, int index, int direction) {
		int count = 1;
		switch (direction) {
		case 0:
			while (A[index] == A[++index] && index < A.length - 1) {
				count++;
			}
			break;
		case 1:
			while (A[index] == A[--index] && index > 0) {
				count++;
			}
			break;

		}
		return count;
	}

	/**
	 * it counts the number of zeros in an array
	 * 
	 * @param A
	 *            input array
	 * @return number of zeros in the array
	 */
	static int countZeros(int[] A) {
		int countZeros = 0;
		int firstElement = A[0];
		for (int a : A) {
			if (a == firstElement)
				countZeros++;
		}
		return countZeros;
	}

}