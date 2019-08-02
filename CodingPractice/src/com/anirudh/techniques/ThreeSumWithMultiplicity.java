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
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : A) {
            if (!counts.containsKey(num))
                counts.put(num, 0);
            counts.put(num, counts.get(num) + 1);
        }
        Arrays.sort(A);
        int count = 0;
        for (int i = 0; i < A.length; ++i) {
            int left = i + 1;
            int right = A.length - 1;
            int sum = target - A[i];
            while (left < A.length && left < right) {
                int currSum = A[left] + A[right];
                if (currSum == sum) {
                    int leftNum = A[left];
                    int rightNum = A[right];

                    count += counts.get(leftNum) * counts.get(rightNum);

                    while (left < A.length && A[left] == leftNum) {
                        left++;
                    }
                    while (right >= 0 && A[right] == rightNum) {
                        right--;
                    }
                } else if (currSum < sum) {
                    left++;
                } else
                    right--;
            }
        }
        return count;
    }
}
