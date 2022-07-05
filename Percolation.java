
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[] grid;
    // private int totalCells;

    private int totalOpen;
    private final WeightedQuickUnionUF weightedQuicUnionUF;
    private final int firstIndex;
    private final int lastIndex;
    private final int gridSizeX;
    private final int gridSizeY;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        int totalCells;
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
            grid[gridSizeY * (gridSizeX - 1) + i] = true;
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
        if (row < 1 || row > gridSizeY || col < 1 || col > gridSizeY) {
            throw new IllegalArgumentException("row and col must be between 1 and n");
        }
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
        if (row < 1 || row > gridSizeY || col < 1 || col > gridSizeY) {
            throw new IllegalArgumentException("row and col must be between 1 and n");
        }
        return isOpen(getIndex(row, col));
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > gridSizeY || col < 1 || col > gridSizeY) {
            throw new IllegalArgumentException("row and col must be between 1 and n");
        }
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

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        Percolation percolation1 = new Percolation(20);

        // System.out.println(percolation.isOpen(2, 1));
        // System.out.println(percolation1.isOpen(3, 11));
        // System.out.println(percolation2.isOpen(4, 21));
        // System.out.println(percolation3.isOpen(5, 34));
        // System.out.println(percolation4.isOpen(6, 1));
        int row = 15;
        int col = 15;
        for (int i = 0; i < 300; i++) {

            percolation1.open(row, col);
            // if (percolation1.isOpen(row, col)) {
            // System.out.printf("IsOpen row = %d col = %d\n", row, col);
            // }
            if (percolation1.isFull(row, col)) {
                System.out.printf("IsFull row = %d  col = %d\n", row, col);
            }
            row = StdRandom.uniform(1, 20);
            col = StdRandom.uniform(1, 20);

        }

        System.out.println(percolation.isOpen(2, 1));
        percolation.open(2, 1);
        System.out.println(percolation.isFull(2, 1));
        System.out.println(percolation.numberOfOpenSites());
        System.out.println(percolation.isFull(3, 1));
        System.out.println(percolation.percolates());

        percolation.open(2, 2);
        System.out.println(percolation.numberOfOpenSites());
        percolation.open(3, 2);
        System.out.println(percolation.numberOfOpenSites());
        percolation.open(4, 2);
        System.out.println(percolation.numberOfOpenSites());
        percolation.open(5, 2);

        System.out.println(percolation.percolates());
        System.out.println(percolation.numberOfOpenSites());

    }

}
