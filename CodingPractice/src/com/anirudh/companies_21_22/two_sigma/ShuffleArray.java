package com.anirudh.companies_21_22.two_sigma;

import java.util.Random;

/*
384. Shuffle an Array
Medium

406

435

Add to List

Share
Given an integer array nums, design an algorithm to randomly shuffle the array. All permutations of the array should be equally likely as a result of the shuffling.

Implement the Solution class:

Solution(int[] nums) Initializes the object with the integer array nums.
int[] reset() Resets the array to its original configuration and returns it.
int[] shuffle() Returns a random shuffling of the array.


Example 1:

Input
["Solution", "shuffle", "reset", "shuffle"]
[[[1, 2, 3]], [], [], []]
Output
[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]

Explanation
Solution solution = new Solution([1, 2, 3]);
solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
                       // Any permutation of [1,2,3] must be equally likely to be returned.
                       // Example: return [3, 1, 2]
solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
 */

/*
for random's bounds, be sure to include the elem itself
   for (int i = 1; i < copy.length; ++i) { //no point starting from 0 as 0 repalced by 0 will be 0
            int idx = rand.nextInt(i + 1); // for elem at index i, consider self for swapping as well
            int tmp = copy[i];
            copy[i] = copy[idx];
            copy[idx] = tmp;
        }
As one shuffle of the elements in bound is no shuffle
 */
public class ShuffleArray {

    int[] nums;
    Random rand;
    int[] original;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
        rand = new Random();
        original = nums.clone();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return original;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        for (int i = 1; i < nums.length; ++i) { //no point starting from 0 as 0 repalced by 0 will be 0
            int idx = rand.nextInt(i + 1); // for elem at index i, consider self for swapping as well
            int tmp = nums[i];
            nums[i] = nums[idx];
            nums[idx] = tmp;
        }
        return nums;
    }
}
