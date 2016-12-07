package com.anirudh.general_algos;

/**
 * Created by anirudh on 6/12/16.
 */
public class FindElemOrMinInRotatedArray {

    //find the pivot (max elem) and return the next elem. O(logn)
    public static int findMinAux(int[] nums, int start, int end){

        // This condition is needed to handle the case when nums is not rotated at all
        if (end < start)  return nums[0];

        // If there is only one element left
        if (end == start) return nums[start];

        // Find mid  /*(start + end)/2;*/
        int mid = start + (end - start)/2;

        // Check if element (mid+1) is minimum element, i.e. mid is the pivot. Consider the cases like {3, 4, 5, 1, 2}
        if (mid < end && nums[mid+1] < nums[mid])
            return nums[mid+1];

        // Check if mid itself is minimum element
        if (mid > start && nums[mid] < nums[mid - 1])
            return nums[mid];

        if(nums[mid] < nums[end])
            return findMinAux(nums, start, mid - 1);
        else
            return findMinAux(nums, mid + 1, end);
    }

    public static int findMin(int[] nums) {
                return findMinAux(nums, 0, nums.length - 1);
    }

    //O(logn)
    public static int findElem(int[] nums, int num, int start, int end) {
        if(start <= end){
            int mid = (start + end)/2;
            if(nums[mid] == num)
                return mid;
            else{
                if(nums[mid] > nums[end]){
                    if(num >= nums[start] && num < nums[mid])
                        return findElem(nums, num, start, mid - 1);
                    else
                        return findElem(nums, num, mid + 1, end);
                }
                else{ //if(nums[mid] <= nums[end])
                    if(num > nums[mid] && num <= nums[end])
                        return findElem(nums, num, mid + 1, end);
                    else
                        return findElem(nums, num, start, mid - 1);
                }
            }
        }
        else
            return -99999;
    }

    public static void main(String[] args) {
        int[] nums = {8,9,10,11,12,0,1,2,3,4,5,6,7};
        int[] nums2 = {3, 1, 2};
        int[] nums3 = {6,7,8,1,2,3,4,5};
        for(int o: nums){
            System.out.println(findElem(nums, o, 0, nums.length - 1));
        }

        System.out.println(findMin(nums3));
    }

}
