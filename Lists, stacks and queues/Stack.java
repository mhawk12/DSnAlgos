/**
 * Created by Nishant Shekhar (nxs167130)
 *            Anusha Agasthi  (nxa162430)
 *            Prince Patel    (pap160930)
 */

package cs6301.g29;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Stack<T> {

    //properties
    private Object[] stack;
    private int top, size, len;

    //Contructor
    public Stack(int n) {
        size = n;
        stack = new Object[size];
        top = -1;
        len = 0;
    }

    /**
     * add an element to the end of the Stack
     * @param nextElement next element to be added
     */
    public void push(T nextElement) {

        if(top + 1 >= size)
            throw new IndexOutOfBoundsException("Overflow Exception");
        if(top + 1 < size )
            stack[++top] = nextElement;
        len++ ;

    }

    /**
     * function which tells wether Stack is empty or not
     * @return true if empty , false if not
     */

    public boolean isEmpty()
    {
        return top == -1;
    }

    //removes the last element from the stack and returns it
    public T pop()
    {
        if( isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        len-- ;
        return (T) stack[top--];
    }


    // return the last element from the stack
    public T peek()
    {
        if( isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        return (T)stack[top];
    }

    // print all the stack elements
    public void printStack()
    {
        System.out.print("\nStack = ");
        if (len == 0)
        {
            System.out.print("Empty\n");
            return ;
        }
        for (int i = top; i >= 0; i--)
            System.out.print(stack[i]+" ");
        System.out.println();
    }

   

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // creating object of class arrayQueue
        //Enter the size of the Stack
        int n = scan.nextInt();
        Stack<Integer> q = new Stack<>(n);
        // Perform Stack Operations
        char ch;
        do{
            System.out.println("\nStack Operations");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Check empty");
            System.out.println("5. Print Stack elements");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter integer element to insert");
                    q.push(scan.nextInt());
                    break;
                case 2:
                    System.out.println("Pop Element = " + q.pop());
                    break;
                case 3:
                    System.out.println("Peek Element = " + q.peek());
                    break;
                case 4:
                    System.out.println("Empty status = " + q.isEmpty());
                    break;
                case 5:
                    System.out.println("Stack Elements = ");
                    q.printStack();
                    break;
                default:
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            System.out.println("All Stack elements ");
            q.printStack();
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');
    }
}