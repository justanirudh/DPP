package com.anirudh.techniques;

/**
 * Created by paanir on 9/20/17.
 */
/*
215. Kth Largest Element in an Array

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

//Good solutions: https://leetcode.com/problems/kth-largest-element-in-an-array/description/
public class KthLargestElementInAnArray {
//TODO later
    public static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static int partition(int[] arr, int start, int end) {
        int pivotElem = arr[start];
        int left = start;
        for (int right = start + 1; right <= end; right++) {
            if (arr[right] < pivotElem) {
                left++;
                swap(arr, right, left);
            }
        }
        //put pivot in right location
        swap(arr, left, start);
        return left;
    }

    public int randSelect(int[] nums, int start, int end, int rank) {
        if (start == end) {
            return nums[start];
        }
        int pivot = partition(nums, start, end); //actual index
        int currRank = pivot - start + 1;
        if (currRank == rank)
            return nums[pivot];
        else if (currRank < rank)
            return randSelect(nums, start, pivot - 1, rank);
        else
            return randSelect(nums, pivot + 1, end, rank - currRank);

    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return -1;
//        List<Integer> arrl = new ArrayList<>();
//        for(int num : nums){
//            arrl.add(num);
//        }
        int rank = nums.length - k + 1;

        return randSelect(nums, 0, nums.length - 1, rank);

    }
}
