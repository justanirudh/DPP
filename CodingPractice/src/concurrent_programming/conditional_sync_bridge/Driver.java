package concurrent_programming.conditional_sync_bridge;

/**
 * Created by anirudh on 29/10/16.
 */

public class Driver {

    public static void main(String[] args ){

        int num_east_cars = 15;
        int num_west_cars = 10;

        Bridge bridge = new Bridge();

        Thread[] ethreads = new Thread[num_east_cars];
        EastBoundCarRunnable[] erunnables = new EastBoundCarRunnable[num_east_cars];

        Thread[] wthreads = new Thread[num_west_cars];
        WestBoundCarRunnable[] wrunnables = new WestBoundCarRunnable[num_west_cars];

        for(int t = 0; t < num_east_cars || t < num_west_cars; ++t){
            if(t < num_east_cars){
                erunnables[t] = new EastBoundCarRunnable(bridge, t);
                ethreads[t]= new Thread(erunnables[t]);
                ethreads[t].start();
            }
            if(t < num_west_cars){
                wrunnables[t] = new WestBoundCarRunnable(bridge, t);
                wthreads[t]= new Thread(wrunnables[t]);
                wthreads[t].start();
            }
        }
    }
}
