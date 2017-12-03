
/**
 * Created by
 * Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */

package cs6301.g29;
import java.util.Scanner;

public class ShortestPathDriver {

        public static void main(String args[]) {

            Scanner in = new Scanner(System.in);
            System.out.println("Choose one of the options");
            System.out.println("1 for bfs");
            System.out.println("2 for Dag shortest Path");
            int options = in.nextInt();
            
            System.out.println("Enter the Graph");
            Graph g = Graph.readDirectedGraph(in);
            
            ShortestPath sP = new ShortestPath(g, g.getVertex(1));
            switch (options) {

                case 1:
                    sP.bfs();
                    System.out.println("BFS Done");
                    break;

                case 2:
                    sP.dagShortestPaths();
                    break;
            }
        }

}
