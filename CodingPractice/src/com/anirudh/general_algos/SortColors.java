package com.anirudh.general_algos;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 9/27/17.
 */
/*
75. Sort Colors
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
 */
public class SortColors {
    public void sortColors(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            Integer count = map.putIfAbsent(i, 1); //is null if was not present previously
            if(count != null)
                map.put(i, map.get(i) + 1);
        }
        int i = 0;
        if(map.containsKey(0)){
            int countRed = i + map.get(0);
            for(; i < countRed; ++i)
                nums[i] = 0;
        }
        if(map.containsKey(1)){
            int countWhite = i + map.get(1);
            for(; i < countWhite; ++i)
                nums[i] = 1;
        }
        if(map.containsKey(2)){
            int countBlue = i + map.get(2);
            for(; i < countBlue; ++i)
                nums[i] = 2;
        }
    }

}
