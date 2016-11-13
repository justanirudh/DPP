package concurrent_programming.hw2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by anirudh on 7/11/16.
 */
public class DeleterRunnable implements Runnable{
    ConcurrentSearcherList<Integer> csl;
    int i;

    public DeleterRunnable(ConcurrentSearcherList<Integer> csl, int i){
        this.csl = csl;
        this.i = i;
    }

    public void run(){
        try{
            int delay = ThreadLocalRandom.current().nextInt(1, 1001);
            Thread.sleep(delay);
            boolean deleted = csl.remove(i);
            System.out.println("Deleting " + i + " returned a " + deleted);

        } catch (Exception e){
        }
    }

}
