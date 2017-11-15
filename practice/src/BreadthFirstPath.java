import edu.princeton.cs.algs4.Queue;

public class BreadthFirstPath {
	private boolean[] marked;
	private int[] edgeTo;
	public BreadthFirstPath(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		bfs(G, s);
	}

	private void bfs(Graph G, int s) {
		Queue<Integer> fringe = new Queue<Integer>();
		fringe.enqueue(s);
		marked[s] = true;
		while (!fringe.isEmpty()) {
			int v = fringe.dequeue();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					fringe.enqueue(w);
					marked[w] = true;
					edgeTo[w] = v;
				}
			}
		}
	}
}
