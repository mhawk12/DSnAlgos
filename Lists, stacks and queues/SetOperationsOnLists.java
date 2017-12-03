/**
 * Created by Nishant Shekhar (nxs167130)
 *            Anusha Agasthi  (nxa162430)
 *            Prince Patel    (pap160930)
 */

package cs6301.g29;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SetOperationsOnLists<T> {

    /**
     * function to return next element of an list if it exits or null if not
     * @param iterator Iterable List
     * @param <T>  Data Type
     * @return T or null
     */
    public static  <T extends Comparable<? super T>> T  next(Iterator<T> iterator){
             return iterator.hasNext() ? iterator.next() : null;
    }


    /**
     * function to return union of two given sorted linked list
     * @param l1 First SinglyLinkedList list
     * @param l2 Second SinglyLinkedList list
     * @param outList  The result of union of two given lists which will also be a SinglyLinkedList list
     * @param <T>  Data Type
     * @return outList
     */

    public static <T extends Comparable<? super T>> SinglyLinkedList<T> union(SinglyLinkedList<T> l1,
                                                                              SinglyLinkedList<T> l2, SinglyLinkedList<T> outList)
    {

        Iterator<T> iterator1 = l1.iterator();
        Iterator<T> iterator2 = l2.iterator();
        T x1 = next(iterator1);
        T x2 = next(iterator2);
        while(x1 != null && x2 != null){

            if(x1.compareTo(x2) < 0) {
                outList.add(x1);
                x1 = next(iterator1);
            }
            else if(x1.compareTo(x2) > 0) {
                outList.add(x2);
                x2 = next(iterator2);
            }
            else{
                outList.add(x1);
                x1 = next(iterator1);
                x2 = next(iterator2);
            }
        }

        return outList;
    }


    /**
     * function to return intersection of two given sorted linked list
     * @param l1 First SinglyLinkedList list
     * @param l2 Second SinglyLinkedList list
     * @param outList  The result of intersection of two given lists which will also be a SinglyLinkedList list
     * @param <T>  Data Type
     * @return outList
     */

    public static <T extends Comparable<? super T>> SinglyLinkedList<T> intersect(SinglyLinkedList<T> l1,
                                                                                  SinglyLinkedList<T> l2, SinglyLinkedList<T> outList)
    {

        Iterator<T> iterator1 = l1.iterator();
        Iterator<T> iterator2 = l2.iterator();
        T x1 = next(iterator1);
        T x2 = next(iterator2);

         while(x1 != null && x2 != null ){


            if(x1.compareTo(x2) < 0)
                x1 = next(iterator1);

            else if(x1.compareTo(x2) > 0)
                x2 = next(iterator2);

            else{
                outList.add(x1);
                x1 = next(iterator1);
                x2 = next(iterator2);
            }
        }

        return outList;
    }



    /**
     * function to return difference of two given sorted linked list
     * @param l1 First SinglyLinkedList list
     * @param l2 Second SinglyLinkedList list
     * @param outList  The result of difference of two given lists which will also be a SinglyLinkedList list
     * @param <T>  Data Type
     * @return outList
     */
    public static <T extends Comparable<? super T>> SinglyLinkedList<T> difference(SinglyLinkedList<T> l1,
                                                                              SinglyLinkedList<T> l2, SinglyLinkedList<T> outList) {

        Iterator<T> iterator1 = l1.iterator();
        Iterator<T> iterator2 = l2.iterator();
        T x1 = iterator1.next();
        T x2 = iterator2.next();
        while (x1 != null && x2 != null) {

            if (x1.compareTo(x2) < 0) {
                outList.add(x1);
                x1 = next(iterator1);
            } else if (x1.compareTo(x2) > 0) {
                outList.add(x1);
                x2 = next(iterator2);
            } else {
               // outList.add(x1);
                x1 = next(iterator1);
                x2 = next(iterator2);
            }
        }
        //System.out.print(outList.toString());
        return outList;
    }

    public static void main(String[] args) throws NoSuchElementException {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }


        // First list
        SinglyLinkedList<Integer> lst1 = new SinglyLinkedList<>();
        for (int i = 1; i <= n; i++) {
            lst1.add(new Integer(i));
        }
        lst1.printList();

        // Second list
        SinglyLinkedList<Integer> lst2= new SinglyLinkedList<>();
        for (int i = 1; i <= n; i++) {
            lst2.add(new Integer(2*i));
        }
        lst2.printList();

        //prints intersection of two lists
        System.out.println("Intersection Output");
        SinglyLinkedList<Integer> intersectionList = intersect(lst1, lst2, new SinglyLinkedList<Integer>());
        intersectionList.printList();


        //prints union of two lists
        System.out.println("Union Output");
        SinglyLinkedList<Integer> unionList = union(lst1, lst2, new SinglyLinkedList<Integer>());
        unionList.printList();


        //prints difference of two lists
        System.out.println("Difference Output");
        SinglyLinkedList<Integer> differenceList = difference(lst1, lst2, new SinglyLinkedList<Integer>());
        differenceList.printList();

    }
    }
