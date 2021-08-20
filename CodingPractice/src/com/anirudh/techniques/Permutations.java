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


    /*
    Complexity:
    for calculating N!
    time complexity = N! + (N-1)! + (N-2)! + . . . .1! = O(N * N!)
     */
    public static List<Integer> addElement(List<Integer> perm, int num, int index) {
        List<Integer> newList = new ArrayList<>(perm);
        newList.add(index, num);
        return newList;
    }

    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> perms = new ArrayList<>();
        if (nums.length == 0)
            return perms;

        List<Integer> first = new ArrayList<>();
        first.add(nums[0]);
        perms.add(first);

        for (int n = 1; n < nums.length; ++n) {
            int num = nums[n];
            List<List<Integer>> newPerms = new ArrayList<>();

            for (List<Integer> perm : perms) {//for each permutation so far
                for (int i = 0; i <= perm.size(); i++) {//in every index of the above permutation
                    List<Integer> newArr = addElement(perm, num, i);//add the element
                    newPerms.add(newArr);//remove the previous element (with one less number) and add the new one (essentially)
                }
            }
            perms = newPerms;//replace old with new
        }
        return perms;
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
