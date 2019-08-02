package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by paanir on 3/29/17.
 */
/*
414. Third Maximum Number
Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the
maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
 */
public class ThirdMax {

    public int thirdMax(int[] nums) { //can input the rank of the number
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);
        //for arrays of length >= 3
        List<Integer> record = new ArrayList<>();
        for (int curr : nums) {
            if (!record.contains(curr)) {//unique elements
                if (record.size() < 3) {
                    record.add(curr);
                    Collections.sort(record); //O(1) as size < 3
                } else { //size >=3
                    int j = 0;
                    while (j < 3 && record.get(j) < curr)
                        j++;
                    if (j > 0) { // not for j = 0 as it will then prepend
                        record.add(j, curr); //add at that index
                        record.remove(0); //remove first element
                    }
                }
            }
        }
        if (record.size() < 3)
            return record.get(record.size() - 1);
        else
            return record.get(0);
    }

    public int thirdMaxBetter(int[] nums) {
        Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;
        for (Integer n : nums) {
            if (n.equals(max1) || n.equals(max2) || n.equals(max3))
                continue;
            if (max1 == null || n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) {
                max3 = max2;
                max2 = n;
            } else if (max3 == null || n > max3) {
                max3 = n;
            }
        }
        return max3 == null ? max1 : max3;
    }
}
