package com.anirudh.interview_prep_2021.two_sigma.anki.multithreading;

class Stutterer extends Thread {
    static final int ITERS = 5;
    private String m;

    public Stutterer(String m) {
        this.m = m;
    }

    //overrides run method @Override
    public void run() {
        for (int i = 0; i != ITERS; i++) {
            System.out.println(m);
            System.out.println(Thread.currentThread().getId());
        }
    }
}

class Talk2 {
    public static void main(String[] args) {
        //create two Thread objects
        Thread s = new Stutterer("Go ");
        Thread t = new Stutterer("Gators");
        //start the threads
        s.start();
        t.start();
    }
}

