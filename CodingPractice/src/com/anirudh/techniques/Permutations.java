package com.anirudh.techniques;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 4/4/17.
 */
/*
46. Permutations
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
public class Permutations {
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

            for (List<Integer> arr : permutations) {//for each permutation so far
                for (int i = 0; i <= arr.size(); i++) {//in every index of the above permutation
                    List<Integer> newArr = addElement(arr, num, i);//add the element
                    permsTemp.add(newArr);//remove the previous and add the new one (essentially)
                }
            }
            permutations = permsTemp;//replace old with new
        }
        return permutations;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> perms = permute(nums);

        for (List<Integer> perm : perms) {
            for (Integer i : perm) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }
}
