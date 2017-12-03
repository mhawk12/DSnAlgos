/**
 * Created by Nishant Shekhar (nxs167130)
 *            Anusha Agasthi  (nxa162430)
 *            Prince Patel    (pap160930)
 */
package cs6301.g29;
import cs6301.g00.Timer;
import java.math.BigInteger;

public class Fibonacci {

    private static final int MAX = 1000000;
    static BigInteger[] fibValue = new BigInteger[MAX];
    static BigInteger two = new BigInteger("2");


    public Fibonacci(){
        for (int i = 0 ;  i< fibValue.length ; i++){
            fibValue[i] = new BigInteger("0");
        }
    }

    static BigInteger linearFibonacci(int n)
    {
        int i;

    /* 0th and 1st number of the series are 0 and 1*/
        fibValue[0] = new BigInteger("0");
        fibValue[1] = new BigInteger("1");

        for (i = 2; i <= n; i++)
        {
            /**
             * Below equation is equivalent to  f[i] = f[i-1] + f[i-2];
             */
              fibValue[i] = fibValue[i-1].add(fibValue[i-2]);
        }

        return fibValue[n];
    }

    static BigInteger logFibonnaci(int n){


        //Base condition
        if(n == 0)
            return new BigInteger("0");
        if(n==1 || n == 2)
            return new BigInteger("1");

        //If n is even then k = n/2 or (n+1)/2
        int k = ((n & 1) == 0)? n/2 : (n+1)/2;


        /**
         * Below Equation is equivalent to the equation
         * fibValue[n] = (n & 1)? (logFibonnaci(k)*logFibonnaci(k) + logFibonnaci(k-1)*logFibonnaci(k-1)): (2*logFibonnaci(k-1) + logFibonnacik))*logFibonnaci(k);
         */
        fibValue[n] = ((n & 1) == 0) ? logFibonnaci(k).multiply(logFibonnaci(k).add(two.multiply(logFibonnaci(k-1)))) :
                                        logFibonnaci(k).multiply(logFibonnaci(k)).add(logFibonnaci(k-1).multiply(logFibonnaci(k-1))) ;


       return fibValue[n];
    }

    public static void main(String args[]) {

        Timer timer = new Timer();

        timer.start();
        BigInteger fib1 = linearFibonacci(100);
        timer.end();
        System.out.println("Linear Fibonacci value :" + fib1);
        System.out.println(timer);

        timer.start();
        BigInteger fib2 = logFibonnaci(100);
        timer.end();
        System.out.println("Log Fibonacci value :" + fib2);
        System.out.println(timer);


    }
}
