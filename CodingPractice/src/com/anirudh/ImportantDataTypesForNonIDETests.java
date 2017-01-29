package com.anirudh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * Created by paanir on 1/19/17.
 */
public class ImportantDataTypesForNonIDETests {

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(s);

        ArrayList<Integer> arrl = new ArrayList<>();
        System.out.println(arrl);

        int[] arr = new int[]{2,3,4};
        System.out.println(arr);

        HashMap<Integer, String> hm = new HashMap<>();
        System.out.println(hm);

        TreeMap<Integer, String> tm = new TreeMap<>(); //sorted

        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>(); //in the same order as inserted
    }
}
