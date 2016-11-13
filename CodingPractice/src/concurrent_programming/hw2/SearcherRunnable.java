package concurrent_programming.hw2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by anirudh on 7/11/16.
 */
public class SearcherRunnable implements Runnable{

    ConcurrentSearcherList<Integer> csl;
    int i;

    public SearcherRunnable(ConcurrentSearcherList<Integer> csl, int i){
        this.csl = csl;
        this.i = i;
    }

    public void run(){
        try{
            int delay = ThreadLocalRandom.current().nextInt(1, 1001);
            Thread.sleep(delay);
            boolean present = csl.search(i);
            System.out.println("Searching " + i + " returned a " + present);

        } catch (Exception e){
        }
    }
}
