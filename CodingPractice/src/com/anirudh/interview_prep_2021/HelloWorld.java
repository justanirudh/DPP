package com.anirudh.interview_prep_2021;

import java.util.*;

/**
 * Created by paanir on 5/18/21.
 */
public class HelloWorld {
    public static void main(String[] args) {
        List<String> r1 = new ArrayList<>();
        r1.add("1");
        r1.add("foo");
        List<String> r2 = new ArrayList<>();
        r2.add("1");
        r2.add("foo");
        Map<List<String>, Integer> map = new HashMap<>();
        map.put(r1, 1);
        System.out.println(map.get(r2));
    }
}
