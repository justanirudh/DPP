package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by paanir on 1/27/17.
 */
public class IntegerToEnglishWords {

    public static HashMap<Integer, String> populateOnesPlace() {
        HashMap<Integer, String> onesPlace = new HashMap<>();
//        onesPlace.put(0, "Zero");
        onesPlace.put(1, "One");
        onesPlace.put(2, "Two");
        onesPlace.put(3, "Three");
        onesPlace.put(4, "Four");
        onesPlace.put(5, "Five");
        onesPlace.put(6, "Six");
        onesPlace.put(7, "Seven");
        onesPlace.put(8, "Eight");
        onesPlace.put(9, "Nine");
        return onesPlace;
    }

    public static HashMap<Integer, String> populateTensPlace() {
        HashMap<Integer, String> tensPlace = new HashMap<>();
        tensPlace.put(1, "Ten");
        tensPlace.put(2, "Twenty");
        tensPlace.put(3, "Thirty");
        tensPlace.put(4, "Forty");
        tensPlace.put(5, "Fifty");
        tensPlace.put(6, "Sixty");
        tensPlace.put(7, "Seventy");
        tensPlace.put(8, "Eighty");
        tensPlace.put(9, "Ninety");
        return tensPlace;
    }

    public static HashMap<String, String> populateTeens() {
        HashMap<String, String> tensPlace = new HashMap<>();
        tensPlace.put("", "Ten");
        tensPlace.put("One", "Eleven");
        tensPlace.put("Two", "Twelve");
        tensPlace.put("Three", "Thirteen");
        tensPlace.put("Four", "Fourteen");
        tensPlace.put("Five", "Fifteen");
        tensPlace.put("Six", "Sixteen");
        tensPlace.put("Seven", "Seventeen");
        tensPlace.put("Eight", "Eighteen");
        tensPlace.put("Nine", "Nineteen");
        return tensPlace;
    }

    public static StringBuilder getTheFixForOne(StringBuilder sb, HashMap<String, String> teens) {
        String[] strArr = sb.toString().split(" ");
        String last = strArr[0];
        String[] newStrArr = Arrays.copyOfRange(strArr, 1, strArr.length);
        sb = new StringBuilder();
        for (int i = 0; i < newStrArr.length; ++i) {
            sb.append(newStrArr[i]);
            if (i != newStrArr.length - 1)
                sb.append(" ");
        }
        return sb.insert(0, teens.get(last) + " ");
    }


    public static String numberToWords(int num) {

        int place = 1;
        StringBuilder sb = new StringBuilder();

        HashMap<Integer, String> onesPlace = new HashMap<>();
        onesPlace = populateOnesPlace();

        HashMap<Integer, String> tensPlace = new HashMap<>();
        tensPlace = populateTensPlace();

        HashMap<String, String> teens = new HashMap<>();
        teens = populateTeens();

        if (num == 0)
            return "Zero";
        while (num != 0) {
            int dig = num % 10;
            num = num / 10;
            if (place == 1) {
                if (dig != 0)
                    sb.insert(0, onesPlace.get(dig));
            } else if (place == 2) {
                if (dig != 0 && dig != 1)

                    sb.insert(0, tensPlace.get(dig) + " ");
                if (dig == 1) {
                    String last = sb.toString();
                    sb = new StringBuilder();
                    sb.insert(0, teens.get(last));
                }
            } else if (place == 3) {
                if (dig != 0)
                    sb.insert(0, onesPlace.get(dig) + " Hundred ");
            } else if (place == 4) {
                if (dig != 0)
                    sb.insert(0, onesPlace.get(dig) + " Thousand ");
                else
                    sb.insert(0, "Thousand ");
            } else if (place == 5) {
                if (dig != 0 && dig != 1)
                    sb.insert(0, tensPlace.get(dig) + " ");
                if (dig == 1) {
                    sb = getTheFixForOne(sb, teens);
                }
            } else if (place == 6) {
                if (dig != 0)
                    sb.insert(0, onesPlace.get(dig) + " Hundred ");
            } else if (place == 7) {
                if (dig != 0)
                    sb.insert(0, onesPlace.get(dig) + " Million ");
            } else if (place == 8) {
                if (dig != 0 && dig != 1)
                    sb.insert(0, tensPlace.get(dig) + " ");
                if (dig == 1) {
                    sb = getTheFixForOne(sb, teens);
                }
            } else if (place == 9) {
                if (dig != 0)
                    sb.insert(0, onesPlace.get(dig) + " Hundred ");
            } else if (place == 10) {
                if (dig != 0)
                    sb.insert(0, onesPlace.get(dig) + " Billion ");
            } else /*if (place == 11)*/ {
//                if (dig != 0 && dig != 1)
//                    sb.insert(0, tensPlace.get(dig) + " ");
//                if (dig == 1) {
//                    sb = getTheFixForOne(sb, teens);
//                }
                return sb.toString();
            }
            place++;
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(numberToWords(1911223833));
    }
}
