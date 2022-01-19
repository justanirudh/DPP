package com.anirudh.companies_21_22.facebook.lc_last_6m.anki;

/*
Description
Solution
Discuss (999+)
Submissions
670. Maximum Swap
Medium

2184

127

Add to List

Share
You are given an integer num. You can swap two digits at most once to get the maximum valued number.

Return the maximum valued number you can get.



Example 1:

Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:

Input: num = 9973
Output: 9973
Explanation: No swap.


Constraints:

0 <= num <= 108
Accepted
143,285
Submissions
305,207
 */

/*
1. Iterate until you find for n[i] < n[i + 1]
2. Now from [i+1 to n-1] find the max element MAX with highest index(hence lowest contribution) thats > n[i]
3. Now from [0 to i] (decreasing order), find the first index thats < MAX
4. Swap 2. and 3.
 */
public class MaximumSwap {
    public int maximumSwap(int num) {
        String numS = Integer.toString(num);
        int p = -1;
        for (int i = 0; i < numS.length() - 1; ++i) { //1.
            if (numS.charAt(i) - '0' < numS.charAt(i + 1) - '0') { //if increasing, found place of change
                p = i;
                break;
            }
        }
        if (p == -1)
            return num;

        int max = Integer.MIN_VALUE;
        int q = -1;
        for (int i = p + 1; i < numS.length(); ++i) { //2.
            if (numS.charAt(i) - '0' >= max) { //if equal, use the higher index so that the swap is with the least significant digit of number
                max = numS.charAt(i) - '0';
                q = i;
            }
        }
        if (q == -1 || max <= numS.charAt(p) - '0') //if all equal or less than first elem
            return num;

        for (int i = 0; i <= p; i++) { //now find 1st index < max, 3.
            if (numS.charAt(i) - '0' < max) {
                p = i;
                break;
            }
        }

        //swap p and q, 4.
        char[] arr = numS.toCharArray();
        char temp = arr[p];
        arr[p] = arr[q];
        arr[q] = temp;
        return Integer.parseInt(new String(arr));
    }
}
