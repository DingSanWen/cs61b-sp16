package hw4.puzzle;

import java.util.Comparator;


public class searchNode implements Comparable<searchNode> {
    private Board board;
    private searchNode prev;
    private final int moves;

    public searchNode(Board board) {
        this.board = board;
        moves = 0;
    }

    public searchNode(Board board, searchNode prev) {
        this.board = board;
        this.prev = prev;
        this.moves = prev.moves + 1;
    }

    public int moves() {
        return moves;
    }

    public int getHamPriority() {
        return moves + board.hamming();
    }

    public int getManPriority() {
        return moves + board.manhattan();
    }

    public Board getBoard () {
        return this.board;
    }

    public int compareTo(searchNode o) {
        return this.getHamPriority() - o.getHamPriority();
    }
}
