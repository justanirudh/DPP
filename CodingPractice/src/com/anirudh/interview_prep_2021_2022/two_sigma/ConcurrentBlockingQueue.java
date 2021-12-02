package com.anirudh.interview_prep_2021_2022.two_sigma;

import java.util.ArrayDeque;
import java.util.Deque;

public class ConcurrentBlockingQueue {

    Deque<Integer> queue;

    public ConcurrentBlockingQueue() {
        queue = new ArrayDeque<>();
    }

    public synchronized void offer(Integer i) {
        queue.offer(i);
        notifyAll();
    }

    public synchronized Integer poll() throws InterruptedException {
        while(size() == 0) {
            wait();
        }
        return queue.poll();
    }

    public synchronized int size() {
        return queue.size();
    }
}

/* Use futures
class App
{    ExecutorService executor = newCachedThreadPool()
     ArchiveSearcher searcher = ...

    void showSearch(final String target) throws InterruptedException
   {     Future<String> future =
             executor.submit(new Callable<String>()
            {  public String call()
               { return searcher.search(target); }
            });
           doOtherThings(); // do other things while searching
           try {   displayText(future.get()); // use future
            }
           catch (ExecutionException ex) { cleanup(); return; }
    }
}

 */