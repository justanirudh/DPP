package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by paanir on 3/29/17.
 */
//Under construction
public class ThirdMax {
    public int thirdMax(int[] nums) {
        if(nums.length == 1)
            return nums[0];
        if(nums.length == 2)
            return Math.max(nums[0], nums[1]);
        //for arrays of length >= 3
        //initialize record array
        ArrayList<Integer> record = new ArrayList<>();
        for(int i = 0; i < nums.length; ++i){
            int curr = nums[i];
            if(!record.contains(curr)){//unique elements
                if(record.size() < 3){
                    record.add(curr);
                    Collections.sort(record); //O(1) as size < 3
                }
                else{ //size >=3
                    int j = 0;
                    while(record.get(j) < curr)
                        j++;

                }
            }
        }
        if(record.size() < 3)
            return record.get(record.size() - 1);
        else
            return record.get(0);
    }
}
