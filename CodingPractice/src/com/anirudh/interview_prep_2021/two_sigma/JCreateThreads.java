package com.anirudh.interview_prep_2021.two_sigma;

class Print0 implements Runnable {
    @Override
    public void run() {
        System.out.println("Go Gators");
    }
}

class JCreateThreads {
    static final int NUM_THREADS = 8;

    static void example0() {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int t = 0; t < NUM_THREADS; ++t) {
            System.out.println("Creating thread " + t);
            threads[t] = new Thread(new Print0());
            threads[t].start();
        }
    }

    public static void main(String[] args) {
        example0();
    }
}

