package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    double[] data;
    int t;
    /** perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T) {
        t = T;
        data = new double[T];
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < T; i++) {
            Percolation per = new Percolation(N);
            while (!per.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                per.open(row, col);
            }
            data[i] = per.numberOfOpenSites();
        }
    }
    /** sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(data);
    }
    /** sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(data);
    }
    /** low  endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(t);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(t);
    }
}                       
