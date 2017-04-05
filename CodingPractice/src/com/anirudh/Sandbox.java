package com.anirudh;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by anirudh on 21/9/16.
 */
public class Sandbox {

    public static class Foo {

    }

    public static int foo(int d) {
        d = 6;
        return 8;
    }

    public static void main(String[] args) {
//        Foo f1 = new Foo();
//        Foo f2 = new Foo();
//        System.out.println(f1.equals(f2));

//        Integer a = 0, b = 0, d = 0;
//
//        int c = a != b ? 5 : foo(d);
//
//        System.out.println(c + "," + d);
//
//        return;
//        int[] record = new int[3];
//        ArrayList<Integer> al = new ArrayList<>();
//        al.add(1);
//        al.add(2);
//        al.add(4);
//        al.add(0, 3);
//        for(Integer i : al)
//            System.out.println(i);

//        System.out.println(Boolean.parseBoolean(""));
//        int a = 5;
//        char[] ar = Integer.toString(5).toCharArray();

//        int [][] array= {
//                {10, 16},
//                {2, 8},
//                {1, 6},
//                {7, 12} };
//
//        Arrays.sort(array, new java.util.Comparator<int[]>() {
//            public int compare(int[] a, int[] b) {
//                return Integer.compare(a[0], b[0]);
//            }
//        });
//
//        for(int[] ar : array){
//            System.out.println(ar[0] + "," + ar[1]);
//        }

//        String url = "http://tinyurl.com/foobar";
//        String[] pieces = url.split("/");
//        for(String str: pieces)
//            System.out.println(str);

        byte[] encodedBytes = Base64.getEncoder().encode("Test".getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
        System.out.println("decodedBytes " + new String(decodedBytes));


    }
}

