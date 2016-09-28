package concurrent_programming.jCreateThreads;

/**
 * Created by anirudh on 1/9/16.
 */
class Print1 implements Runnable{
    int id;

    Print1(int id){this.id = id;}

    @Override
    public void run()
    {
        System.out.println("Thread " + id + ": Go Gators!");
    }
}
