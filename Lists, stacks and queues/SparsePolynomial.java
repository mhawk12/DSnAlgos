package cs6301.g29;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Nishant Shekhar (nxs167130)
 *            Anusha Agasthi  (nxa162430)
 *            Prince Patel    (pap160930)
 */

public class SparsePolynomial {

    static String exponent ;
    OneTerm head;
    OneTerm current;

    //Constructor
    SparsePolynomial(){
        exponent = " ";
        head = null;

    }

    /**
     * Adds element to the list
     * @param c coefficient
     * @param e exponent
     */
    public void createList(int c,int e){
        head=new OneTerm(c,e,head);
    }

    //Class which will have exponent value and coefficient
    public static class OneTerm{
        int coef;
        int exp;
        OneTerm next;

        OneTerm(int c,int e,OneTerm n){
            coef=c;
            exp=e;
            next=n;
        }
    }


    /**
     * function to add two polynomial
     * @param list1  first list to be added
     * @param list2  second list to be added
     * @return
     */
    public static SparsePolynomial add(SparsePolynomial list1,SparsePolynomial list2){
        SparsePolynomial addList=new SparsePolynomial();

        OneTerm temp1=list1.head;
        OneTerm temp3=temp1;
        OneTerm temp2=list2.head;
        OneTerm temp4=temp2;

        /**
         * compare each exponent of one list to another and whenever they are same
         * add their coefficient and form a new element and add to the output list
         */
        while(temp1.next!=null){
            while(temp2.next!=null){
                if(temp1.exp==temp2.exp){

                    addList.createList((temp1.coef+temp2.coef),temp1.exp);
                    exponent+=temp1.exp;

                }
                temp2=temp2.next;
            }
            temp1=temp1.next;
            temp2=temp4;
            addList.print();
        }
        String[] array=exponent.split("");


        /**
         * All the terms in first list that have different exponent from second list's terms
         * are added separately to the output list
         */
        while(temp3!=null){
            boolean exponentPresent = false;
            for(int i=1;i<array.length;i++){
                if(temp3.exp==Integer.parseInt(array[i])){
                    exponentPresent = true;
                }
            }
            if (!exponentPresent) {
                addList.createList(temp3.coef,temp3.exp);
            }
            temp3=temp3.next;
        }


        /**
         * All the terms in second list that have different exponent from first list's terms
         * are added separately to the output list
         */
        while(temp4!=null){
            boolean exponentPresent = false;
            for(int i=1;i<array.length;i++){
                if(temp4.exp==Integer.parseInt(array[i])){
                    exponentPresent = true;
                }
            }
            if (!exponentPresent) {
                addList.createList(temp4.coef,temp4.exp);
            }
            temp4=temp4.next;
        }


        return addList;
    }


    //using Horner's rule to evaluate the polynomial
    public int evaluate(int x) {
        int evaluatedValue = 0;
        current = head;
        evaluatedValue = current.coef + (x * evaluatedValue);
        while (current.next != null) {
            current = current.next;
            evaluatedValue = current.coef + (x * evaluatedValue);
        }
        return evaluatedValue;
    }


    /**
     * Quadratic time function to multiply two polynomials
     * @param polynomialToBeMultiplied Second polynomial that needs to be multiplied to the first one
     * @return returns a HasMap with key as
     */

    public HashMap times(SparsePolynomial polynomialToBeMultiplied) {
        OneTerm temp1 = head;
        OneTerm temp2 = polynomialToBeMultiplied.head;
        OneTerm temp4= temp2;
        HashMap<Integer,Integer> tempOneTerm = new HashMap<>();
        tempOneTerm.put(temp1.exp + temp2.exp, temp1.coef * temp2.coef);

        /**
         * for evey term in both the list add the exponents and make it the key
         * and coefficient of first times coefficient of second . This way we can
         * retrieve coefficients and exponents.
         */

        while (temp1 != null) {
            while (temp2 != null) {
                   if(!tempOneTerm.containsKey(temp1.exp+temp2.exp)) {
                   tempOneTerm.put(temp1.exp + temp2.exp, temp1.coef * temp2.coef);
                }
                 else {
                    tempOneTerm.put(temp1.exp + temp2.exp,
                            tempOneTerm.get(temp1.exp + temp2.exp) + (temp1.coef * temp2.coef));
                }
                temp2 = temp2.next;
            }
            temp1 = temp1.next;
            temp2=temp4;
        }
        return tempOneTerm;
    }

    //prints the list
       public void print() {
        current = head;
        System.out.print(current.coef + "x^" + current.exp);
        while (current.next != null) {
            current = current.next;
            System.out.print(" + " + current.coef + "x^" + current.exp);
        }
        System.out.println();
    }

    public static void main (String args[]){
        SparsePolynomial l1=new SparsePolynomial();
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Integer> times= new HashMap<>();
        l1.createList(3,2);
        l1.createList(5,1);
        l1.createList(3,0);
        SparsePolynomial l2=new SparsePolynomial();
        l2.createList(4,3);
        l2.createList(5,1);
        l2.createList(2,0);
        SparsePolynomial l3=add(l1,l2);
        l3.print();

        System.out.println("Enter the value at which the polynomial needs to be evaluated");
        int value = scanner.nextInt();
        System.out.print("Output of the evaluation= ");
        System.out.println(l1.evaluate(value));



        //multiplication of two lists
        System.out.println("multiplication of first polynomial with second");
        times = l1.times(l2);
        for (Integer exponent: times.keySet()){

            //String key = name.toString();
            int coefficient  = times.get(exponent);
            System.out.print(coefficient + "x^" + exponent + " + ");


        }
        System.out.print(0);
    }
}

