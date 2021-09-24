package com.anirudh.interview_prep_2021.two_sigma.anki.multithreading;

import java.util.Random;

class Producer extends Thread {
    ThreadSafeQueue q;
    Random r;

    Producer(ThreadSafeQueue q) {
        this.q = q;
        r = new Random();
    }

    public void run() {
        int i = 0;
        while (true) {
            int rand = r.nextInt(1000);
            q.offer(rand);
            System.out.println("Put " + rand + " in queue by " + Thread.currentThread().getId());
            i++;
        }
    }
}

class Consumer extends Thread {
    ThreadSafeQueue q;

    Consumer(ThreadSafeQueue q) {
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

public class TestThreadSafeQueue {

    public static void main(String[] args) {
        ThreadSafeQueue q = new ThreadSafeQueue();
        Thread p = new Producer(q);
        Thread c1 = new Consumer(q);
        Thread c2 = new Consumer(q);
        p.start();
        c1.start();
        c2.start();
    }
}
