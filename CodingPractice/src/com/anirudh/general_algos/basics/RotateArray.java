package com.anirudh.general_algos.basics;

/**
 * Created by paanir on 3/7/17.
 */
/*
189. Rotate Array

Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]
 */

public class RotateArray {
    //IMP: The important thing to note here is that, if I make a new array (say newNum) in the method, put elems in
    //the right order in it and ultimately point num to newNum, this WONT work. You NEED to change the array num itself.
    //This is because, in Java, parameters are references that are passed by value. The nums parameter passed is a copy of
    // the reference in the actual main method. Hence, if we make a new array and point this copy to the new array, it
    //does not affect the original pointer to the array. Hence, you need to change the num array itself.
    /*
    * Before pointing to a newNum
    *             num1(main)  ->  [arr]  <- num1 (rotate)
    *
    * After pointing to newNum
    *
    *              num1(main)  -> [arr]   num1(rotate) -> newNum
    *
    * Hence, the old num1 still points to old arr
    *
    * */

    //O(1) space, O(n) time
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void rotate(int[] nums, int k) {
        if(nums.length == 0 || nums.length == 1)
            return;
        k = k % nums.length;
        //reverse full array
        reverse(nums, 0, nums.length - 1);
        //reverse first k elements
        reverse(nums, 0, k-1);
        //reverse rest elements
        reverse(nums, k, nums.length - 1);
    }

    //---------------------------------------------------------------------------------

    //O(n) space, O(n) time
    public void rotateBad(int[] nums, int k) {
        int rot = k % nums.length;
        int buf[] = new int[rot];
        int startIndex = nums.length - rot;

        //make new buffer, put elems in it
        int buffIndex = 0;
        for (int i = startIndex; i < nums.length; ++i) {
            buf[buffIndex] = nums[i];
            buffIndex++;
        }

        //shift rest elems in the array to the right
        int back = nums.length - 1;
        for (int i = startIndex - 1; i >= 0; --i) {
            nums[back] = nums[i];
            back--;
        }

        //put bufferred elems to the right in the array
        for (int i = 0; i < buf.length; ++i) {
            nums[i] = buf[i];
        }
    }
}
