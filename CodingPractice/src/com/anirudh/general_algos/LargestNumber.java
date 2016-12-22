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

public class LargestNumber {
    public static ArrayList<Integer> largestNumber(ArrayList<Integer> nums) {
        TreeMap<Character, ArrayList<Integer>> sm = new TreeMap<>(); //sorted map
        for (Integer i : nums) {
            char firstDigit = i.toString().charAt(0);
            if (!sm.containsKey(firstDigit))
                sm.put(firstDigit, new ArrayList<>());
            sm.get(firstDigit).add(i);
        }

        ArrayList<Character> sortedKeys = new ArrayList<Character>();
        for (Character k : sm.keySet())
            sortedKeys.add(k);

        ArrayList<Integer> al = new ArrayList<>(); //reverse sorted list of integers by their (recursive) first digits

        for (int i = sortedKeys.size() - 1; i >= 0; --i) {
            Character key = sortedKeys.get(i);
            ArrayList<Integer> values = sm.get(key);
            if (values.size() == 1) //if value array has only 1 number, just append it
                al.add(values.get(0));
            else {
                int singleDigitCount = 0;
                ArrayList<Integer> newValues = new ArrayList<>();
                for (Integer v : values) { //if 3 -> 3, 32 ,34; remove 3 and add 2 and 4 to next recursive call
                    Integer firstpart = Character.getNumericValue(v.toString().charAt(0));
                    if (v.intValue() == firstpart.intValue())
                        ++singleDigitCount;
                    else {
                        Integer secondPart = Integer.parseInt(v.toString().substring(1));
                        newValues.add(secondPart);
                    }
                }
                ArrayList<Integer> temp = largestNumber(newValues);
                ArrayList<Integer> newTemp = new ArrayList<>();
                if (singleDigitCount == 0) {
                    for (Integer t : temp) {
                        Integer newt = Integer.parseInt(key.toString() + t.toString());
                        newTemp.add(newt);
                    }
                } else {
                    Integer keyInt = Character.getNumericValue(key);
                    if (temp.size() == 0) {
                        while (singleDigitCount != 0) {
                            newTemp.add(keyInt);
                            singleDigitCount--;
                        }
                    } else {
                        for (Integer t : temp) {
                            if (t <= keyInt) {
                                while (singleDigitCount != 0) {
                                    newTemp.add(keyInt);
                                    singleDigitCount--;
                                }
                            }
                            Integer newt = Integer.parseInt(key.toString() + t.toString());
                            newTemp.add(newt);
                        }
                        if (singleDigitCount > 0) { //key is the lowest number, then add all repeated single digits at the end
                            while (singleDigitCount != 0) {
                                newTemp.add(keyInt);
                                singleDigitCount--;
                            }
                        }
                    }
                }
                al.addAll(newTemp); // add to final array
            }
        }
        return al;
    }

    public static String auxLN(int[] nums) {
        boolean allZeroes = true;
        for (int num : nums) {
            if (num != 0) {
                allZeroes = false;
            }
        }
        if (allZeroes)
            return "0";
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
        int[] arr = {16, 128, 1};
        System.out.println(auxLN(arr));
    }

}
