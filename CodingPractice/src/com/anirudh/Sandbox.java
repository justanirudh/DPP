package com.anirudh;

import com.sun.deploy.util.StringUtils;
import com.sun.tools.javac.util.ArrayUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by anirudh on 21/9/16.
 */
public class Sandbox {



    public static void main(String[] args) {
        System.out.println(Math.pow(2,5));
        System.out.println(2 << 4);

        String[] foo = {"2", "1"};
        List<Integer> bar = Arrays.stream(foo).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
//        int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7};
//        String result = StringUtils.join(ArrayUtils.toObject(arr), " - ");

    }

    String a = "\\";
}

