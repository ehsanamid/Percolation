
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private final double CONFIDENCE_95 = 1.96;
    private final double[] iterations;
    // private int totalCells;
    private final int gridSize;
    private final int repeating;
    private int listSize;
    private int[] frequencies;

    // perform independent T on an n-by-n grid
    public PercolationStats(int n, int t) {
        repeating = t;
        int totalCells = n * n;
        gridSize = n;
        int randomNumber;
        if (n <= 0 || repeating <= 0) {
            throw new IllegalArgumentException("n and T must be greater than 0");
        }
        iterations = new double[repeating];
        int lowRange, highRange;
        lowRange = gridSize;
        highRange = gridSize * (gridSize + 1);
        for (int i = 0; i < repeating; i++) {
            Percolation perc = new Percolation(n);
            getRandomNumberInitialize(lowRange, highRange);
            while (!perc.percolates()) {

                // randomNumber = StdRandom.uniform(lowRange, highRange);
                randomNumber = getRandomNumber();
                // StdOut.println("randomNumber: " + randomNumber);
                perc.open(randomNumber / gridSize, randomNumber % gridSize + 1);

            }
            // StdOut.printf("perc.numberOfOpenSites %d \n", perc.numberOfOpenSites());
            iterations[i] = (double) perc.numberOfOpenSites() / (totalCells);
            frequencies = null;
        }

    }

    // perform independent T on an n-by-n grid

    // public PercolationStats(int n, int t) {

    // repeating = t;
    // totalCells = n * n;
    // gridSize = n;
    // int randomNumber;
    // if (n <= 0 || repeating <= 0) {
    // throw new IllegalArgumentException("n and T must be greater than 0");
    // }
    // iterations = new double[repeating];
    // getRandomNumberInitialize();

    // for (int i = 0; i < repeating; i++) {
    // Percolation perc = new Percolation(n);
    // while (!perc.percolates()) {
    // randomNumber = getRandomNumber();
    // perc.open(randomNumber / gridSize + 1, randomNumber % gridSize + 1);
    // }
    // perc.percolates();
    // iterations[i] = (double) perc.numberOfOpenSites() / (totalCells);
    // }

    // }

    // get a random number between 0 and totalCells - 1
    private void getRandomNumberInitialize(int minVal, int maxValue) {
        listSize = maxValue - minVal;
        int j = 0;
        frequencies = new int[listSize];
        for (int i = minVal; i < maxValue; i++) {
            frequencies[j++] = i;
        }

    }

    // get a random number between 0 and totalCells - 1
    private int getRandomNumber() {

        int randomNmber = 0;
        int k = 0;
        int returnValue = 0;
        randomNmber = StdRandom.uniform(0, listSize);
        returnValue = frequencies[randomNmber];
        frequencies[randomNmber] = -1;
        int[] newfrequencies = new int[listSize - 1];
        k = 0;
        for (int j = 0; j < listSize; j++) {
            if (frequencies[j] != -1) {
                newfrequencies[k++] = frequencies[j];
            }
        }
        frequencies = newfrequencies;
        listSize--;
        newfrequencies = null;
        return returnValue;
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
        return (StdStats.mean(iterations) - CONFIDENCE_95 * StdStats.stddev(iterations) / Math.sqrt(repeating));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (StdStats.mean(iterations) + CONFIDENCE_95 * StdStats.stddev(iterations) / Math.sqrt(repeating));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = 0;
        int t = 0;
        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            t = Integer.parseInt(args[1]);
            PercolationStats ps = new PercolationStats(n, t);
            StdOut.printf("mean                    = %f\n", ps.mean());
            StdOut.printf("stddev                  = %f\n", ps.stddev());
            StdOut.printf("95%% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());
        } else {
            StdOut.printf("PercolationStats n T\n");
        }

    }
}