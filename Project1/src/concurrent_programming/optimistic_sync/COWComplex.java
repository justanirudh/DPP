package concurrent_programming.optimistic_sync;

/**
 * Created by anirudh on 29/9/16.
 */
public class COWComplex {

    protected Complex v;
    public COWComplex(double real, double imag) {
        v = new Complex(real,imag);
    }

    synchronized void scale(double x) {
        System.out.println("Scaling by: " + x);
        double r = x*v.real;
        double i = x*v.imag;
        v = new Complex(r,i);
    }
    synchronized Complex getComplex() {
        return v;
    }
}
