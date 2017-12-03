/**
 * Created by Nishant Shekhar (nxs167130)
 *            Anusha Agasthi  (nxa162430)
 *            Prince Patel    (pap160930)
 */
package cs6301.g29;
public class Rearrange {


    void printArray(int arr[])
    {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    //function to swap two elements
    void swap(int arr[], int l, int r)
    {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
    }

    /**
     *  Function to reverse an array. An array can be
     *  reversed in O(n) time and O(1) space.
     */
    void reverse(int arr[], int l, int r)
    {
        if (l < r)
        {
            swap(arr,l, r);
            reverse(arr, ++l, --r);
        }
    }

    /**
     * Merges two subarrays of arr[]. First subarray is arr[l..m] Second subarray is arr[m+1..r]
     * @param arr input int array
     * @param l  starting index of arr
     * @param m  middle index of arr
     * @param r  end index of ar
     */

    void merge(int arr[], int l, int m, int r)
    {
        int i = l; // Initial index of 1st subarray
        int j = m + 1; // Initial index of IInd

        // move to the index after which all elements are positives
        while (i <= m && arr[i] < 0)
            i++;

        // move to the index after which all elements are positives
        while (j <= r && arr[j] < 0)
            j++;


        // reverse positive part of left sub-array (arr[i..m])
        reverse(arr, i, m);

        // reverse negative part of right sub-array (arr[m+1..j-1])
        reverse(arr, m + 1, j - 1);

        // reverse arr[i..j-1]
        reverse(arr, i, j - 1);
    }


    /**
     * helper function to Rearrange positive and negative numbers in a array
     * @param arr input int array
     * @param l starting index of the array
     * @param r ending index of the array
     */
    void rearrangeMinusPlus(int arr[], int l, int r)
    {
        if (l < r)
        {
            int m = l + (r - l) / 2;

            // rearrange first and second halves
            rearrangeMinusPlus(arr, l, m);
            rearrangeMinusPlus(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    //function called from the driver code
    void rearrangeMinusPlus(int arr[])
    {
        rearrangeMinusPlus(arr,0,arr.length-1);
    }


    public static void main(String args[]) {

        int arr[] = {13, -12, 11, -13, -5, 6, -7, 0, 5, -3, -6};
        Rearrange posNeg = new Rearrange();
        posNeg.rearrangeMinusPlus(arr);
        posNeg.printArray(arr);
    }
}
