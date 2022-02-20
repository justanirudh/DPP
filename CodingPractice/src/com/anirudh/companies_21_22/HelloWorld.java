package com.anirudh.companies_21_22;

import java.util.*;

/**
 * Created by paanir on 5/18/21.
 */
public class HelloWorld {
    enum Status{
        PENDING,
        PARTIAL,
        FILLED
    }

    static void yello() {
        int a = 0;
        int b = 1;
        int c = 2;
        int d = 3;

    }

    public static void main(String[] args) {

        String foo = "/a/b/c";
        String bar = "/";
        String baz = "";

        System.out.println( foo.split("/").length);
        System.out.println( bar.split("/").length);
        System.out.println( baz.split("/").length);
        Map<Integer, Status> orderMap = new HashMap<>();
        orderMap.put(1, Status.PENDING);
        orderMap.put(2, Status.PARTIAL);
        orderMap.put(3, Status.FILLED);

//        List<Integer> list = new ArrayList<>(5);
//        System.out.println("91283472332".compareTo("2147483648"));
//        System.out.println("2147483648".compareTo("91283472332"));
//        String s = "\t\tfoo";
//        System.out.println(s.startsWith("\t"));
//        System.out.println(s.substring(1));
//        System.out.println(s.substring(2));
//        System.out.println('z' - 'a');
//        yello();
//
//        int n = 3;
//        for (int i = 0; i < Math.pow(2, n); ++i) {
//            // generate bitmask, from 0..00 to 1..11
//            String bitmask = Integer.toBinaryString(i)/*.substring(1)*/;
//            System.out.println(bitmask);
//        }
//        System.out.println(Integer.parseInt("-1"));
//        char a = 'a';
//        System.out.println(a + 1);
//        List<Integer> l = new ArrayList<>();
//        l.add(1);
//        l.add(2);
//        l.add(3);
//        l.add(4);
//        Collections.sort(l, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });

//        System.out.println(Integer.toBinaryString(5));

    }
}
