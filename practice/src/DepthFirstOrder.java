
import java.util.Stack;
// Graph should be a DGraph
public class DepthFirstOrder {
	private boolean[] marked;
	private Stack<Integer> reversePostOrder;
	
	public DepthFirstOrder (Graph G) {
		marked = new boolean[G.V()];
		reversePostOrder = new Stack<Integer>();
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) {
				dfs(G, v);
			}
		}
	}
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) dfs(G, w);
			reversePostOrder.push(w);
		}
	}
	
	public Iterable<Integer> reversePostOrder() {
		return reversePostOrder;
	}
}
