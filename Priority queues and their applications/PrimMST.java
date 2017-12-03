/**
* Created by
*  Nishant Shekhar (nxs167130)
*  Anusha Agasthi  (nxa162430)
*  Prince Patel    (pap160930)
*/
package cs6301.g29;
import cs6301.g00.Timer;
import cs6301.g29.Graph.Vertex;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class PrimMST {
    static final int Infinity = Integer.MAX_VALUE;
    Graph  g;
    public PrimMST(Graph g) {
      this.g = g;
    }

    // SP6.Q4: Prim's algorithm using PriorityQueue<Edge>:
    /**
     * Prim algorithm using Edges
     * @param s source vertex
     * @return MST weight
     */
    public int prim1(Graph.Vertex s) {
        int wmst = 0;
        PriorityQueue<Graph.Edge> pq = new PriorityQueue<>();
        s.seen = true;

        for(Graph.Edge u : s)
            pq.add(u);
        
        
        while (!pq.isEmpty()) {
            Graph.Edge e = pq.remove();
            Graph.Vertex u = e.from;
            Graph.Vertex v = e.otherEnd(u);
            if (v.seen) continue;
            v.seen = true;
            v.parent = u;
            wmst = wmst + e.weight;
            for (Graph.Edge e2 : v) {
                if (!e2.otherEnd(v).seen)
                    pq.add(e2);
            }
            
        }
        return wmst;
    }
    
    // SP6.Q6: Prim's algorithm using IndexedHeap<PrimVertex>:
    /**
     * Prim algorithm using Vertices
     * @param s source vertex
     * @return MST weight
     */
    public int prim2(Graph.Vertex s) {
        int wmst = 0;
        s.d = 0;
        
        //Comparator to be passed to the Indexed Heap constructor
        Comparator<Graph.Vertex> comparator = new Comparator<Graph.Vertex>() {
            @Override
            public int compare(Vertex u, Vertex v) {
    			if(u.d < v.d) return -1;
    			else if(u.d == v.d) return 0;
    			else return 1;
    		}
        };
        
       
        //Creating and Indexed Heap based on Vertex d values
        IndexedHeap<Graph.Vertex> pq = new IndexedHeap<Graph.Vertex>(g.v, comparator, g.v.length);
        //Building the heap
        pq.buildHeap();              
        while(!pq.isEmpty()) {
        	Graph.Vertex u = pq.remove();
        	u.seen = true;
        	wmst += u.d;
        	for(Graph.Edge e : u) {
        		Graph.Vertex v =  e.otherEnd(u);
        		if(!v.seen && e.weight < v.d) {
        			v.d = e.weight;
        			v.parent = u;
        			pq.percolateUp(v.getIndex());
        		}
        	}
        }
        return wmst;
    }

    public static void main(String[] args) throws FileNotFoundException {
	    Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }

	Graph g = Graph.readGraph(in);
    Graph.Vertex s = g.getVertex(1);
	Timer timer = new Timer();
	PrimMST mst = new PrimMST(g);
	int wmst = mst.prim1(s);
	timer.end();
        System.out.println(wmst);
    }
}




