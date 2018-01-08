package com.anirudh.general_algos;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by paanir on 12/10/17.
 */
public class NondecreasingArray {
    static boolean isSorted(List<Integer> list) {
        for(int i = 0; i < list.size() - 1; ++i){
            if(list.get(i) > list.get(i + 1))
                return false;
        }
        return true;
    }


    static public boolean checkPossibility(int[] nums) {
        if(nums == null || nums.length == 0)
            return false;
        Integer[] nums2 = new Integer[nums.length];
        for(int i = 0; i < nums.length; ++i){
            nums2[i] = nums[i];
        }
        for(int i = 0; i < nums2.length; ++i){
            List<Integer> list = new ArrayList<>(Arrays.asList(nums2));
            list.remove(i);
            if(isSorted(list))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] foo = {4,3,2};
        System.out.println(checkPossibility(foo));
    }
}
