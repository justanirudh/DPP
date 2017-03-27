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

        Integer a = 0, b = 0, d = 0;

        int c = a != b ? 5 : foo(d);

        System.out.println(c + "," + d);

        return;
    }
}

