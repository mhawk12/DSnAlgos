import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


/**
 * Created by Nishant Shekhar (nxs167130)
 *            Anusha Agasthi  (nxa162430)
 *            Prince Patel    (pap160930)
 */
public class Bfs {

    /**
     * @param edgeTo keeps the previous vertex from where the we reach the present vertex
     */
    private static Graph.Vertex[] edgeTo;
    private int V ;
    private Random random = new Random();
    static Graph.Vertex source ,target;


    //constructor
    Bfs(Graph graph)
    {
        V = graph.size();
        edgeTo = new Graph.Vertex[V];
    }


    /**
     *
     * @param g Input graph
     * @param s random node from where the Breadth First Search needs to start
     * @return array with the distance from a particular vertex
     */
    public int[] BFS(Graph g, int s) {

        // Mark all the vertices as not visited(By default
        // set as false)
        System.out.print(V);
        boolean visited[] = new boolean[V];

        // array that contains distance of vertices from a particular  vertex
        int[] max = new int[g.size()];


        // Create a queue for BFS
        LinkedList<Graph.Vertex> queue = new LinkedList<Graph.Vertex>();



        // Initialize int[] marked before a BFS is going to commence.
        for(int i=0;i< g.size();i++){
            max[i] = -1;
        }

        // Mark the current node as visited and enqueue it
        visited[s-1]=true;

        //assign zero distance to the first vertex
        max[s-1] = 0;
        queue.add(g.getVertex(s));

        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            Graph.Vertex gv = queue.poll();

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Graph.Edge> i = g.getVertex(gv.name+1).iterator();
            while (i.hasNext())
            {
                Graph.Edge n = i.next();
                if (!visited[n.to.name])
                {
                    visited[n.to.name] = true;
                    max[n.to.name] = max[n.from.name]+1;
                    queue.add(n.to);
                    edgeTo[n.to.name] = g.getVertex(n.from.name+1);
                }
            }
        }
        return max;
    }


    /**
     * Funtion to find the vertex with maximum distance from random vertex given by user
     * @param maxBfs
     * @return  index of the vertex with maximum distance
     */
    public int maxbfs(int[] maxBfs)
    {
        int index = -1;
        int max = 0;
        for(int i = 0; i< maxBfs.length ;i++){
            if(maxBfs[i]> max){
                //  System.out.print(maxBfs[i]);
                max = maxBfs[i];
                index = i;
            }
        }
        return index;
    }


    /**
     * Gives the path from a source vertex to target vertex
     * @param g input graph
     * @return  returns target end vertex that need s to be reached from source vertex
     */

    public  LinkedList<Graph.Vertex> diameter(Graph g){

        LinkedList<Graph.Vertex> path = new LinkedList<Graph.Vertex>();
        for (Graph.Vertex v = target; v != source ; v = edgeTo[v.name]){
            System.out.print(edgeTo[v.name]);
            path.add(v);
        }
        path.add(source);
        return path;
    }


    public static void main(String args[]) throws IOException {
        Scanner in = new Scanner(System.in);

        //read the graph
        Graph graph = Graph.readGraph(in);

        Bfs bfs = new Bfs(graph);

        System.out.print("Enter random node");
        int random_node = in.nextInt();

        //Call BFS on the graph
        int[] maxBfs = bfs.BFS(graph, random_node);

        //find vertex which is at maximum distance from the entered vertex
        int max = bfs.maxbfs(maxBfs);

        //Call BFS on the graph
        int[] maxBfs2 = bfs.BFS(graph, max);

        //find vertex which is at maximum distance from the entered vertex
        int max2 = bfs.maxbfs(maxBfs2);

        //Get the source
        source  = graph.getVertex(max);

        //Get the Target node
        target = graph.getVertex(max2);
        //Get the diameter of the give vertex
        LinkedList<Graph.Vertex> gv = bfs.diameter(graph);

        System.out.println("Path");


        while (!gv.isEmpty()) {
            System.out.print("--" + gv.pop());
        }

    }
}
