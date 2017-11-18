/**
 * Created by 丁天庆 on 2017/11/15.
 */
public class DirectedEdge {
    private final int v;                // edge source
    private final int w;                // edge target
    private final double weight;       // edge weight

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }
}
