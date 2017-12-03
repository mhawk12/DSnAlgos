/**
 * Created by 
 * Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */

package cs6301.g29;

import cs6301.g00.Timer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
//import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.io.File;

public class KruskalMST {
	private Graph g;
	DisjointSet disjointSet;

	public KruskalMST(Graph g) {
		this.g = g;
		disjointSet = new DisjointSet();
	}

	// SP6.Q7: Kruskal's algorithm:
	public int kruskal() {
		int wmst = 0;

		for (Graph.Vertex u : g)
			disjointSet.makeSet(u);

		// LinkedList<Graph.Edge> mst = new LinkedList<>();
		ArrayList<Graph.Edge> sortedEdges = new ArrayList<>();

		for (Graph.Vertex v : g)
			for (Graph.Edge e : v)
				sortedEdges.add(e);
       
		//Sorting Edges by weight
		Collections.sort(sortedEdges);
		
		
		for (Graph.Edge e : sortedEdges) {
			Graph.Vertex u = e.from;
			Graph.Vertex v = e.otherEnd(u);
			Graph.Vertex ru = disjointSet.find(u);
			Graph.Vertex rv = disjointSet.find(v);
			if (ru.name != rv.name) {
				// mst.add(e);
				System.out.println(e);
				wmst += e.weight;
				disjointSet.union(ru, rv);
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
		Timer timer = new Timer();
		KruskalMST mst = new KruskalMST(g);
		int wmst = mst.kruskal();
		timer.end();
		System.out.println(wmst);
	}
}
