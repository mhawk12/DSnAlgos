/**
 * Created by
 * Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */

package cs6301.g29;
public class Enumeration {

    int[] A;
    int[] chosen;
    int k;
    int n;
    int VERBOSE;
    int count;

    public Enumeration(int[] A, int k , int  n){
        this.A = A;
        this.k = k;
        this.n = n;
        VERBOSE = 0;
        count = 0;
        chosen = new int[k];
    }

    public  void setVerbose(int VERBOSE){
        this.VERBOSE = VERBOSE;
    }

    //recursive function to get nPk (permutation of k elements out of n elements)
    public int  permute(){
        permute(k);
        return count;
    }

    //helper recursive function for permute function defined above
    private void permute(int c){
        if( c == 0) {
            //visit all permutations in A[0.. k-1]
            count++;
            if(VERBOSE == 0)
            print(A);
        }

        else {
            int d = k - c;
            permute(c - 1);

            for (int i = d + 1 ; i< n; i++){
                int temp = A[d];
                A[d] =A[i];
                A[i] = temp;
                permute(c -1 );
                A[i] = A[d];
                A[d] = temp;
            }
        }
    }

    //recursive function to get nPk (permutation of k elements out of n elements)
    public void heap(){
        heap(n);
    }

    private void heap(int g){
        if( g == 1) {
            //visit all permutations in A[0.. k-1]
            count++;
            if(VERBOSE == 0)
                print(A);
        }

        else {


            for (int i = 0   ; i< g - 1; i++){
                heap(g - 1);

                if(g % 2 == 0)
                    exchange(i, g -1);
                else
                    exchange(0, g -1);

            }
            heap(g -1);
        }
    }

    /**
     * function to exchange two elements of array A at indices i and j
     * @param i first index
     * @param j second index
     */
    private void exchange (int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    //iterative heap function
    public void iterativeHeap(){
        iterativeHeap(n);
    }

    /**
     * iterative function to compute permutations of all the elements of an array
     * Reference : https://en.wikipedia.org/wiki/Heap%27s_algorithm
     * @param g
     */

    private void iterativeHeap(int  g){
        int[] c = new int[A.length];

        count++;
        if (VERBOSE == 0)
        print(A);


        int i = 0;

        while (i < n){
            if(c[i] < i){
                if (i % 2 == 0 )
                    exchange(0, i);
                else
                    exchange(c[i], i);

                count++;
                if (VERBOSE == 0)
                print(A);

                c[i] += 1;
                i = 0 ;

            }
            else {
                c[i] = 0;
                i += 1;
            }
        }
    }


    //recursive function to get nCk (combination of k elements out of n elements)
    public int  combination(){
        combination(0, k);
        return count;
    }


    //helper recursive function for combination function defined above
    private void combination(int i, int c){
        if( c == 0) {
            //visit all permutations in A[0.. k-1]
            count++;
            if(VERBOSE == 0)
                print(chosen);
        }

        else {
            chosen[k - c] = A[i];
            combination(i+1, c -1);
            if(n-i > c)
                combination(i+1,c);

        }
    }

    //function to print all the elements of an array
    public void print(int[] array) {
        for (int x : array)
            System.out.print(x);

        System.out.println();
    }
}
