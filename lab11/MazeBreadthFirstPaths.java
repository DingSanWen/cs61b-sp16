import edu.princeton.cs.algs4.Queue;

import java.util.Observable;
/**
 *  @author Josh Hug
 */

public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at vertex x. */
    private void bfs(int s) {
        /* Your code here. */
        Queue<Integer> fringe = new Queue<Integer>();
        marked[s] = true;
        announce();
        if (s == t) {
            return;
        }

        fringe.enqueue(s);

        while (!fringe.isEmpty()) {
            int v = fringe.dequeue();
            for (int w : maze.adj(v)) {

                if (!marked[w]) {
                    fringe.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    announce();
                }

                if (w == t) return;
            }
        }


    }


    @Override
    public void solve() {
         bfs(s);
    }
}

