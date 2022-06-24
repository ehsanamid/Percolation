
import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    double[] iterations;

    int T;

    // perform independent T on an n-by-n grid
    public PercolationStats(int n, int t) {

        T = t;
        int totalCells = n * n;
        if (n <= 0 || T <= 0) {
            throw new IllegalArgumentException("n and T must be greater than 0");
        }
        iterations = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation perc = new Percolation(n);
            perc.percolates();
            iterations[i] = (double) perc.numberOfOpenSites() / (totalCells);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(iterations);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(iterations);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (StdStats.mean(iterations) - 1.96 * StdStats.stddev(iterations) / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (StdStats.mean(iterations) + 1.96 * StdStats.stddev(iterations) / Math.sqrt(T));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = 0;
        int T = 0;
        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
            PercolationStats ps = new PercolationStats(n, T);
            StdOut.printf("mean                    = %f\n", ps.mean());
            StdOut.printf("stddev                  = %f\n", ps.stddev());
            StdOut.printf("95%% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());
        } else {
            StdOut.printf("PercolationStats n T\n");
        }

    }
}