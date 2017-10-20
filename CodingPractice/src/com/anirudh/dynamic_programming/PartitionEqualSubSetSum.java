package com.anirudh.dynamic_programming;

/**
 * Created by paanir on 10/3/17.
 */
/*
416. Partition Equal Subset Sum

Given a non-empty array containing only positive integers, find if the array can be partitioned into
two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.

 */
//Equivalent to SubSet Sum; 0/1 knapsack
public class PartitionEqualSubSetSum {
    public boolean canPartition(int[] nums) {
        //get sum of all numbers
        int sum = 0;
        for (int i : nums)
            sum += i;
        if (sum % 2 != 0) //if not even, cannot be divided into 2 arrays of same sum
            return false;
        int target = sum / 2;
        //rows is weights from 0 to target, cols is items from 0 to nums.length
        boolean[][] matrix = new boolean[target + 1][nums.length + 1];
        for (int i = 0; i <= target; ++i) {
            for (int j = 0; j <= nums.length; ++j) {
                if (i == 0)
                    matrix[i][j] = true; //for sum = 0, any subset including null subset will do
                else if (j == 0) {
                    matrix[i][j] = false; //i = 0, j = 0 will be true already
                } else {
                    matrix[i][j] = matrix[i][j - 1];
                    if (!matrix[i][j]) { //if it is true, then all arrays that have more numbers will also be true
                        if (i >= nums[j - 1]) { //sum >= the magnitude of current element
                            matrix[i][j] = matrix[i - nums[j - 1]][j - 1]; //remove current elem and its corresponding weight
                        }
                    }
                }
            }
        }
        return matrix[target][nums.length];
    }

}
