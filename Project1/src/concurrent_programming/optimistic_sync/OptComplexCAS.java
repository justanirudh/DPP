package concurrent_programming.optimistic_sync;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by anirudh on 27/9/16.
 */
//CAS = Compare And Set
public class OptComplexCAS {

    protected AtomicReference<Complex> v = new AtomicReference<>() ;  //the state we want to update

    public OptComplexCAS(Complex cx) {
        v.set(cx);
    }

    synchronized private AtomicReference<Complex> getComplex(){
        return v;
    }

    synchronized public void scale(double x) { //no synchronization here (being optimistic)
        boolean success = false;
        do {
            AtomicReference<Complex> old = getComplex();
            AtomicReference<Complex> next = new AtomicReference<Complex>();
            Complex c = new Complex(old.get().real*x, old.get().imag*x);
            next.set(c);
            success = v.compareAndSet(old.get(),next.get());
        } while (!success);
    }

}
