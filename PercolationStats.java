
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
        double[] percs = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            perc.percolates();
            percs[i] = (double) perc.numberOfOpenSites() / (totalCells);
        }
        StdOut.println("mean: " + StdStats.mean(percs));
        StdOut.println("stddev: " + StdStats.stddev(percs));
        StdOut.println(
                "95% confidence interval: " + (StdStats.mean(percs) - 1.96 * StdStats.stddev(percs) / Math.sqrt(trials))
                        + ", " + (StdStats.mean(percs) + 1.96 * StdStats.stddev(percs) / Math.sqrt(trials)));
    }

    // sample mean of percolation threshold
    public double mean() {
        return 0.0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0.0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0.0;
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