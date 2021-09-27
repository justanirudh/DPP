package com.anirudh.interview_prep_2021.two_sigma;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
You are given two infinite streams of data, where each datum has fields (timestamp, value).
The streams have a single function take() that pops the oldest thing off and returns it
or “blocks” until something arrives in the queue for it to return.
Data might arrive much later than its timestamp. You are given a function output(a,b),
which takes two values, one from each stream, and computes something.
You want to call output() on all (a,b) pairs that have timestamps less than some given interval apart,
and you want to do this as soon as any data that completes such a pair arrives.
Construct pseudocode that will do this. The answer involves two threads that each manage a list of
numbers pulled off their stream, pull a number from the other thread’s list, try to match it against
everything, and then carefully discard data when appropriate.
 */
class Element {
    int ts;
    int val;

    Element(int ts, int val) {
        this.ts = ts;
        this.val = val;
    }
}

class CBQ { //threadsafe concurrent blocking queue
    Element take() {
        return new Element(0, 0);//wait
    }

    void put() { //being used somewhere else
        //notifyAll
    }
}

class Buffer {
    Deque<Element> buffer; //sorted by timestamp naturally, so no need of PriorityQueue, peek has oldest elem
    final int interval;

    public Buffer(int interval) {
        buffer = new ArrayDeque<>();
        this.interval = interval;
    }

    //can create read/write locks using conditions and await/signalAll system but keeping it simple for now
    public synchronized void put(Element elem) { //sync on this
        buffer.offer(elem);
    }

    public synchronized List<Element> getAllAndCleanUp(Element elem) { //also remove old entries
        while (!buffer.isEmpty() && Math.abs(buffer.peek().ts - elem.ts) > interval) {
            buffer.poll();
        }
        return new ArrayList<>(buffer);
    }
}


class Consumer extends Thread {
    Buffer bufferA;
    Buffer bufferB;
    CBQ stream;
    boolean isA;
//    ExecutorService executor;

    public void output(Element a, Element b) {
        System.out.println("Pair: {" + a.val + b.val + "}");
    }

    public Consumer(CBQ stream, Buffer a, Buffer b, boolean isA) {
        bufferA = a;
        bufferB = b;
        this.stream = stream;
        this.isA = isA;
//        executor = Executors.newCachedThreadPool(); //can use executor service to further make things concurrent
    }

    /*
    1. take elem from stream; blocking
    2. offer in self buffer, take lock
    2.2 can spin of new thread, but lets do it in this thread to start with
    3. take lock on bufferB and find correct intervals
    4. send values to output function
     */
    public void run() {
        while (true) {
            Element elem = stream.take(); //blocking
            List<Element> res;
            if (isA) {
                bufferA.put(elem);
                res = bufferB.getAllAndCleanUp(elem);
            } else {
                bufferB.put(elem);
                res = bufferA.getAllAndCleanUp(elem);
            }
            res.forEach(x -> output(elem, x));
        }
    }

}

public class InfiniteStreamsOfData {

    public static void main(String[] args) {
        int interval = 5;
        CBQ streamA = new CBQ();
        CBQ streamB = new CBQ();
        Buffer bufferA = new Buffer(interval);
        Buffer bufferB = new Buffer(interval);

        Thread a = new Consumer(streamA, bufferA, bufferB, true);
        Thread b = new Consumer(streamB, bufferA, bufferB, false);
        a.start();
        b.start();
    }

}
