Question No:   Java File                            Main function 
         1     TopologicalSort.java                In TopologicalSort.java
		 2     StronglyConnectedComponents.java    In StronglyConnectedComponents.java
		 3     StronglyConnectedComponents.java    In StronglyConnectedComponents.java
         4     TopologicalSort.java                In TopologicalSort.Java 

		 
		 In TopologicalSort , we have answers to both the parts of question one , i.e topological sorting using DFS and using indegree methods AND answer to question four . They all are commneted in the main method.
		 Please run these methods one by one , by uncommenting them one by one. Otherwise answer may not be correct as they all chnages some of the same global values.

        FOr example :
        IN TopologicalSort.java

        WE have 
		
		First uncommnet this and run it.
            	//To see wether the given graph is a DAG or not
//    	System.out.println("The graph is a DAG :"  + topologicalSort.isDAG(graph)); 
    	
    	Second uncommnet this and run it.
    	//InDegree Topological ordering
//    	topologicalSort.topologicalSortINDEGREE();
    	
    	Thrid uncommnet this and run it.
        //DFS Topological ordering
//    	topologicalSort.topologicalSortDFS();	


For question 2 and 3 please run 	StronglyConnectedComponents.java 
  In main method:
  
   These two lines are there :
   
        
        For quesstion 2 run this:
    	System.out.println("No of connected components  :" + stronglyConnectedComponents.SCCs());
		
		
		For question 3 run this
    	System.out.println("The graph is Eulerian  : " + stronglyConnectedComponents.testEulerian(graph));  	
  
  
         Here both the lines can  run at once. i.e.By run the class file just once we can get the answers.