package cs6301.g29;

/**
 * Created by Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */

import java.util.ArrayList;
import java.util.List;

public class Num { //implements Comparable<Num>
    static long defaultBase = 10;  // This can be changed to what you want it to be.
    long base = defaultBase;  // Change as needed
    boolean sign = false;
    ArrayList<Long> number;


    /**
     * Constructor which takes a long value and converts it into Num
     * @param x Input value
     */
    public Num(long x) {

        number = new ArrayList<>();
        if (x < 0) {
            sign = true;
            x *= -1;
        }
        if (x == 0) number.add(new Long(0));
        while (x > 0) {
            long modOfCurrentNumber = x % base;
            number.add(modOfCurrentNumber);
            x /= base;
        }
    }

    /**
     * Constructor which takes a String value and converts it into Num
     * @param s Input value
     */
    public Num(String s) {
        number = new ArrayList<>();
        String stringNumber;

        //check for the sign of the value entered
        if (s.charAt(0) == '-') sign = true;

        //if input is negative , separate sign and keep just the absolute value
        if (sign) stringNumber = new StringBuilder(s).deleteCharAt(0).reverse().toString();
        else stringNumber = new StringBuilder(s).reverse().toString();

        String[] strings = stringNumber.split("");

        for (String str : strings) {
            number.add(Long.parseLong(str));
        }
    }

    /**
     * Contstrctor which takes array list and make it the Num
     * @param x ArrayList
     */
    public Num(ArrayList<Long> x) {
        number = x;
    }


    // Below are the add function its  helper

    /**
     * Add two Num where absolute value of first input is greater than second
     * @param a First Num Input
     * @param b Second Num Input
     * @return Addition of two Num values
     */
    public static Num addBig(Num a, Num b) {

        int l_a = a.number.size();
        int l_b = b.number.size();
        ArrayList<Long> r = new ArrayList<>(l_a);
        long carry = 0;
        long BASE = a.base;
        long sum ;
        int i ;

        //logic as discussed in class
        for (i = 0; i < l_b; i++) {
            sum = a.number.get(i) + b.number.get(i) + carry;
            carry = sum >= BASE ? 1 : 0;
            r.add(i, sum - carry * BASE);
        }
        while (i < l_a) {
            sum = a.number.get(i) + carry;
            carry = (sum == BASE) ? 1 : 0;
            r.add(i++, sum - carry * BASE);
        }

        if (carry > 0) r.add(carry);
        StringBuilder stringNumber = new StringBuilder();
        for (long num : r)
            stringNumber.append(num);
        Num output = new Num(stringNumber.reverse().toString());
        output.sign = a.sign;

        return output;

    }

    //function to compare which input is bigger in the add function
    public static Num addAny(Num a, Num b) {
        if (a.number.size() >= b.number.size()) return addBig(a, b);
        return addBig(b, a);
    }

    /**
     * Add two Num values
     * function which is called by the driver code
     * @param a First Num Input
     * @param b Second Num Input
     * @return Addition of two Num values
     */
    public static Num add(Num a, Num b) {
        //checks for the sign of the input values
        if (a.sign != b.sign) return subtractPosetive(a, b);
        return addAny(b, a);
    }


    /**
     * Subtract one Num from another where absolute value of first input is greater than second
     * helper function which is called by the subtract function
     * @param a First Num Input
     * @param b Second Num Input
     * @return Subtraction of two Num values
     */
    public static Num subtractAny(Num a, Num b) {
        int a_l = a.number.size();
        int b_l = b.number.size();
        ArrayList<Long> r = new ArrayList<>(a_l);
        long borrow = 0;
        long BASE = a.base;
        int i = 0;
        long difference = 0;
        for (i = 0; i < b_l; i++) {
            difference = a.number.get(i) - borrow - b.number.get(i);
            if (difference < 0) {
                difference += BASE;
                borrow = 1;
            } else borrow = 0;
            r.add(i, difference);
        }
        for (i = b_l; i < a_l; i++) {
            difference = a.number.get(i) - borrow;
            if (difference < 0) difference += BASE;
            else {
                r.add(i++, difference);
                break;
            }
            r.add(i, difference);
        }
        for (; i < a_l; i++) {
            r.add(i, a.number.get(i));
        }
        trim(r);
        StringBuilder stringNumber = new StringBuilder();
        for (long num : r)
            stringNumber.append(num);
        Num output_Subtract = new Num(stringNumber.reverse().toString());
        output_Subtract.sign = a.sign;

        return output_Subtract;
    }


