package cs6301.g29;

/**
* Created by Nishant Shekhar (nxs167130)
*            Anusha Agasthi  (nxa162430)
*            Prince Patel    (pap160930)
*/

import java.util.*;

public class Euler extends GraphAlgorithm<Euler.DFSVertex> {
	public static final int INFINITY = Integer.MAX_VALUE;
	int VERBOSE;
	List<Graph.Edge> T;
	Graph.Vertex src;

	static class DFSVertex {
		boolean seen;
		int indegree;
		int outdegree;
		boolean unexplored;

		DFSVertex(Graph.Vertex u) {
			seen = false;
			indegree = u.adj.size();
			outdegree = u.revAdj.size();
			unexplored = false;
		}
	}

	public Euler(Graph g, Graph.Vertex src) {
		super(g);
		this.src = src;
		node = new DFSVertex[g.size()];
		VERBOSE = 1;
		T = new LinkedList<>();
		// Create array for storing vertex properties
		for (Graph.Vertex u : g) {
			node[u.getName()] = new DFSVertex(u);
		}
	}

	/**
	 * Depth first function to visit all the vertices
	 * 
	 * @param newsrc
	 *            source vertex
	 * @param stack
	 *            Stack to store vertices in order we visit them
	 */

	// function to check whether a vertex has been visited earlier or not
	boolean seen(Graph.Vertex u) {
		return getVertex(u).seen;
	}

	// Visit a node v from u
	void visit(Graph.Vertex u) {
		DFSVertex bv = getVertex(u);
		bv.seen = true;
	}

	// dfs
	void dfs(Graph.Vertex newsrc) {
		visit(newsrc);
		for (Graph.Edge e : newsrc) {
			Graph.Vertex v = e.otherEnd(newsrc);
			if (!seen(v)) {
				dfs(v);
			}
		}
	}

	/**
	 * function to perform DFS on reverse graph or transpose of original graph
	 * 
	 * @param newsrc
	 */
	void revdfs(Graph.Vertex newsrc) {
		visit(newsrc);
		Iterator<Graph.Edge> iterator = newsrc.reverseIterator();
		while (iterator.hasNext()) {
			Graph.Edge edge = iterator.next();
			Graph.Vertex v = edge.otherEnd(newsrc);
			if (!seen(v)) {
				revdfs(v);
			}
		}
	}

	// The main function that finds and prints all strongly
	// connected components
	int SCCs() {
		dfs(g.getVertex(1));

		for (Graph.Vertex u : g) {
			DFSVertex bv = getVertex(u);
			if (bv.seen == false)
				return -1;
		}

		// Mark all the vertices as not visited (For second DFS)
		for (Graph.Vertex u : g) {
			DFSVertex bv = getVertex(u);
			bv.seen = false;
		}

		revdfs(g.getVertex(1));

		for (Graph.Vertex u : g) {
			DFSVertex bv = getVertex(u);
			if (bv.seen == false)
				return -1;
		}

		return 1;
	}

	/*
	 * To do: test if the graph is Eulerian. If the graph is not Eulerian, it prints
	 * the message: "Graph is not Eulerian" and one reason why, such as
	 * "inDegree = 5, outDegree = 3 at Vertex 37" or
	 * "Graph is not strongly connected"
	 */
	boolean isEulerian() {
		if (SCCs() != 1) {
			System.out.println("Not Strongly Connected");
			return false;
		}

		// checking for the equality of InDegree and OutDegree
		for (Graph.Vertex u : g) {
			if (u.adj.size() != u.revAdj.size()) {
				System.out.println("In Degree of " + u + "is not equal to its outdegree");
				System.out
						.println("Indegree of " + u + "=" + u.adj.size() + " " + "Outdegree of " + u + "=" + u.revAdj.size());
				return false;
			}
		}

		return true;

	}

	/*
	 * Print tours found by findTours() using following format: Start vertex of
	 * tour: list of edges with no separators Example: lp2-in1.txt, with start
	 * vertex 3, following tours may be found. 3:
	 * (3,1)(1,2)(2,3)(3,4)(4,5)(5,6)(6,3) 4: (4,7)(7,8)(8,4) 5: (5,7)(7,9)(9,5)
	 *
	 * Just use System.out.print(u) and System.out.print(e)
	 */
	void printTours() {
		for (Graph.Edge e : T) {
			System.out.println("(" + e.from + " " + e.to + " )");
		}
	}

	void setVerbose(int v) {
		VERBOSE = v;
	}
	public void explore(Graph.Vertex source) {
		// if the is being explored now then setting it to unexplored = false, i.e. this
		// vertex's tour is explored
		source.unexplored = false;
		Graph.Vertex temp = source;
		for (Graph.Edge edges : source.tour) {
			T.add(edges);
			temp = edges.otherEnd(temp);
			if (temp.unexplored) {
				explore(temp);
			}
		}
	}

	// Stitch tours into a single tour using the algorithm discussed in class
	void stitchTours() {
		// find the tour starting at a vertes --> here source
		explore(src);
	}

	/**
	 * find a sub-tour starting at u Tour is initially a empty List Output = tour
	 */
//	int count = 0;
	public LinkedList<Graph.Edge> findTours(Graph.Vertex u, LinkedList<Graph.Edge> tour) {
		tour = new LinkedList<>();
		DFSVertex du = getVertex(u);
		while (du.outdegree > 0) {
			/**
			 * removing the first edge of the linkeList from the vertex which has outgoing
			 * edges unexplored This saves us from indexing the vertices and helps in
			 * retrieving the unvisited edges in O(1) time
			 */
			Graph.Edge temp = u.adj.removeFirst();
			du.outdegree--;
			u = temp.otherEnd(u);
			du = getVertex(u);
			tour.add(temp);
		}
//		System.out.println(tour);
		return tour;

	}

	// Find tours starting at vertices with unexplored edges
	void findTours() {
		/**
		 * breaking the graph is sub-tours starting the given source vertex
		 */
		src.tour = findTours(src, new LinkedList<>());
		src.unexplored = true;
		for (Graph.Edge e : src.tour) {
			Graph.Vertex p = e.to;
			DFSVertex bu = getVertex(p);

			/**
			 * if there still exists a node with unexplored outgoing edges , we find that
			 * sub tour starting at that vertex
			 */
			Graph.Vertex previous = null;
			if (bu.outdegree > 0) {
				previous = src;
				src = p;
				findTours();
			}
			if(previous!= null)
			src = previous;

		}
	}

	// To do: function to find an Euler tour
	public List<Graph.Edge> findEulerTour() {
		findTours();
		if (VERBOSE > 9) {
			printTours();
		}
		stitchTours();
		return T;
	}

}
