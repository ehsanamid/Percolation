
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[] grid;
    private int totalCells;

    private int totalOpen;
    private WeightedQuickUnionUF weightedQuicUnionUF;
    private int firstIndex;
    private int lastIndex;
    private int gridSizeX;
    private int gridSizeY;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // initialize the list
        int gridSize = n;
        gridSizeX = gridSize + 2;
        gridSizeY = gridSize;
        totalCells = gridSizeX * gridSizeY;
        firstIndex = 0;
        lastIndex = totalCells - 1;
        weightedQuicUnionUF = new WeightedQuickUnionUF(totalCells);
        grid = new boolean[totalCells];

        for (int i = 0; i < totalCells; i++) {

            grid[i] = false;

        }
        for (int i = 0; i < gridSizeY; i++) {
            grid[i] = true;
            grid[gridSizeX * (gridSizeY - 1) + i] = true;
        }
        for (int i = 1; i < gridSizeY; i++) {
            weightedQuicUnionUF.union(i - 1, i);
        }
        for (int i = 1; i < gridSizeY; i++) {
            weightedQuicUnionUF.union(i - 1 + gridSizeY * (gridSizeX - 1), i + gridSizeY * (gridSizeX - 1));
        }
        totalOpen = 0;

    }

    // get the top neigbour of the given cell if it exists
    private int getTopNeigbour(int index) {
        if (index < gridSizeY) {
            return -1;
        } else {
            return index - gridSizeY;
        }

    }

    // get the bottom neigbour of the given cell if it exists
    private int getBottomNeigbour(int index) {
        if (index >= (gridSizeX - 1) * gridSizeY) {
            return -1;
        } else {
            return index + gridSizeY;
        }
    }

    // get the left neigbour of the given cell if it exists
    private int getLeftNeigbour(int index) {
        if ((index % gridSizeY) == 0) {
            return -1;
        } else {
            return index - 1;
        }

    }

    // get the right neigbour of the given cell if it exists
    private int getRightNeigbour(int index) {
        if (((index + 1) % gridSizeY) == 0) {
            return -1;
        } else {
            return index + 1;
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int cellIndex = getIndex(row, col);
        if (isOpen(row, col)) {
            return;
        }
        grid[cellIndex] = true;
        totalOpen++;

        // check if the top neigbour is open and connect it to the top site
        int neigbourIndex = getTopNeigbour(cellIndex);
        if (neigbourIndex != -1 && (isOpen(neigbourIndex))) {
            weightedQuicUnionUF.union(cellIndex, neigbourIndex);

        }
        // check if the bottom neigbour is open and connect it to the bottom site
        neigbourIndex = getBottomNeigbour(cellIndex);
        if (neigbourIndex != -1 && (isOpen(neigbourIndex))) {
            weightedQuicUnionUF.union(cellIndex, neigbourIndex);

        }
        // check if the left neigbour is open and connect it to the left site
        neigbourIndex = getLeftNeigbour(cellIndex);
        if (neigbourIndex != -1 && (isOpen(neigbourIndex))) {
            weightedQuicUnionUF.union(cellIndex, neigbourIndex);

        }
        // check if the right neigbour is open and connect it to the right site
        neigbourIndex = getRightNeigbour(cellIndex);
        if (neigbourIndex != -1 && (isOpen(neigbourIndex))) {
            weightedQuicUnionUF.union(cellIndex, neigbourIndex);

        }

    }

    // get index of site in UN list
    private int getIndex(int row, int col) {
        return (row) * gridSizeY + (col - 1);
    }

    // check to open sites are coneencted
    private boolean sitesAreConnected(int index1, int index2) {

        if (weightedQuicUnionUF.find(index1) == weightedQuicUnionUF.find(index2)) {
            return true;
        }
        return false;
    }

    // is the site (index) open?
    private boolean isOpen(int index) {
        return grid[index];
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return isOpen(getIndex(row, col));
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // check if site is open and connected to the virtual top site
        if (isOpen(row, col)) {
            if (sitesAreConnected(getIndex(row, col), firstIndex)) {
                return true;
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

        return sitesAreConnected(firstIndex, lastIndex);
    }

}
