package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by paanir on 6/22/17.
 */
/*
47. Permutations II

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 */
public class PermutationsUnique {
    public static List<Integer> addElement(List<Integer> arr, int num, int index) {
        List<Integer> newList = new ArrayList<Integer>(arr);
        newList.add(index, num);
        return newList;
    }

    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> permutations = new ArrayList<>();
        if (nums.length == 0)
            return permutations;

        ArrayList<Integer> first = new ArrayList<>();
        first.add(nums[0]);
        permutations.add(first);

        for (int n = 1; n < nums.length; ++n) {
            int num = nums[n];
            List<List<Integer>> permsTemp = new ArrayList<>();

            for (List<Integer> arr : permutations) {
                for (int i = 0; i <= arr.size(); i++) {
                    List<Integer> newArr = addElement(arr, num, i);
                    permsTemp.add(newArr);
                }
            }
            permutations = permsTemp;
        }
        return permutations;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = permute(nums);
        return new ArrayList<>(new HashSet<>(res));

    }
}
