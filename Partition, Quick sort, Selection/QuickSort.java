/**
* Created by Nishant Shekhar (nxs167130)
*            Anusha Agasthi  (nxa162430)
*            Prince Patel    (pap160930)
*/

package cs6301.g29;
import java.util.Random;

public class QuickSort  {
    /**
     * swaps two elements of an array at index1 and index2
     * @param arr input array
     * @param index1 first index
     * @param index2 second index
     * @param <T> input type Type
     */
    public <T extends Comparable<? super T>> void swap(T[] arr, int index1, int index2){

        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2]= temp;
    }


  public <T extends Comparable<? super T>> int partition(T[] arr, int p, int r ){
      Random random = new Random();
      int  i = random.nextInt(r - p + 1) + p;
      swap(arr,i, r);
      T x = arr[r]; //pivot element
      i = p-1;

      //LI : arr[p...i]<= x, arr[i+1...j-1]>x
      //arr[j...r-1] is unprocessed , arr[r] = x
      for (int j = p ; j <= r-1 ; j++){
          if(arr[j].compareTo(x)<=0){
              i++;
              swap(arr,i,j);
          }
      }
      //Bring pivot back to the middle
      swap(arr,i+1,r);
      //arr[p...1] <=x, ar[i+1] = x, arr[i+2...r] > x
      return i+1;
  }
  

  public <T extends Comparable<? super T>> void quickSort(T[] arr, int p,int r){
      if (p < r){
          int q = partition(arr, p, r);
          quickSort(arr, p, q-1);
          quickSort(arr, q+1, r);
      }
  }


    public <T extends Comparable<? super T>> void quickSort(T[] arr){
        quickSort(arr,0,arr.length-1);
    }

////////////////////////////////////////////////////////////////////////////////////////////////
//Part 2 of Question 1 : Using Partition 2
    
    public <T extends Comparable<? super T>> int partition2(T[] arr, int p, int r ){
        Random random = new Random();
        int  i = random.nextInt(r - p + 1) + p;
        T x = arr[i]; //pivot element
        i = p-1;
        int j = r+1;

        //LI : arr[p...i]<= x, arr[i+1...j-1]>=x
        while(true) {
        	//moving to the index before which all elements are smaller than x
        	while(arr[i].compareTo(x)< 0)
        	      i++;
        	
        	//moving to the index after which all elements are greater than x
        	while(arr[i].compareTo(x)>0)
        		  j++;
        	
        	
        	//if lower index crosses the upper index break the loop
        	if(i >= j)
        		return j;
        	
        	//exchange element at index i with element at index j
        	swap(arr,i,j);
        }

    }
    
    

    public <T extends Comparable<? super T>> void quickSort2(T[] arr, int p,int r){
        if (p < r){
            int q = partition2(arr, p, r);
            quickSort2(arr, p, q);
            quickSort2(arr, q+1, r);
        }
    }


      public <T extends Comparable<? super T>> void quickSort2(T[] arr){
          quickSort(arr,0,arr.length-1);
      }

//////////////////////////////////////////////////////////////////////////////////////////////////      
      public <T extends Comparable<? super T>> void dualPivotQuickSort(T[] arr, int p, int r) {
    	  if(r <= p) return;
          
//          if( r - p < 27)
//        	  insertionSort(arr, p , r);
          
          T x1=arr[p];
          T x2=arr[r];

          
          /*
           * if the second  pivot is less than first pivot 
           * then exchange r values
           */
          if (x1.compareTo(x2)>0){
              swap(arr, p, r);
              x1=arr[p];
              x2=arr[r];
          }
          /**
           * if both the pivots are equal then move the index till 
           * we get different pivot values
           */
          else if (x1.compareTo(x2)==0){
              while (x1.compareTo(x2)==0 && p<r){
                  p++;
                  x1=arr[p];
              }
          }


          int i=p+1;
          int k=p+1;
          int j=r-1;

          while (i<=j){
        
        	  /**
        	   * move all the elements less than first pivot 
        	   * to left of it 
        	   */
              if (arr[i].compareTo(x1)<0){
                  swap(arr, i++, k++);
              }
              /**
               * move all the elements greater than second pivot to the 
               * right
               */
              else if ((x2.compareTo(arr[i])<0)){
                  swap(arr, i, j--);
              }
              else{
                  i++;
              }

          }


          swap(arr, p, --k);
          swap(arr, r, ++j);

          dualPivotQuickSort(arr, p, k-1);
          if(x1.compareTo(x2) != 0 )
          dualPivotQuickSort (arr, k+1, j-1);
          dualPivotQuickSort(arr, j+1, r);

      }
      
      
      
      
      public <T extends Comparable<? super T>> void dualPivotQuickSort (T[] arr){
      	dualPivotQuickSort (arr, 0, arr.length-1);
      }


}
