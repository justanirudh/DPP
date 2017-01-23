package com.anirudh;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by paanir on 1/22/17.
 */
public class BaseArithmetic {


    public static void main(String[] args) {
        HashMap<String, Integer> baseMap = new HashMap<>();
        baseMap.put("0", 1); //digit to base
        baseMap.put("1", 2);
        baseMap.put("2", 3);
        baseMap.put("3", 4);
        baseMap.put("4", 5);
        baseMap.put("5", 6);
        baseMap.put("6", 7);
        baseMap.put("7", 8);
        baseMap.put("8", 9);
        baseMap.put("9", 10);
        baseMap.put("A", 11);
        baseMap.put("B", 12);
        baseMap.put("C", 13);
        baseMap.put("D", 14);
        baseMap.put("E", 15);
        baseMap.put("F", 16);

        Scanner stdin = new Scanner(System.in);
        String num1 = stdin.nextLine();
        int maxBaseValue = 0;
        for (int i = 0; i < num1.length(); ++i) {
            if (baseMap.get(Character.toString(num1.charAt(i))) > maxBaseValue) {
                maxBaseValue = baseMap.get(Character.toString(num1.charAt(i)));
            }
        }
        int sum1 = 0;
        for(int i = 0; i < num1.length(); ++i){
            sum1 += (baseMap.get(Character.toString(num1.charAt(i))) - 1) * Math.pow(maxBaseValue, num1.length() - 1 - i);
        }

        String num2 = stdin.nextLine();
        int maxBaseValue2 = 0;
        for (int i = 0; i < num2.length(); ++i) {
            if (baseMap.get(Character.toString(num2.charAt(i))) > maxBaseValue2) {
                maxBaseValue2 = baseMap.get(Character.toString(num2.charAt(i)));
            }
        }
        int sum2 = 0;
        for(int i = 0; i < num2.length(); ++i){
            sum2 += (baseMap.get(Character.toString(num2.charAt(i))) - 1) * Math.pow(maxBaseValue2, num2.length() - 1 - i);
        }
        System.out.println(sum1 + sum2);
    }
}
