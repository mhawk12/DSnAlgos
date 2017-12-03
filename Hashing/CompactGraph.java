package cs6301.g29;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by
 * Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import cs6301.g00.Timer;

public class CompactGraph {

    private Graph g;
    /**
     * each of the vertices will have a map which will contain all the outgoing vertices(from source to destination in a edge)
     * mapped to minimum weight of all the parallel edges.
     */
    private HashMap<Graph.Vertex, HashMap<Graph.Vertex, Integer>> vertexEdgeMap;

    //constructor
    public CompactGraph(Graph graph) {
        this.g = graph;
        this.vertexEdgeMap = new HashMap<>();
    }

    /**
     * function to map minimum of all parallel edges weights from a source vertex (to the target vertex), to target vertex
     */
    void initializ() {
        for (Graph.Vertex u : g) {
            if (!vertexEdgeMap.containsKey(u))
                vertexEdgeMap.put(u, new HashMap<>());

            //map of minimum weight edge to destination vertex(from u)
            HashMap<Graph.Vertex, Integer> edgeWeights = vertexEdgeMap.get(u);
            for (Graph.Edge e : u) {
                     if (!edgeWeights.containsKey(e.to))
                        edgeWeights.put(e.to, e.weight);
                /**
                 * if the edge weight mapped earlier to the destination vertex from u
                 * is greater than the current edge weight then change the mapped
                 * edge weight to current one
                 */
                else if (edgeWeights.get(e.to) > e.weight)
                        edgeWeights.put(e.to, e.weight);

            }
        }
    }


    void removeParallelEdges() {
        for (Graph.Vertex u : g) {
            Iterator<Graph.Edge> it = u.iterator();
            //gets the mapping of destination vertex (from u) to minimum weight edges
            HashMap<Graph.Vertex, Integer> minWeights = vertexEdgeMap.get(u);

            while (it.hasNext()) {
                 Graph.Edge edge = it.next();
                /**
                 * if the weight of current edge is greater than one in map corresponding to destination
                 * vertex (e.to) then remove that edge
                 */
                if (edge.weight > minWeights.get(edge.to))
                    it.remove();
            }
        }
    }


    static void compactGraph(Graph g) {
        CompactGraph compactGraph = new CompactGraph(g);
        compactGraph.initializ();
        compactGraph.removeParallelEdges();

    }

    //prints all the edges
    static void printGraph(Graph g) {
        for (Graph.Vertex u : g)
            for (Graph.Edge e : u)
                System.out.println(e.from + " " + e.to + " " + e.weight);
    }

    
    public static void main(String args[]) throws FileNotFoundException {
		Scanner in;

		if (args.length > 0)
			in = new Scanner(new File(args[0]));
		else
			in = new Scanner(System.in);

		Graph graph = Graph.readGraph(in, true);
		Timer timer = new Timer();
		timer.start();
	    CompactGraph.compactGraph(graph);
		timer.end();
		System.out.println("Time taken to run the algorithm:" + "  " + timer);
		
		System.out.println("Printing the edges after removing all the parallel edges :");
		CompactGraph.printGraph(graph);

		if (in != null)
			in.close();

	}

}
