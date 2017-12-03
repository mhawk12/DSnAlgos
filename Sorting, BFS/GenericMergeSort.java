import java.util.Random;

/**
 * Created by Nishant Shekhar (nxs167130)
 *            Anusha Agasthi  (nxa162430)
 *            Prince Patel    (pap160930)
 */

/**
 *
 * @param <T> is the type of the input
 */
public class GenericMergeSort<T> {


    /**+
     * Function to shuffle the initial generated array
     * @param arr  initial generated array
     */
    public static <T extends Comparable<T>> void shuffle(T[] arr) {
        shuffle(arr, 0, arr.length-1);
    }


    /**+
     * Helper function to shuffle the initial generate array
     * @param arr initial generated array
     * @param from initial position of array from where to start shuffling
     * @param to   final position of array from where to start shuffling
     */
    public static <T extends Comparable<T>> void shuffle(T[] arr, int from, int to) {
        int n = to - from  + 1;
        Random rand = new Random();
        for(int i=1; i<n; i++) {
            int j = rand.nextInt(i);
            swap(arr, i+from, j+from);
        }
    }

    /**+
     * To shuffle two elements of an array
     * @param arr input array
     * @param x   index of the array one element that needs to be shuffled
     * @param y   index of the array other element that needs to be shuffled
     * @param <T> Type of input
     */
    public static<T extends Comparable<T>> void swap(T[] arr, int x, int y) {
        T tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    /**
     * print the array elements
     * @param arr  input array
     * @param message  user defined string as message to be printed with the array
     * @param <T> type of input
     */
    public static <T extends Comparable<T>> void printArray(T[] arr, String message) {
        printArray(arr, 0, arr.length-1, message);
    }


    /**
     * Helper function to print the array
     * @param arr input array
     * @param from initial index of the array from where array needs to be printed
     * @param to   final index of the array to where array needs to be printed
     * @param message user defined string as message to be printed with the array
     * @param <T> type of input
     */
    public static <T extends Comparable<T>>void printArray(T[] arr, int from, int to, String message) {
        System.out.print(message);
        for(int i=from; i<=to; i++) {
            System.out.println(" " + arr[i]);
        }
        System.out.println();
    }


    /**
     * function to perform merge sort of an int array
     * @param arr input array
     * @param tmp auxiliary array to store values of input array while merging
     * @param <T> type of input
     */

    public static <T extends Comparable<T>> void mergeSort(T[] arr, T[] tmp) {
        int length = arr.length;
        mergeSortHelper(arr, tmp,0, length -1);
    }


    /**
     * helper function to perform the merge sort
     * @param arr  input array
     * @param tmp  auxiliary array to store values of input array while merging
     * @param lowerIndex initial index of the array from where array needs to be sorted
     * @param higherIndex  final index of the array to where array needs to be sorted
     * @param <T> type of input
     */


    public static <T extends Comparable<T>> void mergeSortHelper(T[] arr, T[] tmp, int lowerIndex, int higherIndex) {

        if (lowerIndex < higherIndex) {
            int middle = (lowerIndex + higherIndex) / 2;

            //sort the left side
            mergeSortHelper(arr, tmp,lowerIndex, middle);
            //sort the right side
            mergeSortHelper(arr, tmp, middle + 1, higherIndex);
            //merge both the parts(left part and right part)
            merge(arr, tmp, lowerIndex, middle, higherIndex);
        }

    }

    /**
     * function to perform merge on the left and right part of the array in a mergesort operation
     * @param arr  input array
     * @param tmp  auxiliary array to store values of input array while merging
     * @param lowerIndex  initial index of the array from where array needs to be merged
     * @param middle   index between lower index and last index
     * @param HigherIndex  final index of the array to where array needs to be sorted
     * @param <T> type of input
     */
    public static <T extends Comparable<T>> void merge(T[] arr, T[] tmp, int lowerIndex, int middle, int HigherIndex){

        for(int i = lowerIndex;  i <= HigherIndex; i++)
            tmp[i] = arr[i];


        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;


        while(i <= middle && j <= HigherIndex){
            if(tmp[i].compareTo(tmp[j])<0){
                arr[k] = tmp[i];
                i++;
            }
            else {
                arr[k] = tmp[j];
                j++;
            }
            k++;
        }

        while (i <= middle){
            arr[k] = tmp[i];
            i++;
            k++;
        }
    }


    public static void main(String args[]) {

           Timer timer = new Timer();
           int n = 1000000;
           Integer[] arr = new Integer[n];
           Integer[] tmp = new Integer[n];
           //generating data
           for(int i=0; i<n; i++) {
               arr[i] = i;
           }

        //shuffle the array
        shuffle(arr);
        timer.start();
        //perform merge sort
        mergeSort(arr,tmp);
        timer.end();
        System.out.print(timer);
        //printArray(arr, "After Merge Sort:");
    }
}
