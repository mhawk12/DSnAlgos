/**
 * Created by
 * Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */

package cs6301.g29;
import cs6301.g29.Graph;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ShortestPath extends GraphAlgorithm<ShortestPath.ShortestPathVertex>{
    public static final int INFINITY = Integer.MAX_VALUE;

    private  Graph g;
    private Graph.Vertex src;

    static class ShortestPathVertex {
        boolean seen;
        int distance;
        ShortestPathVertex(Graph.Vertex u) {
            seen = false;
            distance = INFINITY;
        }
    }

    public ShortestPath(Graph g, Graph.Vertex src) {
        super(g);
        this.g = g;
        this.src = src;
        node = new ShortestPath.ShortestPathVertex[g.size()];
        // Create array for storing vertex properties
        for(Graph.Vertex u: g) {
            node[u.getName()] = new ShortestPath.ShortestPathVertex(u);
        }
    }

    // reinitialize allows running BFS many times, with different sources
    void reinitialize(Graph.Vertex newSource) {
        src = newSource;
        for(Graph.Vertex u: g) {
            ShortestPath.ShortestPathVertex bu = getVertex(u);
            bu.seen = false;
        }
    }

    //DFS CODE
    public void bfs(){
        Queue<Graph.Vertex> queue = new LinkedList<>();
        visit(src);
        queue.add(src);
        while (!queue.isEmpty()){
            Graph.Vertex u = queue.remove();
            for (Graph.Edge e: u) {
                Graph.Vertex v = e.otherEnd(u);
                if(!seen(v)){
                    visit(v);
                    System.out.print(v + "-->");
                    queue.add(v);
                }

            }
        }

    }



    //DAG  SHORTEST PATH
    public void dagShortestPaths(){
        Stack<Graph.Vertex> stack = new Stack<>();

        StronglyConnectedComponents scc = new StronglyConnectedComponents(g, src);
//        if(scc.SCCs() !=1)
//            return;

//        reinitialize(src);
        scc.dfs(src,stack);

        getVertex(src).distance = 0;

        while (!stack.empty()){

            Graph.Vertex u = stack.pop();

            ShortestPathVertex du = getVertex(u);

            for (Graph.Edge e: u) {
                ShortestPathVertex dv = getVertex(e.otherEnd(u));
                if( du.distance != INFINITY) {
                    if (du.distance + e.weight < dv.distance)
                        dv.distance = du.distance + e.weight;
                }
            }
        }


        for (Graph.Vertex v : g){
            ShortestPath.ShortestPathVertex dv = getVertex(v);
            if(dv.distance == INFINITY)
            	System.out.println("Vertext Number :" + v + " " + "distance:" +  " INFINITE");
            else
            System.out.println("Vertex Number:" + v + " " + "distance: " + dv.distance);
        }
    }





    boolean seen(Graph.Vertex v) {
        return getVertex(v).seen;
    }

    //visit a node and make it seen
    void visit(Graph.Vertex v) {
        ShortestPath.ShortestPathVertex bv = getVertex(v);
        bv.seen = true;
    }

}
