package com.anirudh.companies_21_22.facebook.lc_last_6m;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by paanir on 4/4/17.
 */
/*
46. Permutations
Medium

9000

171

Add to List

Share
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]


Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
Accepted
1,055,020
Submissions
1,483,021
 */

/*
    Use backtracking
    start with first = 0, swap it with each elem, recurse to do the same with first = 1, backtrack the swap

If the first integer to consider has index n that means that the current permutation is done.
Iterate over the integers from index first to index n - 1.
    Place i-th integer first in the permutation, i.e. swap(nums[first], nums[i]).
    Proceed to create all permutations which starts from i-th integer : backtrack(first + 1).
    Now backtrack, i.e. swap(nums[first], nums[i]) back.

 */
public class Permutations {

    List<List<Integer>> output;
    List<Integer> nums;

    void backtrack(int first) { //swap each number with the number at first index
        if (first == nums.size()) {
            output.add(new ArrayList<>(nums));
            return;
        }

        for (int i = first; i < nums.size(); ++i) {
            Collections.swap(nums, first, i);
            backtrack(first + 1); // all perms start from the number at first index
            Collections.swap(nums, first, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        output = new ArrayList<>();
        this.nums = new ArrayList<>();
        for (int num : nums)
            this.nums.add(num);
        backtrack(0);
        return output;
    }

}
