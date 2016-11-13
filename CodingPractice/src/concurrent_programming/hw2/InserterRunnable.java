package concurrent_programming.hw2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by anirudh on 7/11/16.
 */
public class InserterRunnable implements Runnable{

    ConcurrentSearcherList<Integer> csl;
    int i;

    public InserterRunnable(ConcurrentSearcherList<Integer> csl, int i){
        this.csl = csl;
        this.i = i;
    }

    public void run(){
        try{
            int delay = ThreadLocalRandom.current().nextInt(1, 1001);
            Thread.sleep(delay);
            csl.insert(i);
            System.out.println("Inserted: " + i);

        } catch (Exception e){
        }
    }
}
