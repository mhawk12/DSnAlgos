 /**
 * Created by Nishant Shekhar (nxs167130)
 *            Anusha Agasthi  (nxa162430)
 *            Prince Patel    (pap160930)
 */
package cs6301.g29;
public class BinarySearch {


    public static<T extends Comparable<? super T>> int binarySearch(T[] arr, T x){
       int m = binarySearch( arr, 0 , arr.length ,x);

        if( m == arr.length-1) return m;
        if(arr[m+1].compareTo(x) <= 0) return m+1;
        if(arr[m].compareTo(x) <= 0) return m;
        if( m -1  == -1 ) return  -1;
        if(arr[m-1].compareTo(x) <= 0) return m-1;


        return -1;
    }


     public static<T extends Comparable<? super T>> int binarySearch(T[] arr, int l , int r ,T x){

        if (l+1 < r)
        {
            int m =  l +(r-l)/2;

            if(arr[m].compareTo(x) == 0) return m;
            // If x greater, all elements before middle one are ignored
            if (arr[m].compareTo(x) < 0)
                return binarySearch(arr, m, r, x);


            // If x is smaller, ignore right half
            else
                return binarySearch(arr, l, m-1, x);
        }
        else {
            return  l +(r-l)/2;
        }
    }


    public static  void main(String agrs[]) {
        Integer arr[] = {2, 3, 4, 10, 40, 56, 300, 311, 589, 678, 784, 987, 1739};
        Integer x = 100;
        int result = binarySearch(arr, x);

        if (result == -1)
             System.out.println("No element is less than or equal to the given number");
        else System.out.println(result);
    }
}
