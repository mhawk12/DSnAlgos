import java.util.Random;

/**
 * Created by Nishant Shekhar (nxs167130)
 *            Anusha Agasthi  (nxa162430)
 *            Prince Patel    (pap160930)
 */
public class MergeSort {

    /**+
     * Function to shuffle the initial generated array
     * @param arr  initial generated array
     */
    public static void shuffle(int[] arr) {
        shuffle(arr, 0, arr.length-1);
    }


    /**+
     * Helper function to shuffle the initial generate array
     * @param arr initial generated array
     * @param from initial position of array from where to start shuffling
     * @param to   final position of array from where to start shuffling
     */
    public static void shuffle(int[] arr, int from, int to) {
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
     */
    static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }


    /**
     * print the array elements
     * @param arr  input array
     * @param message  user defined string as message to be printed with the array
     */
    static void printArray(int[] arr, String message) {
        printArray(arr, 0, arr.length-1, message);
    }

    /**
     * Helper function to print the array
     * @param arr input array
     * @param from initial index of the array from where array needs to be printed
     * @param to   final index of the array to where array needs to be printed
     * @param message
     */

    static void printArray(int[] arr, int from, int to, String message) {
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
     */
     public static void mergeSort(int[] arr, int[] tmp) {
         int length = arr.length;
         mergeSortHelper(arr, tmp,0, length -1);
     }


    /**
     * helper function to perform the merge sort
     * @param arr  input array
     * @param tmp  auxiliary array to store values of input array while merging
     * @param lowerIndex initial index of the array from where array needs to be sorted
     * @param higherIndex  final index of the array to where array needs to be sorted
     */
         public static void mergeSortHelper(int[] arr, int[] tmp, int lowerIndex, int higherIndex) {

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
     * @param middle   index between lower index and higerindex
     * @param HigherIndex  final index of the array to where array needs to be sorted
     */
     public static void merge(int[] arr, int[] tmp, int lowerIndex, int middle, int HigherIndex){

           for(int i = lowerIndex;  i <= HigherIndex; i++)
               tmp[i] = arr[i];


           int i = lowerIndex;
           int j = middle + 1;
           int k = lowerIndex;


           while(i <= middle && j <= HigherIndex){
               if(tmp[i] <= tmp[j]){
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
           //initial generated array
           int[] arr = new int[n];
           //auxiliary array to store input array
           int[] tmp = new int[n];
           for(int i=0; i<n; i++) {
               arr[i] = i;
           }

           //shuffle the generated array
           shuffle(arr);
           timer.start();
           //perform mergesort on the int array
           mergeSort(arr, tmp);
           timer.end();
           System.out.println(timer);
           printArray(arr, "After Merge Sort:");

       }
}
