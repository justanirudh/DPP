package concurrent_programming.conditional_sync_bridge;

/**
 * Created by anirudh on 29/10/16.
 */
public class Bridge //FINAL VERSION
{	private int e = 0; //number of eastbound cars on bridge
    private int w =0; //number of westbound cars on bridge

    synchronized void enterEastbound(int carID) throws InterruptedException
    {
        while ( ! (w==0))
        {
            wait();
        }
        System.out.println("Eastbound Car with id " + carID + " entered the bridge");
        e++ ;
    }

    synchronized void exitEastbound(int carID)
    {   e-- ;
        System.out.println("Eastbound Car with id " + carID + " exited the bridge");
        if (e==0)
            notifyAll();
    }

    synchronized void enterWestbound(int carID) throws InterruptedException
    {
        while ( ! (e==0))
        {
            wait();
        }
        System.out.println("Westbound Car with id " + carID + " entered the bridge");
        w++;
    }

    synchronized void exitWestbound(int carID)
    {
        w-- ;
        System.out.println("Westbound Car with id " + carID + " exited the bridge");
        if (w==0)
            notifyAll();
    }
}
