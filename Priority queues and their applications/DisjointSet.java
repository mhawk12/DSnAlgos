/**
 * Created by 
 * Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */

package cs6301.g29;

public class DisjointSet {

	public void makeSet(Graph.Vertex u) {
		u.parent = u;
		u.rank = 0;
	}

	public Graph.Vertex find(Graph.Vertex u) {
		if (u.getName() != u.parent.getName())
			u.parent = find(u.parent);
		return u.parent;
	}

	public void union(Graph.Vertex x, Graph.Vertex y) {
		if (x.rank > y.rank)
			y.parent = x;
		else if (y.rank > x.rank)
			x.parent = y;
		else {
			x.rank++;
			y.parent = x;
		}

	}

}
