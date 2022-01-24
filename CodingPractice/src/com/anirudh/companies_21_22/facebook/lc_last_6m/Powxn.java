package com.anirudh.companies_21_22.facebook.lc_last_6m;

/**
 * Created by paanir on 6/16/17.
 */
/*
50. Pow(x, n)
Medium

3667

4776

Add to List

Share
Implement pow(x, n), which calculates x raised to the power n (i.e., xn).



Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25.
 */

/*
if power % 2 == 0, ans = (x^n/2)^2.
if power % 2 = 1, ans = x * (x^n/2)^2
*/
public class Powxn {

    double fastPower(double x, int n) {
        if (n == 0)
            return 1;
        double half = fastPower(x, n / 2); //memoization
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x; //1 extra x because n/2 looses the 1 extra for odd numbers
        }
    }

    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return fastPower(x, n);
    }
}
