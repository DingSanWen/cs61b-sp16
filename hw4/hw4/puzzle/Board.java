package hw4.puzzle;

public class Board {

    private final int[][] tiles;
    private final int N;
    private final int[][] goal;

/*  Constructs a board from an N-by-N array of tiles where
    tiles[i][j] = tile at row i, column j*/
    public Board(int[][] tiles) {
        N = tiles.length;
        this.tiles = new int[N][N];
        goal = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
                goal[i][j] = i * N + j + 1;
            }
        }
        goal[N - 1][N - 1] = 0;
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return N;
    }

    public int hamming() {
        int han = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != goal[i][j]) {
                    han++;
                }
            }
        }
        return han;
    }

    public int manhattan() {
        int man = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != 0) {
                    int row = tiles[i][j] / N;
                    int col = tiles[i][j] % N - 1;
                    man += Math.abs(row - i) + Math.abs(col - j);
                }
            }
        }
        return man;

    }

    public boolean isGoal(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != goal[i][j])
                    return false;
            }
        }
        return true;
    }

    public boolean equals(Object y) {
        if (y == this)
            return true;
        if (y == null)
            return false;
        if (y.getClass() != this.getClass())
            return false;

        Board other = (Board)y;
        if (this.size() != other.size())
            return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tileAt(i, j) != other.tileAt(i, j))
                    return false;
            }
        }
        return true;
    }
    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
