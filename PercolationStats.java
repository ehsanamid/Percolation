
import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        int totalCells = n * n;
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must be greater than 0");
        }
        double[] iterations = new double[trials];
        for (int i = 0; i < trials; i++) {
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
        return (StdStats.mean(iterations) - 1.96 * StdStats.stddev(iterations) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (StdStats.mean(iterations) + 1.96 * StdStats.stddev(iterations) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {

        int n = 0;
        int T = 0;
        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
        } else {
            n = 10;
            T = 100;
        }
        PercolationStats ps = new PercolationStats(n, T);
        StdOut.printf("mean                    = %f\n", ps.mean());
        StdOut.printf("stddev                  = %f\n", ps.stddev());
        StdOut.printf("95%% confidence interval = %f, %f\n", ps.confidenceLo(), ps.confidenceHi());
    }

}