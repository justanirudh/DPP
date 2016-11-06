package concurrent_programming.counter;

/**
 * Created by anirudh on 24/9/16.
 */
public class Driver {
    public static void main(String[] args) throws InterruptedException{
        Counter counter = new Counter();
        Thread  threadA = new CounterThread(counter);
        Thread  threadB = new CounterThread(counter);

        threadA.start();
        threadB.start();
//        threadA.join();
//        threadB.join();

    }
}
