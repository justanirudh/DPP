package concurrent_programming;

/**
 * Created by anirudh on 1/11/16.
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;
class BeeperControl {
    static private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    static public void beepForAnHour() {
        final Runnable beeper = new Runnable() {
            public void run() { System.out.println("beep"); }
        };
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 5, SECONDS); //beep every 5s

        scheduler.schedule(new Runnable() {
            public void run() { beeperHandle.cancel(true); }
        }, 60 * 60, SECONDS); //run beeperhandle for an hour. even if we comment our this scheduling, just beeperHandle would still work
    }

    public static void main (String[] args){
        beepForAnHour();
    }
}
