import java.util.Stack;

public class DepthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	// edgeTo[v] is previous vertex on path from s to v 
	private int s;
	
	public DepthFirstPaths(Graph G, int s) {
		int V = G.V();
		marked = new boolean[V];
		edgeTo = new int[V];
		this.s = s;
		dfs(G, s);
	}
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(s)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);			
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		for (int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
}