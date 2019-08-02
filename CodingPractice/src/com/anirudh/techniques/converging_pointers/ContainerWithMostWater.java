package com.anirudh.techniques.converging_pointers;

/**
 * Created by paanir on 12/28/16.
 */
/*
11. Container With Most Water

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines
are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis
forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.
 */
//Easier than trapping rainwater: only 2 lines exist as compared to rainwater where all exist
//similar to 2-sum
public class ContainerWithMostWater {

    //O(n^2) solution, enumerate all sols
    public static int maxAreaBad(int[] height) {
        int maxVol = 0;
        for(int i = 0; i < height.length; ++i){
            for(int j = i + 1; j < height.length; ++j){
                int vol = (j - i) * Integer.min(height[i], height[j]);
                if(vol > maxVol)
                    maxVol = vol;
            }
        }
        return maxVol;
    }

    //O(n)
    public static int maxArea(int[] height) {
        int maxVol = 0;
        int left = 0;
        int right = height.length - 1;
        while(left < right){
            int vol = (right - left) * Integer.min(height[left], height[right]);
            if(vol > maxVol)
                maxVol = vol;
            if(height[left] < height[right])
                ++left;
            else
                --right;
        }
        return maxVol;
    }

    public static void main(String[] args) {

    }
}
