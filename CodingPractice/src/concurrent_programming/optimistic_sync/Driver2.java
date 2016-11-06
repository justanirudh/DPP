package concurrent_programming.optimistic_sync;

/**
 * Created by anirudh on 29/9/16.
 */
public class Driver2 {

    public static void main(String[] args) throws InterruptedException{
        int NUM_THREADS = 4;
        COWComplex cow = new COWComplex(3, 4);

        Thread[] threads = new Thread[NUM_THREADS];

        COWComplexRunnable[] runnables = new COWComplexRunnable[NUM_THREADS];

        //Using the same object in all threads. Hence value of real and imag will keep on increasing
        for(int t = 0; t< NUM_THREADS; ++t){
            runnables[t] = new COWComplexRunnable(cow, t+2);
            threads[t] = new Thread(runnables[t]);
            threads[t].start();
            Thread.sleep(100); //without this, same copy returned across various threads
        }
    }
}
