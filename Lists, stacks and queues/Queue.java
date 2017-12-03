/**
 * Created by Nishant Shekhar (nxs167130)
 *            Anusha Agasthi  (nxa162430)
 *            Prince Patel    (pap160930)
 */

package cs6301.g29;
import java.util.Scanner;


public class Queue<T> {

    //properties
    private Object[] queue;
    private int front, rear, size, len;

    //Contructor
    public Queue() {
        size = 8;
        queue = new Object[size];
        front = -1;
        rear = -1;
        len = 0;
    }

    //get the size of the Queue
    public int getSize(){
        return size;
    }
    
    //get the no of elements in the Queue
    public int size(){
        return len;
    }


    //increase of decrease
    public void setSize(double scale){
        size = (int)scale*size;
        if(size < 16){
            System.out.println("Can not resize the size of the Queue to less than 16");
            return;
        }
        Object[] temp = queue;
        queue = new Object[size];

        for(int i = 0; i< len; i++)
            queue[i] = temp[i];


    }

    /**
     * add an element to the end of the queue
     * @param nextElement next element to be added
     * @return true if element added , false if not because of size restrictions
     */
    public boolean add(T nextElement) {

        if(nextElement == null)
            throw new NullPointerException ("Can not add null");

        if (rear == -1)
        {
            front = 0;
            rear = 0;
            queue[rear] = nextElement;
        }

        else if (rear + 1 < size) {
            queue[++rear] = nextElement;
        }

        else if (rear + 1 >= size)
            throw new IllegalStateException ("Overflow Exception");

        len++;
        return true;

    }

    /**
     * function which tells wether queue is empty or not
     * @return true if empty , false if not
     */
    public boolean isEmpty()
    {
        return front == -1;
    }

    // returns the first element of the queue
    public T peek()
    {

        if (isEmpty())
            return null;
        return (T) queue[front];
    }


    //removes first element of the queue and returns it
    public T poll()
    {
        if (isEmpty())
            throw null;
        else
        {
            len--;
            T element = (T) queue[front];
            if ( front == rear)
            {
                front = -1;
                rear = -1;

            }
            else
                front++;
            return element;
        }
    }

    //adds element to the add of the queue , returns false if can not add an element
    public boolean offer(T nextElement)
    {
        if(nextElement == null)
            throw new NullPointerException ("Can not add null");

        if (rear == -1)
        {
            front = 0;
            rear = 0;
            queue[rear] = nextElement;
        }

        else if (rear + 1 < size)
            queue[++rear] = nextElement;


        else if (rear + 1 >= size)
            return false;

        len++;
        return true;
    }

    /**
     * doubles the size of the queue if queue id over 90% full
     * or halves the size of the queue if queue is less then 25% occupied
     */

    public void resize() {
        double percentageFull = len / getSize();
        if ((percentageFull >= 0.9) && (percentageFull <= 1)) {
            setSize(2);
            System.out.println("Size of the queue doubled");
        }

        else if ((percentageFull >= 0.9) && (percentageFull <= 1)) {
            setSize(0.5);
            System.out.println("Size of the queue halved");
        }

        else
            System.out.println("Resizing not required right now");
    }

    //prints all the elements of the queue
    public void printQueue()
        {
            if (len == 0)
            {
                System.out.print("Empty\n");
                return ;
            }
            for (int i = front; i <= rear; i++)
                System.out.print(queue[i]+" ");
            System.out.println();
        }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        /* creating object of class arrayQueue */
        Queue<Integer> q = new Queue<>();
        /* Perform Queue Operations */
        char ch;
        System.out.println("Here we are adding just int values , but this code will work for any data type");
        do{
            System.out.println("\nQueue Operations");
            System.out.println("1. Insert");
            System.out.println("2. Poll");
            System.out.println("3. Peek");
            System.out.println("4. Check empty");
            System.out.println("5. Resize the Queue");
            System.out.println("6. PrintQueue");
            System.out.println("7. Get the size of the queue");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter element to insert");
                    q.add(scan.nextInt());
                    break;
                case 2:
                    System.out.println("poll Element = " + q.poll());
                    break;
                case 3:
                    System.out.println("Peek Element = " + q.peek());
                    break;
                case 4:
                    System.out.println("Empty status = " + q.isEmpty());
                    break;
                case 5:
                    System.out.println("Resizing Queue");
                    q.resize();
                    break;
                case 6:
                    System.out.println("All queue elements ");
                    q.printQueue();
                    break;
                case 7:
                    System.out.println("Fixed Size of the queue " + q.getSize());
                    break;
                default:
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            System.out.println("All queue elements ");
            q.printQueue();
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');
    }
}