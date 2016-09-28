package concurrent_programming.optimistic_sync;

/**
 * Created by anirudh on 27/9/16.
 */
public class Driver {

    public static void main(String[] args) {
        int NUM_THREADS = 4;
        Complex cx = new Complex(3 ,4);
        OptComplexCompareAndSet occas = new OptComplexCompareAndSet(cx);

        Thread[] threads = new Thread[NUM_THREADS];

        OptComplexCASRunnable[] runnables = new OptComplexCASRunnable[NUM_THREADS];

        //Using the same object in all threads. Hence value of real and imag will keep on increasing
        for(int t = 0; t< NUM_THREADS; ++t){
            runnables[t] = new OptComplexCASRunnable(occas, t+2);
            threads[t] = new Thread(runnables[t]);
            threads[t].start();
        }
    }

}
