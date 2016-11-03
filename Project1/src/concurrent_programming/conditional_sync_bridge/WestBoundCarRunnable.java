package concurrent_programming.conditional_sync_bridge;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by anirudh on 29/10/16.
 */
public class WestBoundCarRunnable implements Runnable{

    Bridge b;
    int id;
    public WestBoundCarRunnable(Bridge b, Integer id){
        this.b = b;
        this.id = id;
    }

    public void run(){
        try{
            int arrivalTime = ThreadLocalRandom.current().nextInt(1, 1001);
            Thread.sleep(arrivalTime); // to make sure arrival is random
            System.out.println("Westbound Car with id " + id + " arrived");
            b.enterWestbound(id);
            Thread.sleep(20); //time, taken to cross the bridge
            b.exitWestbound(id);

        } catch (Exception e){
        }
    }
}
