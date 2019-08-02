package com.anirudh.techniques;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by paanir on 12/18/16.
 */

/*
169. Majority Element
Easy

Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3

Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2


 */
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

    //O(1) space, O(n) time
    /*
    Approach 6: Boyer-Moore Voting Algorithm

Intuition

If we had some way of counting instances of the majority element as +1+1+1 and instances of any other element as −1-1−1, summing them would make it obvious that the majority element is indeed the majority element.

Algorithm

Essentially, what Boyer-Moore does is look for a suffix sufsufsuf of nums where suf[0]suf[0]suf[0] is the majority element in that suffix. To do this, we maintain a count, which is incremented whenever we see an instance of our current candidate for majority element and decremented whenever we see anything else. Whenever count equals 0, we effectively forget about everything in nums up to the current index and consider the current number as the candidate for majority element. It is not immediately obvious why we can get away with forgetting prefixes of nums - consider the following examples (pipes are inserted to separate runs of nonzero count).

[7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]

Here, the 7 at index 0 is selected to be the first candidate for majority element. count will eventually reach 0 after index 5 is processed, so the 5 at index 6 will be the next candidate. In this case, 7 is the true majority element, so by disregarding this prefix, we are ignoring an equal number of majority and minority elements - therefore, 7 will still be the majority element in the suffix formed by throwing away the first prefix.

[7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 5, 5, 5, 5]

Now, the majority element is 5 (we changed the last run of the array from 7s to 5s), but our first candidate is still 7. In this case, our candidate is not the true majority element, but we still cannot discard more majority elements than minority elements (this would imply that count could reach -1 before we reassign candidate, which is obviously false).

Therefore, given that it is impossible (in both cases) to discard more majority elements than minority elements, we are safe in discarding the prefix and attempting to recursively solve the majority element problem for the suffix. Eventually, a suffix will be found for which count does not hit 0, and the majority element of that suffix will necessarily be the same as the majority element of the overall array.
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
