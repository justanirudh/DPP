package com.anirudh.companies_21_22.facebook.lc_last_6m.anki;

/*
1004. Max Consecutive Ones III
Medium

3882

53

Add to List

Share
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.



Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length
Accepted
183,415
Submissions
293,842
 */
/*
Use Sliding Window to get <=k 0s in the window
Use the angle question sliding window method
 */
public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int slow = 0;
        int count = 0;
        int len = Integer.MIN_VALUE;
        for(int fast = 0; fast < nums.length; ++fast) {
            if(nums[fast] == 0)
                count++;
            while(slow < nums.length && count > k) {
                if(nums[slow] == 0)
                    count--;
                slow++;
            }
            len = Math.max(len, fast - slow + 1);
        }
        return len;
    }
}