    public static Num subtract(Num a, Num b) {
        if(a.sign != b.sign){
            b.sign = !b.sign;
            Num value = add(a,b);
            return value;
        }
        if(a.sign == true && b.sign == true){
             b.sign = false;
           add(a,b);
        }
             return subtractPosetive(a, b);

    }
    /**
     * Subtract one Num from another
     * function which is called by the driver code
     * @param a First Num Input
     * @param b Second Num Input
     * @return Subtraction of two Num values
     */
    public static Num subtractPosetive(Num a, Num b) {
        Num value;
        if (compareAbs(a, b) >= 0) {
            value = subtractAny(a, b);
            value.sign = a.sign;
        } else {
            value = subtractAny(b, a);
            b.sign = !b.sign;
            value.sign = b.sign;
        }
        return value;
    }


    /**
     * helper function to product function
     * O(N^2) implementation of product for two Num
     * (where length of these inputs will be smaller than 30)
     * @param x First Num input
     * @param y Second Num input
     * @return  product of two Num values
     */
    public static Num multiplyLong(Num x, Num y) {
        int a_l = x.number.size();
        int b_l = y.number.size();
        int l = a_l + b_l;
        long[] r = new long[l];
        Object[] a = x.number.toArray();
        Object[] b = y.number.toArray();
        long BASE = x.base;
        int i;
        long a_i, b_j, product;
        for (i = 0; i < a_l; ++i) {
            a_i = (long) a[i];
            for (int j = 0; j < b_l; ++j) {
                b_j = (long) b[j];
                product = a_i * b_j + r[i + j];
                int carry = (int) Math.floor(product / BASE);
                r[i + j] = product - carry * BASE;
                r[i + j + 1] += carry;
            }
        }
        StringBuilder stringNumber = new StringBuilder();
        for (long num : r) {
            stringNumber.append(num);
        }
        Num returnNum = new Num(stringNumber.reverse().toString());
        trimmer(returnNum);
        if (x.sign || y.sign) returnNum.sign = false;
        if (x.sign != y.sign) returnNum.sign = true;
        return returnNum;
    }


    /**
     * helper function to product function
     * O(N^log3) implementation of product for two Num OR Karatsuba's algorithm implementation
     * (for inputs having length more than 30)
     * @param x First Num input
     * @param y Second Num input
     * @return  product of two Num values
     */
    public static Num Karatsuba(Num x, Num y) {
        int n = Math.min(x.number.size(), y.number.size());

        if (n <= 30) return multiplyLong(x, y);

        n = (int) Math.ceil(n / 2);

        Num b = new Num(new ArrayList<>(x.number.subList(n, x.number.size())));
        Num a = new Num(new ArrayList<>(x.number.subList(0, n)));
        Num d = new Num(new ArrayList<>(y.number.subList(n, x.number.size())));
        Num c = new Num(new ArrayList<>(y.number.subList(0, n)));


        Num ac = Karatsuba(a, c);
        Num bd = Karatsuba(b, d);
        Num abcd = Karatsuba(add(a, b), add(c, d));

        Num product = add(add(ac, shiftLeft(subtract(subtract(abcd, ac), bd), n)), shiftLeft(bd, 2 * n));
        trimmer(product);
        return product;
    }

    /**
     * function to find the product of two Num values
     * function called by driver code
     * @param a
     * @param b
     * @return Product of two Num values
     */
    public static Num product(Num a, Num b) {

        if (b.number.size() == 1 && b.number.get(0) == 0) return new Num(0);
        if (b.number.size() == 1 && b.number.get(0) == 1) return a;
        if (b.number.size() == 1 && b.number.get(0) == -1) {
            a.sign = true;
            return a;
        }
        return Karatsuba(a, b);
    }


