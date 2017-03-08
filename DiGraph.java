import java.util.LinkedList;

public class DiGraph {
   private LinkedList<Integer>[] graph;
   
   public DiGraph(int n) { //Constructor
      graph = new LinkedList[n];
      for (int i = 0; i < graph.length; i++) {
         graph[i] = new LinkedList<Integer>();
      }
   }
   
   public void addEdge(int from, int to) {
      if (!graph[from - 1].contains(to - 1)) {
         graph[from - 1].addLast(to - 1);
      }
   }
   
   public void deleteEdge(int from, int to) {
      if (graph[from - 1].contains(to - 1)) {
         graph[from - 1].remove((Object)(to - 1));
      }
   }
   
   public int edgeCount() {
      int count = 0;
      for (int i = 0; i < graph.length; i++) {
         count += graph[i].size();
      }
      return count;
   }
   
   public int vertexCount() {
      return graph.length;
   }
   
   public void print() {
      for (int i = 0; i < graph.length; i++) {
         System.out.print((i + 1) + " is connected to: ");
         for (int j = 0; j < graph[i].size(); j++) {
            System.out.print(graph[i].get(j) + 1);
         	try {
               graph[i].get(j + 1);
               System.out.print(", ");
            }
            catch (IndexOutOfBoundsException e) {
            }
         }
         System.out.println();
      }
   }
   
}
