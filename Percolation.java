//import edu.princeton.cs.algs4.StdOut;
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
        totalCells = n * n;
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
        if( (row < 1) || (row > gridSize) || (col < 1) || (col > gridSize) )
        {
            throw new IllegalArgumentException ("row or col is out of bounds");
        }
        if (row > 1) 
        {
            return (row -2) * gridSize + (col-1);
        }
        return -1;
    }
    
    //get the bottom neigbour of the given cell if it exists
    private int getBottomNeigbour(int row, int col) 
    {
        if( (row < 1) || (row > gridSize) || (col < 1) || (col > gridSize) )
        {
            throw new IllegalArgumentException ("row or col is out of bounds");
        }
        if (row < gridSize) 
        {
            return (row) * gridSize + (col-1);
        }
        return -1;
    }

    //get the left neigbour of the given cell if it exists
    private int getLeftNeigbour(int row, int col) 
    {
        if( (row < 1) || (row > gridSize) || (col < 1) || (col > gridSize) )
        {
            throw new IllegalArgumentException ("row or col is out of bounds");
        }
        if (col > 1) 
        {
            return (row-1) * gridSize + (col-2);
        }
        return -1;
    }

    //get the right neigbour of the given cell if it exists
    private int getRightNeigbour(int row, int col) 
    {
        if( (row < 1) || (row > gridSize) || (col < 1) || (col > gridSize) )
        {
            throw new IllegalArgumentException ("row or col is out of bounds");
        }
        if (col < gridSize) 
        {
            return (row-1) * gridSize + (col);
        }
        return -1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(isOpen(row, col)) {
            return;
        }
        grid[row - 1][col - 1] = true;
        totalOpen++;

        // check if the top neigbour is open and connect it to the top site
        int neigbourIndex = getTopNeigbour(row, col);
        if (neigbourIndex != -1 && (isOpen(neigbourIndex) )) 
        {
            weightedQuicUnionUF.union(getIndex(row, col), neigbourIndex);
            return;
        }
        // check if the bottom neigbour is open and connect it to the bottom site  
        neigbourIndex = getBottomNeigbour(row, col);
        if(neigbourIndex != -1 && (isOpen(neigbourIndex) )) 
        {
            weightedQuicUnionUF.union(getIndex(row, col), neigbourIndex);
            return;
        }
        // check if the left neigbour is open and connect it to the left site
        neigbourIndex = getLeftNeigbour(row, col);
        if(neigbourIndex != -1 && (isOpen(neigbourIndex) )) 
        {
            weightedQuicUnionUF.union(getIndex(row, col), neigbourIndex);
            return;
        }
        // check if the right neigbour is open and connect it to the right site
        neigbourIndex = getRightNeigbour(row, col);
        if(neigbourIndex != -1 && (isOpen(neigbourIndex) )) 
        {
            weightedQuicUnionUF.union(getIndex(row, col), neigbourIndex);
            return;
        }
        
    }

    // get index of site in UN list 
    private int getIndex(int row, int col)
    {
        return (row - 1) * gridSize + (col - 1);
    }


    // check to open sites are coneencted
    private boolean sitesAreConnected(int row1, int col1, int row2, int col2)
    {
        
        if(weightedQuicUnionUF.find(getIndex(row1, col1)) == weightedQuicUnionUF.find(getIndex(row2, col2)))
        {
            return true;
        }
        return false;
    }

    // is the site (index) open?
    private boolean isOpen(int index)
    {
        int row = index / gridSize + 1;
        int col = index % gridSize + 1;
        return isOpen(row, col);
    }
    
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        return grid[row - 1][col - 1];
    }

 

    // is the site (row, col) full?
    public boolean isFull(int row, int col) 
    {
        int rowOneColumn = 0;
        int rowOneNextColumn = 0;
        //check if site is open and connected to the top site
        if(isOpen(row, col) )
        {
            rowOneColumn = 1;
            while(rowOneColumn < gridSize)
            {
                if(isOpen(1,rowOneColumn))
                {
                    rowOneNextColumn = rowOneColumn + 1;
                    while(isOpen(1,rowOneNextColumn))
                    {
                        rowOneNextColumn++;
                    }
                    if(sitesAreConnected(row, col, 1, rowOneColumn))
                    {
                        return true;
                    }
                    else
                    {
                        rowOneColumn = rowOneNextColumn;
                    }   
                }
                else
                {
                    rowOneColumn++;
                    rowOneNextColumn = rowOneColumn + 1;
                }
                
            }
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {

        return totalOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        int randomRow = StdRandom.uniform(1 , gridSize);
        int randomColumn = StdRandom.uniform(1 , gridSize);
        open(randomRow, randomColumn);
        while(!isFull(randomRow, randomColumn))
        {
            randomRow = StdRandom.uniform(1 , gridSize);
            randomColumn = StdRandom.uniform(1 , gridSize);
            open(randomRow, randomColumn);
        }
        return true;
    }

    

    // test client (optional)
    public static void main(String[] args) {

        System.out.println("Hello, World");
        
    }
}
