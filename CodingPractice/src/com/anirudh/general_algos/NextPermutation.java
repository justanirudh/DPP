package com.anirudh.general_algos;

/**
 * Created by paanir on 6/15/17.
 */
//# 31 under-construction
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if(nums.length == 0 || nums.length == 1)
            return;
        int peak = nums.length - 1;
        for(; peak >= 0; peak--){
            if(peak == 0 || nums[peak - 1] < nums[peak])
                break;
        }
        if(peak == 0){ //reverse arr
            int s = 0;
            int e = nums.length - 1;
            while(e > s){
                int temp = nums[s];
                nums[s] = nums[e];
                nums[e] = temp;
                e--;
                s++;
            }
        }
        else{ //swap the (peak-1)th elem with lowest of nums[peak to n-1]. Then sort nums[peak,n-1]
           int
        }
    }
}
