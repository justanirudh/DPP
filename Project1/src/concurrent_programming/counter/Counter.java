package concurrent_programming.counter;

/**
 * Created by anirudh on 24/9/16.
 */
public class Counter{

    long count = 0;

    public synchronized void add(long value){
        this.count += value;
        System.out.println(this.count);
    }
}