    /**
     * recursive function to find the power of first Input raised to second Input
     * @param a Num Input
     * @param n Long Input
     * @return  power of first Input raised to second Input (a^n)
     */

    public static Num power(Num a, long n) {
        if (n == 0) return new Num(1);
        else if (n == 1) return a;
        else {
            Num s = power(a, n / 2);
            if (n % 2 == 0)
                return product(s, s);
            else
                return product(product(s, s), new Num(n));
        }
    }


    /**
     * helper (recursive) function to find the power of first Input raised to second Input (Num^Num)
     * @param a Num Input
     * @param n Num Input
     * @return  power of first Input raised to second Input (a^n)
     */
    public static Num powerHelper(Num a, Num n) throws Exception {
        if (compareAbs(n, new Num(0)) == 0) return new Num(1);
        else if (compareAbs(n, new Num(1)) == 0) return a;
        else {
            Num s = powerHelper(a, divideByTwo(n));
            if (compareAbs(mod(n, new Num(2)), new Num(0)) == 0)
                return product(s, s);
            else
                return product(product(s, s), n);
        }
    }


    /**
     * function to find the power of first Input raised to second Input
     * function called by the drive code
     * @param a Num Input
     * @param n Num Input
     * @return  power of first Input raised to second Input (a^n)
     */
    public static Num power(Num a, Num n) throws Exception {
        
        if (n.sign) throw new Exception("Power value can not be negative");
        
        Num zero = new Num(0);
        
        if (compareAbs(a, zero) == 0) return a;
        if (compareAbs(n, zero) == 0) return new Num(1);
        boolean sign = a.sign;
        
        Num test = mod(n, new Num(2));
        if (compareAbs(test, zero) == 0) {
            sign = false;
        }
        Num output = powerHelper(a, n);
        output.sign = sign;
        return output;

    }

    /**
     * function to find  division of one Num divided by other Num (Binary search method)
     * @param a First Num input
     * @param b Second Num input
     * @return a/b
     * @throws Exception
     */
    public static Num divide(Num a, Num b) throws Exception {

        if (compareAbs(b, new Num(0)) == 0) throw new Exception("Divided by 0 not defined");
        Num low = new Num(0);
        Boolean sign = a.sign == b.sign ? false : true;
        if(a.sign ) a.sign =false;
        Num high = a;

        while (true) {

            Num sum = add(high, low);
            trimmer(sum);
            Num mid = divideByTwo(sum);
            trimmer(mid);
            mid.sign = a.sign;
            Num mid_Num_1 = add(mid, new Num(1));
            Num sub = product(mid, b);
            trimmer(sub);
            Num sub1 = product(mid_Num_1, b);
            trimmer(sub1);


//            trimmer(sub);
//            trimmer(sub1);

            if (compareAbs(sub, a) <= 0 && compareAbs(sub1, a) > 0) {
                mid.sign= sign;
                return mid;
            }

            if (compareAbs(sub, a) < 0)
                low = add(mid, new Num(1));
            else
                high = subtract(mid, new Num(1));
        }
    }


    /**
     * function to find square root of  a Num
     * Assume that a is non-negative.
     * @param a Num Input
     * @return sqrt(Num)
     */
    public static Num squareRoot(Num a) throws Exception {
        Num low = new Num(0);
        Num high = a;

        // Base Cases
//        System.out.println(a.sign);
        if (a.sign) throw new Exception("Negative number not allowed");

        while (true) {
            Num sum = add(high, low);
            Num mid = divideByTwo(sum);
            Num mid_Num_1 = add(mid, new Num(1));
            Num sub = product(mid, mid);
            Num sub1 = product(mid_Num_1, mid_Num_1);
            trimmer(sub);
            trimmer(sub1);
            if (compareAbs(sub, a) <= 0 && compareAbs(sub1, a) > 0) {
                trimmer(mid);
                return mid;
            }

            if (compareAbs(sub, a) < 0)
                low = add(mid, new Num(1));
            else
                high = subtract(mid, new Num(1));
        }
    }

