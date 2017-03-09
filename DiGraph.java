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
   
   private int[] indegrees() {
      int[] indegrees = new int[graph.length];
      
      for (int i = 0; i < graph.length; i++) {
         for (int j = 0; j < graph.length; j++) {
            if (graph[j].contains(i))
               indegrees[i]++;
         }
      }
      
      return indegrees;
   }
   
   public int[] topSort() {
      LinkedList<Integer> noIns = new LinkedList<Integer>();
      int[] res = new int[graph.length];
      int[] indegrees = this.indegrees();
      int tail = 0;
      
      for (int i = 0; i < graph.length; i++) {
         if (indegrees[i] == 0)
            noIns.add(i);
      }
      while (noIns.size() > 0) {
         int node = noIns.poll();
         res[tail] = node;
         tail++;
         indegrees = this.indegrees();
         for (int i = 0; i < graph.length; i++) {
            if (indegrees[i] == 0)
               noIns.add(i);
         }
      }
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
