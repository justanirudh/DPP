package com.anirudh.companies_21_22.facebook.lc_last_6m;

/**
 * Created by paanir on 8/29/21.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Expression Add Operators
 * Hard
 * <p>
 * 1859
 * <p>
 * 318
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a string num that contains only digits and an integer target, return all possibilities to add the binary operators
 * '+', '-', or '*' between the digits of num so that the resultant expression evaluates to the target value.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Example 2:
 * <p>
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Example 3:
 * <p>
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 * <p>
 * Input: num = "00", target = 0
 * Output: ["0*0","0+0","0-0"]
 * Example 5:
 * <p>
 * Input: num = "3456237490", target = 9191
 * Output: []
 */

/*
 send {curr, currVal, currExp, lastNum}
 base case

 for(int end = curr -> end of string)
    if (num(curr) = 0 && end > curr)  //handle nums starting with 0
        just break; //example 01234
    num = substring(curr, end+1)
    if(curr = 0) //very first num
        recurse(curr + 1, num, num, num )
    else
        recurse(curr + 1, currVal + num, "currVal + num", num)  //for +
        recurse(curr + 1, currVal - num, "currVal - num", -num)  //for -
        recurse(curr + 1, currVal - preNum + (preNum * num), "currVal * num", (preNum * num))  //for *
 */
public class ExpressionAddOperators {

    String num;
    int target;
    List<String> res;

    public void dfs(int start, long currVal, String currExp, long lastNum) {
        if (start == num.length() && currVal == target) { //base case
            res.add(currExp);
            return;
        }

        for (int end = start; end < num.length(); end++) { //get all substrings
            if (end > start && num.charAt(start) == '0') {// multi-digit num cannot start with 0
                break;
            }
            long curNum = Long.parseLong(num.substring(start, end + 1));

            if (start == 0) {
                dfs(end + 1, curNum, Long.toString(curNum), curNum); //for the very first case
            } else {
                dfs(end + 1, currVal + curNum, currExp + "+" + curNum, curNum);
                dfs(end + 1, currVal - curNum, currExp + "-" + curNum, -curNum);
                dfs(end + 1, currVal - lastNum + (lastNum * curNum),
                        currExp + "*" + curNum, lastNum * curNum);
            }
        }
    }

    public List<String> addOperators(String num, int target) {
        this.num = num;
        this.target = target;
        res = new ArrayList<>();
        if (num.length() == 0) {
            return new ArrayList<>();
        }

        dfs(0, 0, "", 0);
        return res;
    }
}
