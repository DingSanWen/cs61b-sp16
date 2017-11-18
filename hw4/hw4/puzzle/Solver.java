package hw4.puzzle;
import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.HashSet;

public class Solver {
//    private final int moves;
//    private ArrayList<searchNode> solutionNodes;
//    private HashSet<Board> marked;
    private MinPQ<searchNode> pq;
    private Stack<Board> boards;
    public Solver(Board ini) {
//        solutionNodes = new ArrayList<>();
        boards = new Stack<Board>();
//        marked = new HashSet<>();
        pq = new MinPQ<searchNode>();
//        marked.add(ini);
        searchNode first = new searchNode(ini);
        pq.insert(first);
        searchNode curr = pq.delMin();
//        solutionNodes.add(curr);
        while (!curr.getBoard().isGoal()) {
//            curr = pq.delMin();
            visit(curr);
//            solutionNodes.add(curr);
            curr = pq.delMin();
        }
//        solutionNodes.add(curr); // add last searchNode
        while (!curr.equals(first)) {
            boards.push(curr.getBoard());
            curr = curr.getPrev();
        }
    }

    public void visit(searchNode v) {
        for (Board b : BoardUtils.neighbors(v.getBoard())) {
//            if (b.isGoal()) {
//                marked.add(b);
//                searchNode w = new searchNode(b, v);
//                solutionNodes.add(w);
//                return;
//            }
            if (!b.equals(v.getPrev().getBoard())){
//                marked.add(b);
                searchNode w = new searchNode(b, v);
                pq.insert(w);
            }
        }
    }

    public int moves() {
//        return solutionNodes.get(solutionNodes.size() - 1).moves();
        return boards.size();
    }

    public Iterable<Board> solution() {
        // the moves of solutionNode will be like 0 1 2 1 2 3 4
        // we should delete the first 0 and 1
//        Stack<Board> solution = new Stack<>();
//        int size = solutionNodes.size();
//        solution.push(solutionNodes.get(size - 1).getBoard());
//        int currMoves = solutionNodes.get(size - 1).moves(); // it's 0, actually
//
//        for (int i = size - 1; i >= 0 ; i--) {
//            searchNode curr = solutionNodes.get(i);
//            if (curr.moves() < currMoves) {
//                solution.push(curr.getBoard());
//                currMoves = curr.moves();
//            }
//        }
//        return solution;
        return boards;
    }
    // DO NOT MODIFY MAIN METHOD
    /* Uncomment this method once your Solver and Board classes are ready.*/
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }


        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution()) {
            StdOut.println(board);
       }
    }

}

/* todo : run time is big than 1s and use too much memory,
 perhaps still didn't implement critical optimization right */
