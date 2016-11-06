package concurrent_programming.jCreateThreads;

/**
 * Created by anirudh on 1/9/16.
 */
class Driver_JCreateThreads {
    static final int NUM_THREADS = 8;

    static void example_norpassinganything(){
        Thread[] threads = new Thread[NUM_THREADS];
        for (int t = 0; t< NUM_THREADS; ++t){
            System.out.println("Creating thread " + t);
            threads[t]= new Thread(new Print0Runnable());
            threads[t].start();
        }
    }
    static void example_passingid(){
        Thread[] threads = new Thread[NUM_THREADS];
        for (int t = 0; t< NUM_THREADS; ++t){
            threads[t]= new Thread(new Print1Runnable(t));
            threads[t].start();
        }
    }

    static void example2() throws InterruptedException{
        Thread[] threads = new Thread[NUM_THREADS];
        //refs to Runnables
        BusyWorkRunnable[] runnables = new BusyWorkRunnable[NUM_THREADS];

        //create and start the threads
        for (int t = 0; t< NUM_THREADS; ++t){
            runnables[t] = new BusyWorkRunnable(t);
            threads[t]= new Thread(runnables[t]);
            threads[t].start();
        }

//        Thread.dumpStack();
//        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
//        for (Map.Entry entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//            for (StackTraceElement element : (StackTraceElement[])entry.getValue()){
//                System.out.println(element);
//            }
//        }

        //join the threads
        for (int t = 0; t< NUM_THREADS; ++t){
            //join can throw InterruptedException, thus this method does too
            System.out.println( threads[t].getId() + ": "+ threads[t].getState());
            threads[t].join();
            //get resultVal from the Runnable
            System.out.println("Joined with thread " +
                    runnables[t].id + " result="+ runnables[t].resultVal);
        }

    }

    static void example1_lambda(){
        Thread[] threads = new Thread[NUM_THREADS];
        for (int t = 0; t< NUM_THREADS; ++t){
            final int id = t;
            threads[t]= new Thread(
                    () -> System.out.println("Thread "+ id + ": Go Gators")
            );
            threads[t].start();
        }
    }

    static void example3(){
        for (int t = 0; t< NUM_THREADS; ++t){
            new Thread(
                    ()->System.out.println(Thread.currentThread())
            ).start();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        //example0();
//        example1();
        example2();
//        example1_lambda();
//        example3();
    }
}
