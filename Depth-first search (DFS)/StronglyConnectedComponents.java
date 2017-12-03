//Strongly Connected Components

/**
 * Created by Nishant Shekhar (nxs167130)
 *            Anusha Agasthi  (nxa162430)
 *            Prince Patel    (pap160930)
 */

package cs6301.g29;

import java.util.Scanner;
import java.util.Stack;

//import g00.TopologicalSort.DFSVertex;

import java.util.Iterator;

public class StronglyConnectedComponents extends GraphAlgorithm<StronglyConnectedComponents.DFSVertex> {
    public static final int INFINITY = Integer.MAX_VALUE;
    // Class to store information about a vertex in this algorithm
    static class DFSVertex {
	boolean seen;
	int indegree;
	int outdegree;
	DFSVertex(Graph.Vertex u) {
	    seen = false;
	    indegree =0;
	    outdegree=0;
	}
    }

    Graph.Vertex src;

    
    public StronglyConnectedComponents(Graph g, Graph.Vertex src) {
	super(g);
	this.src = src;
	node = new DFSVertex[g.size()];
   // Create array for storing vertex properties
	for(Graph.Vertex u: g) {
	    node[u.getName()] = new DFSVertex(u);
	}
    }
    
    
   /**
    * Depth first function to visit all the vertices
    * @param newsrc source vertex
    * @param stack Stack to store vertices in order we visit them
    */

    void dfs(Graph.Vertex newsrc, Stack<Graph.Vertex> stack) {
    	visit(newsrc);
	    for(Graph.Edge e: newsrc) {
		Graph.Vertex v = e.otherEnd(newsrc);
		if(!seen(v)) {
    		dfs(v, stack);	
		  }
	    }
	    stack.push(newsrc);
    }
    
    
    /**
     * function to perform DFS on reverse graph or transpose of orriginal graph
     * @param newsrc
     */
    void revdfs(Graph.Vertex newsrc) {
    	visit(newsrc);
    	
    	System.out.print(newsrc.name + "-->");
    	//calling the reverse iterator of Graph class 
    	Iterator<Graph.Edge> iterator = newsrc.reverseIterator();
    	while(iterator.hasNext()) {
    	Graph.Edge edge = iterator.next();
    	Graph.Vertex v = edge.otherEnd(newsrc);
		if(!seen(v)) {
			revdfs(v);	
		  }
	    }
    }

   
 // The main function that finds and prints all strongly
    // connected components
    int  SCCs()
    {
        Stack<Graph.Vertex> stack = new Stack<>();
        int countOfConnectedCommponents = 0;
//      Topologically storing the vertex  
        for (Graph.Vertex u: g )
            if (!seen(u))
            	dfs(u, stack);
 
        
//      Mark all the vertices as not visited (For second DFS)
        for (Graph.Vertex u: g ) {
        	DFSVertex bv = getVertex(u);
        	bv.seen = false;
        }
        
        // Now process all vertices in order defined by Stack
        while (stack.empty() == false)
        {
            Graph.Vertex v = stack.pop();
            if (!seen(v))
            {
            	revdfs(v);
            	countOfConnectedCommponents++;
                System.out.println();
            }
        }
        return countOfConnectedCommponents;
    }

    
    /**
     * function to test wether the given graph is Eulerian or not
     * @param g input graph
     * @return true or false , depending upon whether the graph is Eulerian or not
     */
    boolean testEulerian(Graph g) {
            	
    	//if graph is not just a single strongly connected one then it can not be Eulerian
    	if(SCCs() != 1) return false;
    	
    	//calculate the indegrees and outdegrees
    	degrees();
    	
    	for(Graph.Vertex u: g) {
    		DFSVertex bv = getVertex(u);
    		if (bv.indegree != bv.outdegree) return false;
    	}
    	
    	return true;
    	
     }
   

    //function to check whether a vertex has been visited earlier  or not
    boolean seen(Graph.Vertex u) {
	return getVertex(u).seen;
    }
    
    public void degrees() {   
    	for(Graph.Vertex u: g) {
    		for(Graph.Edge e : u) {
    		DFSVertex bv = getVertex(e.otherEnd(u));
    	    bv.indegree++;
    	    DFSVertex bu = getVertex(u);
    	    bu.outdegree++;
    	}
    	}
	}
    
       
    // Visit a node v from u
    void visit(Graph.Vertex u) {
    DFSVertex bv = getVertex(u);
	bv.seen = true;
    }
    
    
    public static void main(String args[]) {
    	Scanner in = new Scanner(System.in);
    	Graph graph = Graph.readDirectedGraph(in);
    	StronglyConnectedComponents stronglyConnectedComponents = new StronglyConnectedComponents(graph, graph.getVertex(1));
    	System.out.println("No of connected components  :" + stronglyConnectedComponents.SCCs());
    	System.out.println("The graph is Eulerian  : " + stronglyConnectedComponents.testEulerian(graph));  	
    }
}