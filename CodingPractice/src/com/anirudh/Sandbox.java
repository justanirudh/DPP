package com.anirudh;


import java.util.*;

/**
 * Created by anirudh on 21/9/16.
 */

public class Sandbox {
    public static void main(String[] args) {
//        List<Integer> l = new ArrayList<>();
//        l.add(5);
//        int f = l.get(0);
//        System.out.println(l.get(0));
        String[] s = "/".split("/");
        String[] f = "/a/b/c/d".split("/");
        String[] g = "/a".split("/");
        System.out.println("-----------------");
        for(String a : s) {
            System.out.println("->" + a);
        }
        System.out.println("-----------------");
        for(String a : f) {
            System.out.println("->" + a);
        }
        System.out.println("-----------------");
        for(String a : g) {
            System.out.println("->" + a);
        }

    }


}

