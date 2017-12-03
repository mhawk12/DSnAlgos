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
public class GenericInsertionSort<T> {
    /**+
     * Function to shuffle the initial generated array
     * @param arr  initial generated array
     */
    public static <T extends Comparable<T>> void shuffle(T[] arr) {
        shuffle(arr, 0, arr.length-1);
    }


    /**
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

    /**
     * To shuffle two elements of an array
     * @param arr input array
     * @param x   index of the array one element that needs to be shuffled
     * @param y   index of the array other element that needs to be shuffled
     * @param <T> Type of input
     */
    public static <T extends Comparable<T>> void swap(T[] arr, int x, int y) {
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
     * @param message  user defined string as message to be printed with the array
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
     * function to perform Insertion sort
     * @param arr input array
     * @param <T> type of input
     */

    public static <T extends Comparable<T>> void insertionSort(T[] arr) {
          for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    public static void main(String args[]) {
           Timer timer = new Timer();
           int n = 1000000;
           Integer[] arr = new Integer[n];
           //generating data
           for(int i=0; i<n; i++) {
               arr[i] = i;
           }

        //shuffle the array
        shuffle(arr);
        timer.start();
        //perform insertion sort
        insertionSort(arr);
        timer.end();
        System.out.print(timer);
        printArray(arr, "After Insertion Sort:");

    }
}
