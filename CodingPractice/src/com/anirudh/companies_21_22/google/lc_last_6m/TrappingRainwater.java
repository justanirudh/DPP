package com.anirudh.companies_21_22.google.lc_last_6m;

/**
 * Created by paanir on 12/31/16.
 */
/*
42. Trapping Rain Water
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it
is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section)
 are being trapped. Thanks Marcos for contributing this image!


 */
/*
    O(n)-S O(n)-T approach:
        make 2 arrays, for each elem, leftMax and right max
        then iterate through them and res += Math.min(leftMax, rightMax) - height[i]
    O(1)-S O(n)-T approach:
        Use 2 pointers, left and right
        for each cell, get leftMax and rightMax
        if leftMax is smaller, calculate res+ and increment left

*/
public class TrappingRainwater {

    //O(n) time, O(1) space
    public static int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int res = 0;
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;

        while (l < r) {
            if (height[l] > leftMax)
                leftMax = height[l];
            if (height[r] > rightMax)
                rightMax = height[r];
            if (leftMax < rightMax) { //take leftMax as the height of the container
                res += Math.max(0, leftMax - height[l]);
                l++; //keeping right as is because it is bigger height so possibility of more water to be stored
            } else { //rightMax <= leftMax   take rightMax as the height of the container
                res += Math.max(0, rightMax - height[r]);
                r--;
            }
        }
        return res;
    }

    //------------------------------------------------

    //O(n) time, O(n) space
    public static int trapBad(int[] height) {

        if (height == null || height.length <= 2) //if 2 bars, nothing to store water in
            return 0;

        int len = height.length;

        //from left to right. finding left max boundary of each bar
        int[] left = new int[len];
        left[0] = height[0];
        for (int i = 1; i < len; ++i)
            left[i] = Math.max(left[i - 1], height[i]);

        //from right to left. finding right max boundary of each bar
        int[] right = new int[len];
        right[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; --i)
            right[i] = Math.max(right[i + 1], height[i]);

        int trapped = 0;
        //for every bar, (minimum of the left and right) - (height of the bar). taking min as barrage will hold water
        //equal to lesser boundaries height. And minus height, because until height, there is solid bar
        for (int i = 0; i < len; ++i)
            trapped += Math.min(left[i], right[i]) - height[i];
        return trapped;
    }

    public static void main(String[] args) {
        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(heights));
    }
}
