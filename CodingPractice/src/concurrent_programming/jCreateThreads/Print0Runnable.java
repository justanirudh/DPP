package concurrent_programming.jCreateThreads;

/**
 * Created by anirudh on 1/9/16.
 */
//a class that implements Runnable
class Print0Runnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Go Gators");
    }
}