    /**
     * function which gives mod , Assume that a is non-negative, and b > 0.
     * @param a First Num input
     * @param b Second Num input
     * @return a%b
     * @throws Exception when b == 0
     */
    public static Num mod(Num a, Num b) throws Exception {
        Num divide = divide(a, b);
        Num product = product(divide, b);
        trimmer(product);
        Num subtract = subtract(a, product);
        trimmer(subtract);
        return subtract;
    }


    /**
     * divides a Num by 2
     * @param a Input Num
     * @return  a/2
     */
    public static Num divideByTwo(Num a) {
        long remainder = 0;
        for (int i = a.number.size() - 1; i >= 0; i--) {

            Long p = (a.number.get(i) + remainder * a.base) >> 1;
            remainder = a.number.get(i) % 2;
            a.number.set(i, p);
        }
        return a;
    }

    /**
     * compares two Num
     * @param a First Num input
     * @param b Second Num input
     * @return 1 if a> b , 0 if a == b, -1 if a < b
     */
    public static int compareAbs(Num a, Num b) {
        if (a.number.size() == 1 && b.number.size() == 1 && a.number.get(0) == 0 && b.number.get(0) == 0) return 0;
        if (a.number.size() != b.number.size()) {
            return a.number.size() > b.number.size() ? 1 : -1;
        }
        for (int i = a.number.size() - 1; i >= 0; i--) {
            if (a.number.get(i) != b.number.get(i)) return a.number.get(i) > b.number.get(i) ? 1 : -1;
        }
        return 0;
    }

    //function to remove trailing zeros from ArrayList
    public static void trim(ArrayList<Long> v) {
        int i = v.size();
        while (i < v.size()) {
            if (v.get(--i) == 0)
                v.remove(i);
        }
    }


    //function to convert a Num to its String representation
    public String toString() {
        StringBuilder numberString = new StringBuilder();

        for (long digits : this.number)
            numberString.append(digits);

        numberString.reverse();
        if (this.sign) numberString.insert(0, "-");
        
        
        return numberString.toString();
    }

    //function to left shift a Num
    public static Num shiftLeft(Num x, int n) {
        List<Long> r = new ArrayList<>();
        while (n-- > 0) r.add((long) 0);
        r.addAll(x.number);
        StringBuilder stringNumber = new StringBuilder();
        for (long num : r)
            stringNumber.append(num);
        return new Num(stringNumber.reverse().toString());
    }

    /**
     * function to remove trailing zeros from a Num
     * @param a Num value
     */
    public static void trimmer(Num a) {
        StringBuilder strBuilder = new StringBuilder();
        int i = a.number.size();
        while (--i != 0 && a.number.get(i) == 0) {
            a.number.remove(i);
        }
        for (int j = i; j >= 0; j--)
            strBuilder.append(a.number.get(j));
    }

    /**
     * function to print a Num value as it is stored in Num , Here in Small endian form
     */
    public void printList() {
        System.out.print(defaultBase + ":");
        for (long num : number) {
            System.out.print("   " + num);
        }
        System.out.println();

    }

    public static void main(String args[]) throws Exception {
        Num num = new Num("-2");
//        System.out.println(num.sign);
        Num num1 = new Num(-3);
//        Num outAdd = add(num, num1);
        Num outSub = subtract(num, num1);
//        Num outProduct = product(num, num1);
//        Num outPower = power(num, 8);
//        Num outDivide = divide(num, num1);
//        Num outSquareRoot = squareRoot(num);
//        Num outModulus = mod(num, num1);
//        Num outPowerNum = power(num, num1);
//        System.out.println("Add : " + outAdd.toString());
        System.out.println("Subtract : " + outSub.toString());
//        System.out.println("Product : " + outProduct.toString());
//        System.out.println("Power : " + outPower.toString());
//        System.out.println("Divide :" + outDivide.toString());
//        System.out.println("Square root: " + outSquareRoot.toString());
//        System.out.println("Modulus : " + outModulus.toString());
//        System.out.println("Power of Num : " + outPowerNum.toString());
//        outAdd.printList();
    }
}

