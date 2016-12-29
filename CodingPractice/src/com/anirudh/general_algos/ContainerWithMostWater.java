package com.anirudh.general_algos;

/**
 * Created by paanir on 12/28/16.
 */
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
