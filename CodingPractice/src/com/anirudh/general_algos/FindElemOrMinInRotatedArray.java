package com.anirudh.general_algos;

/**
 * Created by anirudh on 6/12/16.
 */
public class FindElemOrMinInRotatedArray {
//DONT think in terms of pivot. Think in terms of finding a number whose pred. is greater than it. That's it.


/*
The minimum element is the only element whose previous element is greater than it. If there is no such element, then there
is no rotation and first element is the minimum element. Therefore, we do binary search for an element which is smaller
than the previous element.
 */

    //Both cases dont work with reverse sorted array. Why? Because you cannot rotate a sorted aray to form a reverse
    // sorted array

    //O(logn)
    public static int findMinAux(int[] nums, int start, int end){

        // This condition is needed to handle the case when nums is not rotated at all. Why? because in that case, no
        // element would have its pred. bigger than itself. Hence, end would end up getting smaller than start.
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
        if (midRight < end && nums[midRight+1] < numsMid){ /*numsMid = nums[midRight]*/
            return nums[midRight+1];
        }

        // Check if mid itself is minimum element
        if (midLeft > start && nums[midLeft - 1] > nums[midLeft]){
            return numsMid; //same as nums[midRight]
        }

        if(nums[mid] <= nums[end]) //equals should be here if nums wrap around. eg. num7
            return findMinAux(nums, start, midLeft - 1);
        else
            return findMinAux(nums, midRight + 1, end);

    }

    public static int findMin(int[] nums) {

        return findMinAux(nums, 0, nums.length - 1);
    }

    //O(logn)
    public static int findElem(int[] nums, int num, int start, int end) {
        if(start <= end){ //equals here is important
            int mid = (start + end)/2;
            if(nums[mid] == num)
                return mid;
            else{
                //for handling duplicates
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

                if(nums[mid] > nums[end]){ //pivot on the right side; equals should be here if no duplicates
                    if(num >= nums[start] && num < nums[mid]) // num is between start and mid
                        return findElem(nums, num, start, midLeft - 1);
                    else
                        return findElem(nums, num, midRight + 1, end);
                }
                else if (nums[mid] < nums[end]){ //if(nums[mid] < nums[end]) i.e pivot on the left side
                    if(num > nums[mid] && num <= nums[end])
                        return findElem(nums, num, midRight + 1, end);
                    else
                        return findElem(nums, num, start, midLeft - 1);
                }
                else{ //for handling duplicates; nums[mid] = nums[end]; potential wrap around. Eg nums10 and nums11
                    if(nums[mid] != nums[start]){ //if this is not the case, number is sure to be on the right
                        return findElem(nums, num, start, midLeft - 1);
                    }
                    else{ //we don't know where it can be. Eg. nums8 and nums11. hence, testing both sides
                        int left = findElem(nums, num, start, midLeft - 1);
                        if(left == -1)
                            return findElem(nums, num, midRight + 1, end);
                        else
                            return left;
                    }
                }
            }
        }
        else
            return -1;
    }

    public static void main(String[] args) {
        int[] nums = {8,9,10,11,12,0,1,2,3,4,5,6,7};
        int[] nums2 = {2, 1};
        int[] nums4 = {0,2,2,2,2,2,3};
        int[] nums5 = {1,2,3,4,6,8,9};
        int[] nums6 = {6,7,7,7,7,8,8,9,9,0,0,0,0,1,2,3,3,4,4,4,5};
        int[] nums7 = {10,1,10,10,10};
        int[] nums8 = {1,1,3,1};
        int[] nums9 = {10,10, 10, 10, 10, 1,10};
        int[] nums10 = {3,1,1};
        int[] nums11 = {1,3,1,1,1};

        int[] arr = nums11;
        for(int o: arr){
            System.out.println(findElem(arr, 3, 0, arr.length - 1));
        }
        System.out.println(findMin(nums8));

    }
}
