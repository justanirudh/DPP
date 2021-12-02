package com.anirudh.interview_prep_2021_2022.two_sigma;

import java.util.Random;

class Producer extends Thread {
    ConcurrentBlockingQueue q;
    Random r;

    Producer(ConcurrentBlockingQueue q) {
        this.q = q;
        r = new Random();
    }

    public void run() {
        while (true) {
            int rand = r.nextInt(1000);
            q.offer(rand);
            System.out.println("Put " + rand + " in queue by " + Thread.currentThread().getId());
        }
    }
}

class Consumer extends Thread {
    ConcurrentBlockingQueue q;

    Consumer(ConcurrentBlockingQueue q) {
        this.q = q;
    }

    public void run() {
        while (true) {
            try {
                int elem = q.poll();
                System.out.println("Removed " + elem + " from queue by " + Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class TestConcurrentBlockingQueue {

    public static void main(String[] args) {
        ConcurrentBlockingQueue q = new ConcurrentBlockingQueue();
        Thread p = new Producer(q);
        Thread c1 = new Consumer(q);
        Thread c2 = new Consumer(q);
        p.start();
        c1.start();
        c2.start();
    }
}
