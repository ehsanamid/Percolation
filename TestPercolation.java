import edu.princeton.cs.algs4.StdOut;

public class TestPercolation {

    public  static void main(String[] args) 
    {
        Percolation perc = new Percolation(5);

        perc.open(3, 1);
        perc.open(3, 2);
        perc.open(2, 2);
        boolean ret =  perc.percolates();
        StdOut.print(ret);
    }
}
