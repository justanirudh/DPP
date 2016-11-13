package concurrent_programming.hw2;

/**
 * Created by anirudh on 7/11/16.
 */
public class Driver {
    public static void main(String args[]){
        
        int num_inserters = 15;
        int num_searchers = 10;
        int num_deleters = 5;

        ConcurrentSearcherList<Integer> csl = new ConcurrentSearcherList<Integer>();

        Thread[] ithreads = new Thread[num_inserters];
        InserterRunnable[] irunnables = new InserterRunnable[num_inserters];

        Thread[] sthreads = new Thread[num_searchers];
        SearcherRunnable[] srunnables = new SearcherRunnable[num_searchers];

        Thread[] dthreads = new Thread[num_deleters];
        DeleterRunnable[] drunnables = new DeleterRunnable[num_deleters];

        for(int t = 0; t < num_inserters || t < num_searchers ||t < num_deleters; ++t){
            if(t < num_inserters){
                irunnables[t] = new InserterRunnable(csl, t);
                ithreads[t]= new Thread(irunnables[t]);
                ithreads[t].start();
            }
            if(t < num_searchers){
                srunnables[t] = new SearcherRunnable(csl, t);
                sthreads[t]= new Thread(srunnables[t]);
                sthreads[t].start();
            }
            if(t < num_deleters){
                drunnables[t] = new DeleterRunnable(csl, t);
                dthreads[t]= new Thread(drunnables[t]);
                dthreads[t].start();
            }
        }
    }
}
