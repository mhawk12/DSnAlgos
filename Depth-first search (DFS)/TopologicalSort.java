
//TOPOLOGICAL SORTING USING DFS
/**
 * Created by Nishant Shekhar (nxs167130)
 *            Anusha Agasthi  (nxa162430)
 *            Prince Patel    (pap160930)
 */

package cs6301.g29;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class TopologicalSort extends GraphAlgorithm<TopologicalSort.DFSVertex> {
    public static final int INFINITY = Integer.MAX_VALUE;
    // Class to store information about a vertex in this algorithm
    static class DFSVertex {
	boolean seen;
    boolean recStack;
	int indegree;
	DFSVertex(Graph.Vertex u) {
	    seen = false;
	    indegree = 0;
        recStack = false;
	    
	}
    }

    Graph.Vertex src;

    
    public TopologicalSort(Graph g, Graph.Vertex src) {
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
    boolean dfs(Graph.Vertex newsrc) {
    	if(!seen(newsrc)) {
    	visit(newsrc);
	    for(Graph.Edge e: newsrc) {
		Graph.Vertex v = e.otherEnd(newsrc);
		if(!seen(v) && dfs(v)) 
    		 return true;
    		else if(onStack(v))
    			return true;
		  
	    }
	 }
	    
	    removeFromStack(newsrc);
	    return false;
    }
    
    
    void dfs(Graph.Vertex newsrc, Stack<Graph.Vertex> stack) {
    	visit(newsrc);
	    for(Graph.Edge e: newsrc) {
		Graph.Vertex v = e.otherEnd(newsrc);
		if(!seen(v)) {
//			visit(v);
    		dfs(v, stack);	
		  }
	    }
	    stack.push(newsrc);
    }
    
    

    /**
     * function to Topologically sort the given graph using DFS
     */
    void topologicalSortDFS()
    {
        Stack<Graph.Vertex> stack = new Stack<>();
        
        // reinitializing the DFSVertex 

        if(!isDAG(g)) {
        	System.out.println("Not a DAG");
        	return;
        }
        
      for(Graph.Vertex u: g) {
	    node[u.getName()] = new DFSVertex(u);
	}
    
        //loop to visit all the vertices , i.e. for the vertices which are not strongly connected
        for (int i = 1; i <= g.size(); i++)
            if (seen(g.getVertex(i)) == false)
            	dfs(g.getVertex(i), stack);
            
 
        // Print contents of stack
        System.out.print("Finish Order : ");
        while (stack.empty()==false)
            System.out.print((stack.pop()).name+1 + "--> ");
        
        System.out.println();
    }
     
    /**
     * function to Topologically sort the given graph using INDEGREE method
     */
    void topologicalSortINDEGREE()
    {
    	
    	 
        if(!isDAG(g)) {
        	System.out.println("Not a DAG");
        	return;
        }
        
        
    	indegree();
    	
    	 //reinitializing the DFSVertex 
//        for(Graph.Vertex u: g) {
//    	    node[u.getName()] = new DFSVertex(u);
//    	}
        
    	//a queue to add all the vertices to a queue whose indegree is 0
    	Queue<Graph.Vertex> q = new LinkedList<Graph.Vertex>();
        for(int i = 0 ; i< g.size() ;  i++) {
        	if(node[i].indegree == 0) {
        	q.add(g.getVertex(i+1));	
        	}
        }
        
        int cnt = 0;
        
        /**
         * Create a vector to store result (A topologicalordering of the vertices)
         */
         
        Vector <Graph.Vertex> topOrder=new Vector<Graph.Vertex>();
        while(!q.isEmpty())
        {
            /**
             *  Extract front of queue (or perform dequeue) 
             *  and add it to topological order
             */
            // 
        	Graph.Vertex u=q.poll();
            topOrder.add(u);
             
           /**
            *   Iterate through all its neighboring nodes
            *   of dequeued node u and decrease their in-degree 
            *   by 1
            */
            for(Graph.Edge e: u) {
        		Graph.Vertex v = e.otherEnd(u);
                // If in-degree becomes zero, add it to queue
        		DFSVertex bv = getVertex(v);
                if(--bv.indegree == 0)
                    q.add(v);
            }
            cnt++;
        }
        
        // Check if there was a cycle       
        if(cnt != g.size())
        {
            System.out.println("Not a DAG");
            return ;
        }

        // Print contents of stack
        System.out.print("Finish Order : ");      
        for(Graph.Vertex i : topOrder)
        {
            System.out.print(i.name+1 +"--> ");
        }
        System.out.println();
    }
    

    //function to check whether a vertex has been visited earlier  or not
    boolean seen(Graph.Vertex u) {
	return getVertex(u).seen;
    }
    
    //function to check whether a vertex is on stack or not earlier  or not
    boolean onStack(Graph.Vertex u) {
	return getVertex(u).recStack;
    }
    
    // To calculate the indegree of all the vertices
    void indegree() {   
    	for(Graph.Vertex u: g) {
    		for(Graph.Edge e : u) {
    		DFSVertex bv = getVertex(e.otherEnd(u));
    	    bv.indegree++;
    	}
    	}
	}
   
    
   void  removeFromStack(Graph.Vertex u){
    	DFSVertex bv = getVertex(u);
    	bv.recStack = false;
    }
    
  // Visit a node v from u
    void visit(Graph.Vertex u) {
    DFSVertex bv = getVertex(u);
	bv.seen = true;
	bv.recStack = true;
    }

    boolean isDAG(Graph g) {
    	if(isCyclic(g)) return false;
    	else return true;
    }
    boolean isCyclic(Graph g) {
    	for(Graph.Vertex u : g)
    		if (dfs(u))
    			return true;
            	
     return false;	
    }
    
    public static void main(String args[]) {
    	Scanner in = new Scanner(System.in);
    	Graph graph = Graph.readDirectedGraph(in);
    	TopologicalSort topologicalSort = new TopologicalSort(graph, graph.getVertex(1));
    	
    	
    	//To see wether the given graph is a DAG or not
//    	System.out.println("The graph is a DAG :"  + topologicalSort.isDAG(graph)); 
    	
    	
    	//InDegree Topological ordering
//    	topologicalSort.topologicalSortINDEGREE();
    	
    	
        //DFS Topological ordering
//    	topologicalSort.topologicalSortDFS();
    	
    	
    	
    }
}