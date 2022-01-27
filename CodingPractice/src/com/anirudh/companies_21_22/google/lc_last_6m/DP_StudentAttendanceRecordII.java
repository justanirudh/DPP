package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.HashMap;
import java.util.Map;

/*
552. Student Attendance Record II
Hard

948

168

Add to List

Share
An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:

'A': Absent.
'L': Late.
'P': Present.
Any student is eligible for an attendance award if they meet both of the following criteria:

The student was absent ('A') for strictly fewer than 2 days total.
The student was never late ('L') for 3 or more consecutive days.
Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.



Example 1:

Input: n = 2
Output: 8
Explanation: There are 8 records with length 2 that are eligible for an award:
"PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
Example 2:

Input: n = 1
Output: 3
Example 3:

Input: n = 10101
Output: 183236316
 */
/*

1: Brute force: O(3^n)

2: DP
https://leetcode.com/problems/student-attendance-record-ii/solution/
Consider only L and P
num(n) = num(n-1)L + num(n-1)P
    num(n-1)P is alright
    num(n-1)L = num(n-3)PPL + num(n-3)PLL + num(n-3)LPP + num(n-3)LLL
    The last one is not rewardable. Thats true if num(n-3) is rewardable.
    num(n-3) is rewardable and num(n-1) is not rewardable iff it ends with P i.e num(n-4)P LLL and NOT with L i.e. num(n-4)LLLL
Hence,
    num(n) = 2*num(n-1) - num(n-4)
Now consider A. Only 0 or 1 As are allowed
A = 0 => num(n)
A = 1 => Can be at any position
for(i = 0 -> n-1) => sum += num(i) * num(n-i-1)

Total = num(n) + sum
 */
public class DP_StudentAttendanceRecordII {
    //TODO

    long M = 1000000007;

    public int checkRecordWorks(int n) {
        long[] f = new long[n <= 5 ? 6 : n + 1];
        f[0] = 1;
        f[1] = 2;
        f[2] = 4;
        f[3] = 7;
        for (int i = 4; i <= n; i++)
            f[i] = ((2 * f[i - 1]) % M + (M - f[i - 4])) % M;
        long sum = f[n];
        for (int i = 1; i <= n; i++) {
            sum += (f[i - 1] * f[n - i]) % M;
        }
        return (int) (sum % M);
    }


    //--------------------------------------------------------------------------------------------------------------

    Map<Integer, Long> numCombos;
    long MOD = (long)Math.pow(10, 9) + 7;

    void populateCombinations(int size) {
        long numCombo = (2 * numCombos.get(size - 1) - numCombos.get(size - 4)) % MOD;
        numCombos.put(size, numCombo);
    }

    public int checkRecord(int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return 3;
        //ONLY consider PL
        numCombos = new HashMap<>(); //size of arr -> number of rewardable PL combinations
        numCombos.put(0, (long)1); //1 way to award
        numCombos.put(1, (long)2); //2 because only consider P and L
        numCombos.put(2, (long)4);
        numCombos.put(3, (long)7);
        for (int i = 4; i <= n; ++i)
            populateCombinations(i);
        //consider A
        int sum = 0;
        //If A = 0, all combinations of P and L
        sum += numCombos.get(n);
        //If A = 1, put it in each pos
        for (int i = 0; i < n; ++i) {
            sum += (numCombos.get(i) * numCombos.get(n - i - 1)) % MOD; //multiply
        }
        return (int) (sum % MOD);
    }
}
