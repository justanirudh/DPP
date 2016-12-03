package com.anirudh.general_algos;

/**
 * Created by anirudh on 14/11/16.
 */
public class UseSingleton {

    public static void main(String[] args){
        SingletonExmaple s = SingletonExmaple.getInstance();
        System.out.println(s.foo);
        SingletonExmaple s2 = SingletonExmaple.getInstance();
        System.out.println(s2.foo);
    }
}
