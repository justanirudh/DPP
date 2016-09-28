package concurrent_programming.jCreateThreads;

/**
 * Created by anirudh on 1/9/16.
 */
class BusyWork implements Runnable{
    int id;
    double resultVal;

    BusyWork(int id){this.id = id;}

    public void run(){
        double result = 0;
        for (int i = 0; i < 1000000; i++){
            result = result + Math.random();
        }
        System.out.println(id + ": Thread result =" + result);
        System.out.flush();
        resultVal = result;
    }
}
