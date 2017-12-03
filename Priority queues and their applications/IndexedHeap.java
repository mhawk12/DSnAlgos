/**
 * Created by 
 * Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */

package cs6301.g29;
import java.util.Comparator;

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
    /** Build a priority queue with a given array q */
    public IndexedHeap(T[] q, Comparator<T> comp, int n) {
	super(q, comp, n);
    }

    /** restore heap order property after the priority of x has decreased */
    public void decreaseKey(T x) {
	percolateUp(x.getIndex());
    }
    
    @Override
    public void move(T[] pq, int i, T X) {
    	pq[i] = X;
    	X.putIndex(i);
    }
    
    
   
}
