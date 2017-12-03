/**
 * Created by
 * Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */

package cs6301.g29;
import java.util.Scanner;

public class EnumerationsDriver {
    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);
        System.out.println("Choose one of the options");
        System.out.println("1 for recursive Permutation");
        System.out.println("2 for Combination");
        System.out.println("3 for Permutation with Heap's algorithm");
        System.out.println("4 for Permutation with iterative Heap's algorithm");
        int options = in.nextInt();

        System.out.println("Enter n (Number of array elements");
        int n = in.nextInt();
        int[] A = new int[n];


        System.out.println("Enter Array");
        for(int i= 0 ; i< n ; i ++)
            A[i] = in.nextInt();


        int k = 0;
        if(options == 1 || options ==2) {
            System.out.println("Enter K");
            k = in.nextInt();
        }

        System.out.println("Enter Verbose flag");
        int VERBOSE = in.nextInt();
        in.close();

        Enumeration perm = new Enumeration(A, k, n);
        perm.setVerbose(VERBOSE);



        switch (options) {

            case 1:

                perm.permute();

                if (VERBOSE != 0)
                    System.out.println("Number of permutations :" + perm.count);

                break;

            case 2:

                perm.combination();

                if (VERBOSE != 0)
                    System.out.println("Number of combinations :" + perm.count);

                break;

            case 3:

                perm.heap();

                if (VERBOSE != 0)
                    System.out.println("Number of permutations :" + perm.count);

                break;

            case 4:

                perm.iterativeHeap();

                if (VERBOSE != 0)
                    System.out.println("Number of permutations :" + perm.count);

                break;

            default:
                System.out.println("Please select valid option");

        }

    }
}
