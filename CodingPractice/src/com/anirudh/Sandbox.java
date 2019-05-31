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
        ArrayList<Integer> arrl = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            arrl.add(i);
        }
        Iterator arrlIter = arrl.iterator();
        while(arrlIter.hasNext()) {
            System.out.println(arrlIter.next());
        }


    }


}

