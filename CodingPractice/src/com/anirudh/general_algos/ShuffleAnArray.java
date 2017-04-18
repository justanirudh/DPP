package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 4/17/17.
 */

//#384.Naive. Under-construction
public class ShuffleAnArray {
    List<List<Integer>> shuffles;
    int[] nums;

    private List<Integer> addElement(List<Integer> arr, int num, int index) {
        List<Integer> newList = new ArrayList<Integer>(arr);
        newList.add(index, num);
        return newList;
    }

    private List<List<Integer>> permute(int[] nums) {

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

    private int[] toIntArray(List<Integer> list){
        int[] ret = new int[list.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = list.get(i);
        return ret;
    }

    public ShuffleAnArray(int[] nums) {
        this.nums = nums;
        shuffles = permute(nums);
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int rand_indx = (int )(Math.random() * 50); //0 to 49
        List<Integer> shuffled = shuffles.get(rand_indx);

        return toIntArray(shuffled);
    }
}
