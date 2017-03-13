import java.util.Scanner;

public class DiGraphTest {
   
   public static void main(String[] arg) {
      Scanner in = new Scanner(System.in);
      int numVert, exInput1, exInput2;
      String buffer;
      char input;
      DiGraph graph;

      System.out.println("Enter the number of vertices: ");
		numVert = in.nextInt();

		graph = new DiGraph(numVert);

		System.out.println();
		System.out.println("Choose one of the following operations: ");
		System.out.println("- add edge (enter a)");
		System.out.println("- delete edge (enter d)");
		System.out.println("- edge count (enter e)");
		System.out.println("- vertex count (enter v)");
		System.out.println("- print graph (enter p)");
      System.out.println("- topological sort (enter t)");
      System.out.println("- is there a path (enter i)");
      System.out.println("- length of the path (enter l)");
      System.out.println("- shortest path (enter s)");
      System.out.println("- print the breadth-first-tree (enter b)");
		System.out.println("- Quit (enter q)");
		System.out.println();

		buffer = in.nextLine();
		do {
			System.out.println("Enter command: ");
			buffer = in.nextLine();
			if (buffer.length() > 1) {
				input = buffer.charAt(0);
				System.out.println(input + " is an invalid command");
			}
			else {
			input = buffer.charAt(0);
			switch (input) {
				case 'a' :
				   System.out.println("Enter vertices to connect separated by a space: ");
			      graph.addEdge((exInput1 = in.nextInt()), (exInput2 = in.nextInt()));
			      System.out.println("Added edge (" + exInput1 + "," + exInput2 + ")");
					buffer = in.nextLine();
					break;

			   case 'd' :
			   	System.out.println("Enter vertices to disconnect separated by a space: ");
			   	graph.deleteEdge((exInput1 = in.nextInt()), (exInput2 = in.nextInt()));
			   	System.out.println("Deleted edge (" + exInput1 + "," + exInput2 + ")");
					buffer = in.nextLine();
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
               
            case 't' :
               try {
						int[] res = graph.topSort();
               	System.out.println("Indegrees:");
                  for (int i = 0; i < res.length; i++) {
                     System.out.print((res[i] + 1));
                     if (i < res.length - 1)
                     	System.out.print(", ");
                  }
                  System.out.println();
               }
               catch (IllegalArgumentException e) {
						System.out.println("This graph is cyclic");
               }
               break;

            case 'q' :
            	System.out.println("Quitting program");
            	break;

            case 'i' :
            	System.out.println("Enter start and end vertices, separated by a space");
            	Boolean res = graph.isTherePath((exInput1 = in.nextInt()) - 1, (exInput2 = in.nextInt()) - 1);
            	if (res == true) {
						System.out.println("There IS a path between " + exInput1 + " and " + exInput2);
            	}
            	else {
						System.out.println("NO path exists");
            	}
            	buffer = in.nextLine();
					break;

            case 'l' :
            	System.out.println("Enter start and end vertices, separated by a space");
            	int length = graph.lengthOfPath((exInput1 = in.nextInt()) - 1, (exInput2 = in.nextInt()) - 1);
            	System.out.println("Length of shortest path between " + exInput1 + " and " + exInput2 + " is: " + length);
					buffer = in.nextLine();
					break;

            case 's' :
            	System.out.println("Enter start and end vertices, separated by a space");
					graph.printPath((exInput1 = in.nextInt()) - 1, (exInput2 = in.nextInt()) - 1);
					buffer = in.nextLine();
					break;
               
            case 'b':
               System.out.println("Enter start vertex to print");
               graph.printTree(in.nextInt());
               buffer = in.nextLine();
               break;

			   default :
			   	System.out.println(input + " is an invalid command");
			   	break;
			}
			}
		}
		while (input != 'q');
   }
}
