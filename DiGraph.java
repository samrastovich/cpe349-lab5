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
   
   private void addZeroIndegrees(int[] indegrees, LinkedList<Integer> queue) {
      
      for (int i = 0; i < indegrees.length; i++) {
         if (indegrees[i] == 0) {
            queue.addLast(i);
            indegrees[i]--;
         }
      }
   }
   
   public int[] topSort() throws IllegalArgumentException {
      int[] res = new int[graph.length]; //the result of the sort
      int[] indegrees = this.indegrees(); //find indegrees of all vertices
      LinkedList<Integer> queue = new LinkedList<Integer>(); //initialize a queue for vertices with 0 indegrees
      addZeroIndegrees(indegrees, queue); //add all nodes with 0 indegrees to the queue
      int cntr = 0, visitedVertices = 0;
      
      while (!queue.isEmpty()) {
         int node = queue.poll(); //dequeue first element and append
         res[cntr++] = node;
         for (int adj: graph[node]) { //reduce indegrees of all adjacent nodes
            if (indegrees[adj] > 0)
               indegrees[adj]--;
            	if (indegrees[adj] == 0) {
            		queue.addLast(adj);
            		indegrees[adj]--;
            	}
         }
         visitedVertices++;
      }
      
      if (visitedVertices != graph.length)
         throw new IllegalArgumentException();
      else
         return res;
   }

   private class VertexInfo {
   	int dist, pred;
   }

   private VertexInfo[] BFS(int s) {
		int numVert = graph.length, cur;
		VertexInfo[] res = new VertexInfo[numVert];

		for (int i = 0; i < numVert; i++) { //Initialization
			res[i] = new VertexInfo();
			res[i].dist = -1;
			res[i].pred = -1;
		}

		res[s].dist = 0;

		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.addLast(s);

		while (!queue.isEmpty()) {
			cur = queue.poll();
			for (int v : graph[cur]) {
				if (res[v].dist == -1) {
					res[v].dist = res[cur].dist + 1;
					res[v].pred = cur;
					queue.addLast(v);
				}
			}
		}

		return res;
   }

   public Boolean isTherePath(int from, int to) {
   	VertexInfo[] res = BFS(from);

   	return !(res[to].dist == -1);
   }

   public int lengthOfPath(int from, int to) {
   	VertexInfo[] res = BFS(from);

   	return res[to].dist;
   }

   public void printPath(int from, int to) {
   	VertexInfo[] res = BFS(from);

   	if (res[to].dist == -1) {
			System.out.println("There is no path");
   	}
   	else {
			String output = "";
			while (from != to) {
				output = "-> " + (to + 1) + " " + output;
				to = res[to].pred;
			}
			output = (from + 1) + " " + output;
			System.out.println(output);
   	}
   }

   private class TreeNode {
		int vertNum;
		LinkedList<TreeNode> children;
   }

   private TreeNode buildTree(int s) {
   	VertexInfo[] bfsArray = BFS(s);
   	TreeNode[] nodes = new TreeNode[graph.length];

   	for (int i = 0; i < graph.length; i++) {
			nodes[i] = new TreeNode();
			nodes[i].vertNum = i;
			nodes[i].children = new LinkedList<TreeNode>();
   	}

   	for (int i = 0; i < bfsArray.length; i++) {
			if (bfsArray[i].pred != -1) {
				nodes[bfsArray[i].pred].children.add(nodes[i]);
			}
   	}

   	return nodes[s];
   }

   public void printTree(int s) {
		TreeNode root = buildTree(s - 1);
      
		recurPrint(root, 1);
   }

   private void recurPrint(TreeNode root, int indent) {
      System.out.println(root.vertNum + 1);
      for (TreeNode cur: root.children) {
         for (int i = indent; i > 0; i--) {
            System.out.print(" ");
         }
         recurPrint(cur, indent * 3);
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
