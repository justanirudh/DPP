package com.anirudh.general_algos;

import java.util.*;

/**
 * Created by paanir on 12/17/16.
 */

/*
Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.
 */

//TODO: some tests failing
public class LargestNumber {
    public static ArrayList<Integer> largestNumber(ArrayList<Integer> nums) {
        TreeMap<Character, ArrayList<Integer>> sm = new TreeMap(); //sorted map
        for (Integer i : nums) {
            char firstDigit = i.toString().charAt(0);
            if (!sm.containsKey(firstDigit))
                sm.put(firstDigit, new ArrayList<>());
            sm.get(firstDigit).add(i);
        }

        ArrayList<Character> sortedKeys = new ArrayList<Character>();

        for (Character k : sm.keySet())
            sortedKeys.add(k);

        ArrayList<Integer> al = new ArrayList<>();

        for (int i = sortedKeys.size() - 1; i >= 0; --i) {
            Character key = sortedKeys.get(i);
            ArrayList<Integer> values = sm.get(key);
            if (values.size() == 1)
                al.add(values.get(0));
            else {
                int singleDigitCount = 0;
                ArrayList<Integer> newValues = new ArrayList<>();
                for (Integer v : values) {
                    if (v == v % 10) {
                        ++singleDigitCount;
                    } else {
                        newValues.add(v % 10);
                    }
                }
                ArrayList<Integer> temp = largestNumber(newValues); // /10
                ArrayList<Integer> newTemp = new ArrayList<>();
                if (singleDigitCount == 0) {
                    for (Integer t : temp) {
                        Integer newt = Integer.parseInt(key.toString() + t.toString());
                        newTemp.add(newt);
                    }
                } else {
                    for (Integer t : temp) {
                        Integer keyInt = Character.getNumericValue(key);
                        if (t <= keyInt) {
                            while (singleDigitCount != 0) {
                                newTemp.add(keyInt);
                                singleDigitCount--;
                            }
                        }
                        Integer newt = Integer.parseInt(key.toString() + t.toString());
                        newTemp.add(newt);
                    }
                    al.addAll(newTemp);
                }
            }
        }
        return al;
    }

    public static String auxLN(int[] nums) {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for (int index = 0; index < nums.length; index++) {
            intList.add(nums[index]);
        }
        ArrayList<Integer> al = largestNumber(intList);
        StringBuilder sb = new StringBuilder();
        for (Integer i : al) {
            sb.append(i.toString());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {128, 12/*, 50, 99, 3*/};
        System.out.println(auxLN(arr));
    }

}
