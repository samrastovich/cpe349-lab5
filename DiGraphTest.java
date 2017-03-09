import java.util.Scanner;

public class DiGraphTest {
   
   public static void main(String[] arg) {
      Scanner in = new Scanner(System.in);
      int numVert, exInput1, exInput2;
      char input;
      DiGraph graph;

      System.out.print("Enter the number of vertices: ");
		numVert = in.nextInt();

		graph = new DiGraph(numVert);

		System.out.println("Choose one of the following operations: ");
		System.out.println("- add edge (enter a)");
		System.out.println("- delete edge (enter d)");
		System.out.println("- edge count (enter e)");
		System.out.println("- vertex count (enter v)");
		System.out.println("- print graph (enter p)");
      System.out.println("- topological sort (enter t)");
		System.out.println("- Quit (enter q)");

		do {
			System.out.println("Enter command: ");
			input = in.next().charAt(0);

			switch (input) {
				case 'a' :
				   System.out.println("Enter vertices to connect separated by a space: ");
			      graph.addEdge((exInput1 = in.nextInt()), (exInput2 = in.nextInt()));
			      System.out.println("Added edge between " + exInput1 + " and " + exInput2);
			      break;

			   case 'd' :
			   	System.out.println("Enter vertices to disconnect separated by a space: ");
			   	graph.deleteEdge((exInput1 = in.nextInt()), (exInput2 = in.nextInt()));
			   	System.out.println("Deleted edge between " + exInput1 + " and " + exInput2);
			   	break;

			   case 'e' :
			   	System.out.println("Number of edges: " + graph.edgeCount());
			   	break;

			   case 'v' :
			   	System.out.println("Number of vertices: " + graph.vertexCount());
			   	break;

			   case 'p' :
			   	System.out.println("The graph is the following: ");
			   	graph.print();
			   	break;

			   case 'q' :
			   	System.out.println("Quitting program");
			   	break;
               
            case 't' :
               System.out.println("Indegrees:");
               int[] res = graph.topSort();
               for (int i = 0; i < res.length; i++) {
                  System.out.println(res[i] + ", ");
               }
               break;

			   default :
			   	System.out.println("Invalid command");
			   	break;
			}
		}
		while (input != 'q');
   }
}
