import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


    private boolean[][] grid;
    private int totalCells;
    private int gridSize;
    private int totalOpen;
    private WeightedQuickUnionUF weightedQuicUnionUF;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // initialize the list
        gridSize = n;
        totalCells = n ^ 2;
        weightedQuicUnionUF = new WeightedQuickUnionUF(totalCells);
        grid = new boolean[gridSize][gridSize];
        
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = false;
            }
        }
        totalOpen = 0;

    }

    // get the top neigbour of the given cell if it exists
    private int getTopNeigbour(int row, int col) 
    {
        if (row == 1) {
            return -1;
        } else {
            return (row -2) * gridSize + (col-1);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(isOpen(row, col)) {
            return;
        }
        grid[row - 1][col - 1] = true;
        totalOpen++;
        int index = (row - 1) * gridSize + (col - 1);
        int topNeigbour = getTopNeigbour(row, col);
        if(weightedQuicUnionUF.find(index) != weightedQuicUnionUF.find((topNeigbour)) )
        {
            weightedQuicUnionUF.union(index, topNeigbour);
        }
        
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        return true;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {

        return totalOpen;
    }

    // does the system percolate?
    public boolean percolates() {

        return true;
    }

    

    // test client (optional)
    public static void main(String[] args) {

        int N = 0;
        int randomRow,randomColumn;
        
        if(args.length > 0)
        {
            N = Integer.valueOf(args[0]);

        }
        else 
        { 
            StdOut.println(" missing command line argument");
            return;
        }
        

        randomRow = StdRandom.uniform(0 , N);
        randomColumn = StdRandom.uniform(0 , N);

        {
            StdOut.printf(" Row = %d Column = %d", randomRow,randomColumn);
        }
    }
}
