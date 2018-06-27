package com.anirudh;

import com.sun.deploy.util.StringUtils;
import com.sun.tools.javac.util.ArrayUtils;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by anirudh on 21/9/16.
 */
public class Sandbox {

    int[] foo(int a, int b){
        return new int[]{a,b};
    }
    int foo;

    private Sandbox() {
        foo = System.identityHashCode(this);
    }

    public static void main(String[] args) {
////        Foo bar = new Bar();
////        bar.print();
//
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        int r = list.remove(1);
//        System.out.println(r );
//        for(int a : list)
//            System.out.println(a);

//        System.out.println("z".compareTo("aa"));
//        int a = 5;
//        int b = 6;

//        System.out.println(Integer.MAX_VALUE + "," +  Float.MAX_VALUE + "," + Double.MAX_VALUE);
        System.out.println(Math.pow(56.5, 5));

        
    }




}

