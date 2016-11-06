package concurrent_programming.jCreateThreads;

/**
 * Created by anirudh on 1/9/16.
 */
class Print1Runnable implements Runnable{
    int id;

    Print1Runnable(int id){this.id = id;}

    @Override
    public void run()
    {
        System.out.println("Thread " + id + ": Go Gators!");
    }
}
