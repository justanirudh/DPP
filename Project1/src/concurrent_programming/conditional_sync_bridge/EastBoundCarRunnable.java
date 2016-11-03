package concurrent_programming.conditional_sync_bridge;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by anirudh on 29/10/16.
 */
public class EastBoundCarRunnable implements Runnable{

    Bridge b;
    int id;
    public EastBoundCarRunnable(Bridge b, Integer id){
        this.b = b;
        this.id = id;
    }

    public void run(){
        try{
            int arrivalTime = ThreadLocalRandom.current().nextInt(1, 1001);
            Thread.sleep(arrivalTime);
            System.out.println("Eastbound Car with id " + id + " arrived");
            b.enterEastbound(id);
            Thread.sleep(1000);
            b.exitEastbound(id);

        } catch (Exception e){
        }
    }
}