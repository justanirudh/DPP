package com.anirudh.techniques;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 8/1/19.
 */
/*
923. 3Sum With Multiplicity
Medium

Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.

As the answer can be very large, return it modulo 10^9 + 7.



Example 1:

Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation:
Enumerating by the values (A[i], A[j], A[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.

Example 2:

Input: A = [1,1,2,2,2,2], target = 5
Output: 12
Explanation:
A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.



Note:

    3 <= A.length <= 3000
    0 <= A[i] <= 100
    0 <= target <= 300

 */
public class ThreeSumWithMultiplicity {

    public int threeSumMulti(int[] A, int target) {
        if (A == null || A.length == 0)
            return -1;

        /*
            MOD is distributive over addition
            (x % c) + (y % c) = (x + y) % c
         */

//        int MOD = 1_000_000_007; //modulo 10^9 + 7
        double MOD = Math.pow(10, 9) + 7;

        Arrays.sort(A);
        int count = 0;
        for (int i = 0; i < A.length; ++i) {
            int left = i + 1;
            int right = A.length - 1;
            int currTarget = target - A[i];

            while (left < right) {
                int sum = A[left] + A[right]; //current target

                if (sum == currTarget) {
                    int leftNum = A[left];
                    int rightNum = A[right];
                    if (leftNum != rightNum) { //if they are different, count frequency of each and add to res
                        int leftCount = 0, rightCount = 0;
                        while (left < A.length && A[left] == leftNum) {
                            left++;
                            leftCount++;
                        }
                        while (right >= 0 && A[right] == rightNum) {
                            right--;
                            rightCount++;
                        }
                        count += leftCount * rightCount;  //number of ways to get the answer
                        count %= MOD;

                    } else { //if both are equal, total combinations is nC2 (number of ways 2 of these can be selected; selecting 2 because we need total 3: A[i], curr, curr)
                        int size = right - left + 1;
                        count += (size * (size - 1) / 2);
                        count %= MOD;
                        break; //break after this as and change pivot (i)
                    }
                } else if (sum < currTarget) {
                    left++;
                } else
                    right--;
            }
        }
        return count;
    }
}
