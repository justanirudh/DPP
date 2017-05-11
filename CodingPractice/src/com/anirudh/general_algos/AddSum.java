package com.anirudh.general_algos;

/**
 * Created by paanir on 4/26/17.
 */
/*
258. Add Digits
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Under-construction:Could you do it without any loop/recursion in O(1) runtime?
 */
public class AddSum {
    public int addDigits(int num) {
        char[] chars = Integer.toString(num).toCharArray();
        if(chars.length == 1){
            return num;
        }
        else{
            int sum = 0;
            for(char c: chars){
                sum +=  Character.getNumericValue(c);
            }
            return addDigits(sum);
        }
    }
}
