package com.anirudh.companies_21_22.two_sigma;

import java.util.ArrayList;
import java.util.List;

/*
Similar to Combinations question
Use backtracking:
    For each start -> end
        Add a number to current list
        send the list for next recursion
        remove the elem from curr list
 */
public class SubsetsSizeK {
    int[] nums;
    int k;
    List<List<Integer>> res = new ArrayList<>();

    private void backtrack(int first, List<Integer> curr) {

        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = first; i < nums.length; ++i) {
            curr.add(nums[i]);
            backtrack(i + 1, curr);
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> getSubsets(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        backtrack(0, new ArrayList<>());
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int k = 2;
        List<List<Integer>> res = new SubsetsSizeK().getSubsets(nums, k);
        for (int i = 0; i < res.size(); ++i) {
            for (int num : res.get(i)) {
                System.out.print(num + ",");
            }
            System.out.println();
        }
    }
}
