import edu.princeton.cs.algs4.Bag;

import java.util.Iterator;

/**
 * Created by 丁天庆 on 2017/11/15.
 */
public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        adj = (Bag<DirectedEdge>[])new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<DirectedEdge>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> edges = new Bag<>();
        for (int i = 0; i < V; i++) {
            for (DirectedEdge e : adj[i]) {
                edges.add(e);
            }
        }
        return edges;
    }
}
