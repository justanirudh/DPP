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

    public static void main(String[] args) {
        Deque<Character> deq_arr = new ArrayDeque<>();//can be used for both stack and queue. faster than Stack and LL
        deq_arr.addFirst('C');
        deq_arr.addFirst('B');
        deq_arr.addFirst('D');
        System.out.println(deq_arr.removeFirst());
        System.out.println(deq_arr.removeFirst());
        System.out.println(deq_arr.removeFirst());


    }


}

