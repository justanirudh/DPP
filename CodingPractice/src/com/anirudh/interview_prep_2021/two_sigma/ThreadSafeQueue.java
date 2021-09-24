package com.anirudh.interview_prep_2021.two_sigma;

import java.util.ArrayDeque;
import java.util.Deque;

public class ThreadSafeQueue {

    Deque<Integer> queue;

    public ThreadSafeQueue() {
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
