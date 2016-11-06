package concurrent_programming.optimistic_sync;

/**
 * Created by anirudh on 27/9/16.
 */
public class OptComplex {

    protected Complex v;  //the state we want to update

    void scale(double x) { //no synchronization here (being optimistic)
        boolean success = false;
        do {
            Complex old = getComplex();
            Complex next = new Complex(old.real*x, old.imag*x);
            success = commit(old,next);
        } while (!success);
    }

    synchronized Complex getComplex(){
        return v;
    }

    synchronized boolean commit(Complex old, Complex newComplex) {
        if (old == v) {
            v = newComplex;
            return true;
        }
        else
            return false;
    }
}
