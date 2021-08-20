package com.anirudh.interview_prep_2021;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by paanir on 5/18/21.
 */
public class HelloWorld {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        map.put(1,1);
        map.put(2,1);
        map.put(3,1);
        map.put(4,1);
        map.put(5,1);

        for(int i : map.keySet()){
            i += 1;
            System.out.println(i);
        }

    }
}
