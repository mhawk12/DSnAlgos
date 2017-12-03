/**
 * Created by 
 * Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */

package cs6301.g29;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PerfectNumber implements Comparable<PerfectNumber> {
    private final long value;
    private final int a;
    private final int b;

    public PerfectNumber(int a, int b) {
        this.value = power(a, b);
        this.a = a;
        this.b = b;
    }


    public static long power(int a, int b) {
        long pow = 1;
        for (int i = 0; i < b; i++) {
            pow *= a;
        }
        return pow;
    }

    public int compareTo(PerfectNumber that) {
        if      (this.value < that.value) return -1;
        else if (this.value > that.value) return +1;
        else                              return  0;
    }

    public String toString() {
        return value + " =" + a + "^" + b;
    }


    public static void main(String[] args) {
        int x = 2;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter value of N");
        int N = in.nextInt();
        in.close();
        // initialize priority queue
        PriorityQueue<PerfectNumber> pq = new PriorityQueue<>();

        // maximum power representable as a long is 2^62
        for (int b = x; b <= N; b++) {
            pq.offer(new PerfectNumber(2, b));
        }

        // find smallest power, print out, and update
        while (!pq.isEmpty()) {
            PerfectNumber p = pq.poll();
            System.out.println(p);

            // add next perfect power if it doesn't overflow a long
            if (Math.pow(p.a + 1, p.b) < Long.MAX_VALUE)
                pq.offer(new PerfectNumber(p.a + 1, p.b));
        }
    }

}
