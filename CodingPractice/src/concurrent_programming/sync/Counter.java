package concurrent_programming.sync;

/**
 * Created by anirudh on 10/9/16.
 */
//Exposing one object of Counter to ALL threads
public class Counter{

    long count = 0;

    public synchronized void add(long value){
        this.count += value;
        System.out.println(count);
    }
}
