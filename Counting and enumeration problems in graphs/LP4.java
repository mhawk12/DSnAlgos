//Starter code for LP4
//Do not rename this file or move it away from cs6301/g??

//change following line to your group number
package cs6301.g29;

import cs6301.g29.Graph.Vertex;
import cs6301.g29.Graph;
import cs6301.g29.Graph.Edge;
import cs6301.g29.GraphAlgorithm;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class LP4 extends GraphAlgorithm<LP4.VertexInfo> {
	Graph g;
	Vertex s;
	boolean test = false;
	private int topologicalSortCount;

	// common constructor for all parts of LP4: g is graph, s is source vertex
	public LP4(Graph g, Vertex s) {
		super(g);
		this.g = g;
		this.s = s;

		topologicalSortCount = 0;
		node = new VertexInfo[g.size()];
		// Create array for storing vertex properties
		for (Graph.Vertex u : g)
			node[u.getName()] = new VertexInfo(u);
	}

	// class to store vertex information required for the program
	static class VertexInfo {
		private boolean seen;
		private int indegree;

		VertexInfo(Graph.Vertex u) {
			seen = false;
			indegree = 0;
		}
	}

	// to calculate the indegree values associated with a vertex
	private void calculateIndegrees() {
		for (Graph.Vertex v : g) {
			if (v == null)
				continue;
			VertexInfo dv = getVertex(v);
			dv.indegree = v.revAdj.size();

		}

	}

	//////////////////////////
	// Helper functions

	// returns whether a vertex hasd been visited(seen) or not
	private boolean seen(Graph.Vertex v) {
		VertexInfo dv = getVertex(v);
		return dv.seen;
	}

	// returns the count of indegrees of a passed vertex
	private int inDegrees(Graph.Vertex v) {
		VertexInfo dv = getVertex(v);
		return dv.indegree;
	}

	/**
	 * Increases or decreases the indegree count of a vertex by certain amount
	 * 
	 * @param v
	 *            Vertex
	 * @param increaseBy
	 *            value by which the indegree has to be increased or decraesed.
	 */

	private void increaseIndegree(Graph.Vertex v, int increaseBy) {
		VertexInfo dv = getVertex(v);
		dv.indegree += increaseBy;
	}

	/**
	 * function to count the total number of topological sorts in a DAG and print
	 * all the topological paths
	 * 
	 * @param vertexList
	 *            empty vertex list to store all the Vertices of a topological sort
	 */
	private int allTopologicalSorts(ArrayList<Graph.Vertex> vertexList, boolean print) {

		for (Graph.Vertex v : g) {
			if (v == null)
				continue;

			// if the vertex is not seen and has no indegrees
			if (!seen(v) && inDegrees(v) == 0) {
				for (Graph.Edge e : v.adj)
					// decrease all the connected vertices' indegree count by one(same as removing
					// one)
					increaseIndegree(e.otherEnd(v), -1);

				// visit the vertex
				visit(v, true);
				vertexList.add(v);
				allTopologicalSorts(vertexList, print);
				vertexList.remove(v);
				// make the same vertex unvisited
				visit(v, false);
				for (Graph.Edge e : v.adj)
					increaseIndegree(e.otherEnd(v), 1);

			}
		}
		if (vertexList.size() == g.size())

			iterateTopologicalList(vertexList, print);

		return topologicalSortCount;
	}

	// iterates over a list (here list of vertices) and prints them
	private void iterateTopologicalList(ArrayList<Graph.Vertex> vertexList, boolean print) {

		if (print) {
			for (Graph.Vertex vertex : vertexList) {
				System.out.print(vertex + ", ");
			}
			System.out.println();
		}

		topologicalSortCount++;

	}

	// @param
	/**
	 * makes a graph visited or unvisited depending on the value of visited
	 * parameter
	 * 
	 * @param v
	 *            vertex
	 * @param visited
	 *            true to make a vertex seen(or visited) , false for making it
	 *            unvisited
	 */
	private void visit(Graph.Vertex v, boolean visited) {
		VertexInfo dv = getVertex(v);
		dv.seen = visited;
	}

	/**
	 * function to find the length from source to target vertex using at most k
	 * edges
	 * 
	 * @param startVertex
	 *            start vertex
	 * @param targetVertex
	 *            target vertex
	 * @param k
	 *            no of edges that can used
	 * @return -1 for negative cycle, 0 for no reachable ,otherwise the shortest
	 *         path length.
	 */

	public int BellmanFord(Graph.Vertex startVertex, Graph.Vertex targetVertex, int k) {
		for (Graph.Vertex u : g) {
			u.d[0] = Integer.MAX_VALUE;
		}

		startVertex.d[0] = 0;

		int i;
		for (i = 1; i <= k; i++) {
			for (Graph.Vertex u : g) {
				u.d[i] = u.d[i - 1];
				Iterator<Graph.Edge> it = u.reverseIterator();
				while (it.hasNext()) {
					Graph.Edge e = it.next();
					// relaxing the edges
					if (e.from.d[i - 1] != Integer.MAX_VALUE && u.d[i] > e.from.d[i - 1] + e.weight) {
						u.d[i] = e.from.d[i - 1] + e.weight;
					}

				}
			}
		}

		if (targetVertex.d[i] < targetVertex.d[i - 1] && targetVertex.d[i] != 0)
			return -1;
		else if (targetVertex.d[i - 1] == 0)
			return 0;
		else
			return targetVertex.d[i - 1];
	}

	public static class VertexComparator implements Comparator<Graph.Vertex> {
		@Override
		public int compare(Graph.Vertex o1, Graph.Vertex o2) {
			return o1.distance - o2.distance;
		}
	}

	private void DijkstraAlgorithm(Graph.Vertex sourceVertex) {

		Comparator<Graph.Vertex> comp = new VertexComparator();
		PriorityQueue<Graph.Vertex> queue = new PriorityQueue<>(comp);

		sourceVertex.distance = 0;
		queue.add(sourceVertex);

		while (!queue.isEmpty()) {
			Graph.Vertex u = queue.poll();
			u.visited = true;
			for (Graph.Edge e : u) {
				if (!e.otherEnd(u).visited && e.otherEnd(u).distance > u.distance + e.weight) {
					e.otherEnd(u).distance = u.distance + e.weight;
					queue.add(e.otherEnd(u));
				}
			}
		}
	}

	private void reInitialize() {
		for (Graph.Vertex v : g)
			v.visited = false;
	}

	/**
	 * modified dfs to calculate the the cycle weight
	 * 
	 * @param sourceVertex
	 * @param addOrNot
	 */
	private void dfs(Graph.Vertex sourceVertex, HashMap<Vertex, Integer> vertexRewardMap, boolean addOrNot) {

		sourceVertex.visited = true;
		if (addOrNot)
			sourceVertex.value += vertexRewardMap.get(sourceVertex);

		for (Graph.Edge e : sourceVertex) {

			// if tight edges then only increase reward amount of a vertex
			if (!e.otherEnd(sourceVertex).visited
					&& e.otherEnd(sourceVertex).distance == sourceVertex.distance + e.weight) {
				test = false;
				e.otherEnd(sourceVertex).value += (sourceVertex.value);
				dfs(e.otherEnd(sourceVertex), vertexRewardMap, true);

			} else if (!e.otherEnd(sourceVertex).visited
					&& e.otherEnd(sourceVertex).distance < sourceVertex.distance + e.weight) {
				test = true;
				e.otherEnd(sourceVertex).value = sourceVertex.value;
				dfs(e.otherEnd(sourceVertex), vertexRewardMap, false);

			}

			if (e.otherEnd(sourceVertex).visited) {
				e.otherEnd(sourceVertex).value = sourceVertex.value;
			}

			if (e.otherEnd(sourceVertex).previousValue < e.otherEnd(sourceVertex).value) {
				e.otherEnd(sourceVertex).previousValue = e.otherEnd(sourceVertex).value;
				e.otherEnd(sourceVertex).value = 0;
			}

		}

		sourceVertex.visited = false;

	}

	// Part a. Return number of topological orders of g
	public long countTopologicalOrders() {
		return allTopologicalSorts(new ArrayList<>(), false);
	}

	// Part b. Print all topological orders of g, one per line, and
	// return number of topological orders of g
	public long enumerateTopologicalOrders() {
		calculateIndegrees();
		return allTopologicalSorts(new ArrayList<>(), true);
	}

	// Part c. Return the number of shortest paths from s to t
	// Return -1 if the graph has a negative or zero cycle
	public long countShortestPaths(Vertex t) {
		// To do
		return 0;
	}

	// Part d. Print all shortest paths from s to t, one per line, and
	// return number of shortest paths from s to t.
	// Return -1 if the graph has a negative or zero cycle.
	public long enumerateShortestPaths(Vertex t) {
		// To do
		return 0;
	}

	// Part e. Return weight of shortest path from s to t using at most k edges
	public int constrainedShortestPath(Vertex t, int k) {
		int result = BellmanFord(s, t, k);
		if (result == -1)
			System.out.println("Negative Cycle");
		else if (result == 0)
			System.out.println("INF");
		else
			System.out.println("Shortest Path distance using " + k + " edges: " + result);
		return result;
	}

	// Part f. Reward collection problem
	// Reward for vertices is passed as a parameter in a hash map
	// tour is empty list passed as a parameter, for output tour
	// Return total reward for tour
	public int reward(HashMap<Vertex, Integer> vertexRewardMap, List<Vertex> tour) {
		return reward(vertexRewardMap, s);
	}

	public int reward(HashMap<Vertex, Integer> vertexRewardMap, Graph.Vertex sourceVertex) {
		DijkstraAlgorithm(sourceVertex);
		reInitialize();

		dfs(sourceVertex, vertexRewardMap, true);

		return sourceVertex.previousValue;

	}

	// Do not modify this function
	static void printGraph(Graph g, HashMap<Vertex, Integer> map, Vertex s, Vertex t, int limit) {
		System.out.println("Input graph:");
		for (Vertex u : g) {
			if (map != null) {
				System.out.print(u + "($" + map.get(u) + ")\t: ");
			} else {
				System.out.print(u + "\t: ");
			}
			for (Edge e : u) {
				System.out.print(e + "[" + e.weight + "] ");
			}
			System.out.println();
		}
		if (s != null) {
			System.out.println("Source: " + s);
		}
		if (t != null) {
			System.out.println("Target: " + t);
		}
		if (limit > 0) {
			System.out.println("Limit: " + limit + " edges");
		}
		System.out.println("___________________________________");
	}
}
