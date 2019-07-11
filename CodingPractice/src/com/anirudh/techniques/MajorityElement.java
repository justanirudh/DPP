package com.anirudh.techniques;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by paanir on 12/18/16.
 */

//Given an integer array of size n, return the number of elements that appear more than ⌊ n/2 ⌋ times.
public class MajorityElement {


    //O(n) space, O(n) time
    public static int majorityElement(int[] nums) {
        HashMap<Integer, Integer> maj = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            if (maj.containsKey(num))
                maj.put(num, maj.get(num) + 1);
            else
                maj.put(num, 1);
        }
        for (Integer n : maj.keySet()) {
            System.out.println(n + "," + maj.get(n));
        }
        for (Integer n : maj.keySet()) {
            if (maj.get(n) > nums.length / 2)
                return n;
        }
        return -9999;
    }

    //O(1) space, O(1) time
    /*
    When applying the algorithm on an array, only one of below two cases might happen:

    A. the first candidate’s counter never drops to zero through out the array, or;

    B. the first candidate’s counter drops to zero at some point (reset point).

If A, then apparently this candidate is the majority;

If B, then (let’s say we have an array of n elements, and by the first time counter drops to zero we have gone through x elements so far):

    If the real majority element M never appeared in the subarray before the reset point, then it will still be the majority
    in the remaining subarray — do the algorithm again on remaining subarray;

    If the majority element M has appeared in the subarray before reset point, then it must only have appeared up to x/2
    times (because counter is now zero). Thus in remaining subarray we have (n-x) elements in total, of which at least
    (n/2 +1 – x/2) = (n-x)/2 +1 are M [to be more precise, it's at least (floor(n/2) +1 - x/2) = floor((n-x)/2)+1 ],
    making it still the majority in remaining subarray — do the algorithm again on remaining subarray;

And there we have it like a recursive function. Note that this is when there IS a majority (more than half) in the array.
When there's no majority, this process will give you a wrong candidate, that's why you always have to do a second pass to check.
     */
    public static int majorityElementLTMV(int[] nums) { //Linear Time Majority Vote
        int result = 0, count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                result = nums[i];
                count = 1;
            } else if (result == nums[i]) {
                count++;
            } else {
                count--;
            }
        }

        return result;
    }

    //Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
    // The algorithm should run in linear time and in O(1) space.

    //O(n) s, O(n) time
    public static List<Integer> majorityElement2(int[] nums) {
        HashMap<Integer, Integer> maj = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            if (maj.containsKey(num))
                maj.put(num, maj.get(num) + 1);
            else
                maj.put(num, 1);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer n : maj.keySet()) {
            if (maj.get(n) > nums.length / 3)
                ans.add(n);
        }
        return ans;
    }

    //O(1) s, O(n) time
    public static List<Integer> majorityElement2LTMV(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();

        Integer result1 = null, result2 = null;  //only 2 can exist
        int count1 = 0, count2=0;

        for (int i = 0; i < nums.length; i++) {
             if (result1 != null && result1 == nums[i]) {
                count1++;
            }else if (result2 != null && result2 == nums[i]) {
                count2++;
            } else if (count1 == 0) {
                result1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                result2 = nums[i];
                count2 = 1;
            }
            else{
                count1--;
                count2--;
            }
        }

        count1 = count2 = 0;

        //Checking if count is n/3
        for (int i = 0; i < nums.length; i++) {
            if(result1 != null && nums[i] == result1)
                count1++;
            if(result2 != null && nums[i] == result2)
                count2++;
        }

        if(count1 > nums.length/3)
            res.add(result1);
        if(count2 > nums.length/3)
            res.add(result2);

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};
        System.out.print(majorityElement2LTMV(nums));

    }
}
