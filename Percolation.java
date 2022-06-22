
public class Percolation {

    private int[] id;
    private int[] grid;
    private int totalCells;
    private int gridSize;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        // initialize the list
        gridSize = n;
        totalCells = n^2;
        id = new int[totalCells];
        for (int i = 0; i < totalCells; i++) id[i] = i;
       
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {

        return true;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {

        return true;
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {

        return 0;
    }

    // does the system percolate?
    public boolean percolates()
    {

        return true;
    }

    // test client (optional)
    public static void main(String[] args)
    {

    }
}