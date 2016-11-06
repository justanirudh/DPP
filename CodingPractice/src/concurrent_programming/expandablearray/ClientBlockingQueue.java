package concurrent_programming.expandablearray;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by anirudh on 27/9/16.
 */
public class ClientBlockingQueue {
    static int CAPACITY = 10;

    static ExpandableArray array;
    static final BlockingQueue<ExpandableArray> queue =
            new ArrayBlockingQueue<ExpandableArray>(2);

    public static void main(String[] args) throws InterruptedException{
        array = new ExpandableArray(CAPACITY);
        Thread t = new Thread(new UseExpandableArrayRunnable());
        t.start();  //both t and main thread access array.
        queue.add(array); //passing the whole array to a blocking queue. Thread calls queue.take
        Thread.sleep(100); //without this, no time for context switch to child thread, hence NPE for get(1)
        System.out.println(array.get(1));
    }
}
