package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF grids; //sites;
    private int numberOfOpenSites;
    private int n; //number of row;
    private int top;
    private int bottom;
    private boolean[] open;

    /** create N-by-N grid, with all sites initially blocked
     * 0 -- N^2 - 1 is index of sites, N^2 is virtual top, N^2+1 is virtual bottom */
    public Percolation(int N) {

        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grids = new WeightedQuickUnionUF(N * N + 2);
        numberOfOpenSites = 0;
        n = N;
        top = N * N;
        bottom = N * N + 1;
        open = new boolean[N * N]; //default value is false.
        // create virtual top and bottom .
        for (int i = 0; i < N; i++) {
            grids.union(top, i);
            grids.union(bottom, xyTo1D(N - 1, i));
        }
    }
    /** convert row and col to a index. */
    private int xyTo1D(int row, int col) {
        return row * n + col;
    }

    /** if it's a valid row or col, return true, otherwise return false.*/
    private boolean checkBound(int num) {
        return num >= 0 && num <= n - 1;
    }

    /** open the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        if (!(checkBound(row) && checkBound(col))) {
            throw new IndexOutOfBoundsException();
        }
        int index = xyTo1D(row, col);
        if (!open[index]) {
            open[index] = true;
            numberOfOpenSites += 1;
        }
        //union it and it's neighbour.
        unionOpen(index, row, col + 1);
        unionOpen(index, row, col - 1);
        unionOpen(index, row + 1, col);
        unionOpen(index, row - 1, col);
    }
    /** if neighbour is open, union it and it's neighbour. */
    private void unionOpen(int index, int row, int col) {
        int neighbour = xyTo1D(row, col);
        if (checkBound(row) && checkBound(col) && open[neighbour]) {
            grids.union(index, neighbour);
        }
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        if (!(checkBound(row) && checkBound(col))) {
            throw new IndexOutOfBoundsException();
        }
        int index = xyTo1D(row, col);
        return open[index];
    }

    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        if (!(checkBound(row) && checkBound(col))) {
            throw new IndexOutOfBoundsException();
        }
        int index = xyTo1D(row, col);
        if (open[index]) {
            return grids.connected(index, top);
        }
        else {
            return false;
        }
    }

    /** number of open sites */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /** does the system percolate? */
    public boolean percolates() {
        return grids.connected(top, bottom);
    }

    /** unit testing (not required) */
    public static void main(String[] args) {

    }
}
//TODO : potential downside unfixed.
