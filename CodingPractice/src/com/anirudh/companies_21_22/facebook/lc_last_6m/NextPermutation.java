package com.anirudh.companies_21_22.facebook.lc_last_6m;

/*
31. Next Permutation
Medium

8251

2800

Add to List

Share
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such an arrangement is impossible, it must rearrange it to the lowest possible order (i.e., sorted in ascending order).

The replacement must be in place and use only constant extra memory.



Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]
Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100
 */

/*
1. Going from right to left, find first pair of elems s.t. a[i] < a[i + 1]
    Also means, all elems on the right of a[i] are in decreasing order
2. Find the smallest elem in [i + 1, n-1] range that is larger than a[i] : say a[j]
3. Swap a[i] and a[j]
    all elems on the right of a[i] are STILL in decreasing order
4. Reverse elems on the right of a[i]
 */
public class NextPermutation {
    int[] nums;

    int binarySearch(int num, int start, int end) { // in decreasing order arr
        int res = -1;
        while (start <= end) { //needs to be equal to
            int mid = start + (end - start) / 2;
            if (num < nums[mid]) {
                res = mid;
                start = mid + 1;
            } else { //num > nums[mid]
                end = mid - 1;
            }
        }
        return res;
    }

    void reverseArray(int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void nextPermutation(int[] nums) {
        this.nums = nums;
        int i = nums.length - 1;
        for (; i >= 1; --i) {
            if (nums[i - 1] < nums[i]) {
                break;
            }
        }
        if (i == 0) { //all elems in decreasing order from start to end; //reverse array and return
            reverseArray(0, nums.length - 1);
            return;
        }
        int idx = binarySearch(nums[i - 1], i, nums.length - 1);
        int temp = nums[i - 1]; //swap
        nums[i - 1] = nums[idx];
        nums[idx] = temp;

        reverseArray(i, nums.length - 1);
    }
}
