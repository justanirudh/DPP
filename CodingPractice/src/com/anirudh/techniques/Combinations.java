package com.anirudh.techniques;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 11/5/17.
 */
// Java program to print all combination of size r in an array of size n
/*
77. Combinations
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */
class Combinations {
    public List<List<Integer>> combineAux(List<Integer> nums, int k, int start, int end, List<List<Integer>> res, List<Integer> curr, int currIdx) {

        if (curr.size() == k) {
            res.add(curr);
            return res;
        }

        //end-i + 1 >= k - currIndex (length of remaining elements >= remaining slots to fill in the curr array)
        for (int nextIdx = start; nextIdx <= end && end - nextIdx + 1 >= k - currIdx; ++nextIdx) {
            List<Integer> newCurr = new ArrayList<>(curr);
            newCurr.add(nums.get(nextIdx));
            res = combineAux(nums, k, nextIdx + 1, end, res, newCurr, currIdx + 1);
        }

        return res;

    }


    public List<List<Integer>> combine(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int curr = 1;
        while (curr <= n) {
            nums.add(curr);
            curr++;
        }
        return combineAux(nums, k, 0, n - 1, new ArrayList<>(), new ArrayList<>(), 0);
    }
}