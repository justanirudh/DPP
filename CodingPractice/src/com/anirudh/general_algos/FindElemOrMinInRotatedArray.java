package com.anirudh.general_algos;

/**
 * Created by anirudh on 6/12/16.
 */
public class FindElemOrMinInRotatedArray {

/*
The minimum element is the only element whose previous element is greater than it. If there is no such element, then there
is no rotation and first element is the minimum element. Therefore, we do binary search for an element which is smaller
than the previous element.
 */

    //find the pivot (max elem) and return the next elem. O(logn)
    public static int findMinAux(int[] nums, int start, int end){

        // This condition is needed to handle the case when nums is not rotated at all. Why? because in that case, no
        // element would have its pred. bigger than itself. Hence, end would end up getting smaller than start
        if (end < start){
            return nums[0];
        }

        // If there is only one element left
        if (end == start) {
            return nums[start];
        }

        // Find mid  /*(start + end)/2;*/
        int mid = start + (end - start)/2;

        //for handling duplicates; not reqd if guaranteed no duplicates
        int midRight = mid; //rightest duplicate element
        int midLeft = mid; //leftest duplicate element
        int numsMid = nums[mid];
        while(midRight <= end && nums[midRight] == numsMid){
            ++midRight;
        }
        --midRight;
        while(midLeft >= start && nums[midLeft] == numsMid){
            --midLeft;
        }
        ++midLeft;

        // Check if element (mid+1) is minimum element (mid might not be the pivot).
        // The idea is NOT to get the pivot. The idea is to get the ONLY element whose predecessor is greater than itself
        // Consider the cases like {3, 4, 5, 1, 2}
        if (midRight < end && nums[midRight+1] < numsMid/*numsMid = nums[midRight]*/){
            return nums[midRight+1];
        }

        // Check if mid itself is minimum element
        if (midLeft > start && nums[midLeft - 1] > nums[midLeft]){
            return numsMid; //same as nums[midRight]
        }

        if(nums[mid] <= nums[end]) //equal to here is important else num7 case will return 100
            return findMinAux(nums, start, midLeft - 1);
        else
            return findMinAux(nums, midRight + 1, end);

    }

    public static int findMin(int[] nums) {

        return findMinAux(nums, 0, nums.length - 1);
    }

    //O(logn)
    public static int findElem(int[] nums, int num, int start, int end) {
        if(start <= end){ //equal to here is important
            int mid = (start + end)/2;
            if(nums[mid] == num)
                return mid;
            else{
                if(nums[mid] > nums[end]){ //pivot on the right side
                    if(num >= nums[start] && num < nums[mid]) // num is between start and mid
                        return findElem(nums, num, start, mid - 1);
                    else
                        return findElem(nums, num, mid + 1, end);
                }
                else{ //if(nums[mid] <= nums[end]) i.e pivot on the left side
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
        int[] nums2 = {3,2,1};
        int[] nums4 = {0,2,2,2,2,2,3};
        int[] nums5 = {1,2,3,4,6,8,9};
        int[] nums6 = {6,7,7,7,7,8,8,9,9,0,0,0,0,1,2,3,3,4,4,4,5};
        int[] nums7 = {10,1,10,10,10};
        for(int o: nums){
            System.out.println(findElem(nums, o, 0, nums.length - 1));
        }
            System.out.println(findMin(nums7));

    }
}
