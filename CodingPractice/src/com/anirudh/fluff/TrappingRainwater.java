package com.anirudh.fluff;

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
public class TrappingRainwater {

    public static int trap(int[] height) {

        if (height == null || height.length <= 2) //if 2 bars, nothing to store water in
            return 0;

        int[] left = new int[height.length];
        left[0] = height[0];
        for (int i = 1; i < height.length; ++i) //from left to right. finding left boundary of barrage. left max boundary of each bar
            left[i] = Integer.max(left[i - 1], height[i]);

        int[] right = new int[height.length];
        right[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; --i) //from right to left. finding right boundary of barrage. right max boundary of each bar
            right[i] = Integer.max(right[i + 1], height[i]);

        int trapped = 0;
        //for every bar, minimum of the left and right minus height of the bar. taking min as barrage will hold water
        //equal to lesser boundaries height.
        for (int i = 0; i < height.length; ++i)
            trapped += Integer.min(left[i], right[i]) - height[i];
        return trapped;
    }

    public static void main(String[] args) {
        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(heights));
    }
}
