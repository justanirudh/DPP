package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 4/4/17.
 */

//under_construction
public class Permutations {
    public static ArrayList<Integer> addElement(ArrayList<Integer> arr, int elem) {

    }

    public List<List<Integer>> permute(int[] nums) {

        ArrayList<ArrayList<Integer>> permutations = new ArrayList<>();
        if(nums.length == 0)
            return null;

        ArrayList<Integer> first = new ArrayList<>();
        first.add(nums[0]);
        permutations.add(first);

        for(int i = 1; i < nums.length; ++i){
            ArrayList<ArrayList<Integer>> permsTemp = new ArrayList<ArrayList<Integer>>();

            for(ArrayList<Integer> arr : permutations){
                ArrayList<Integer> newArr = addElement(arr, i);
                permsTemp.add(newArr);
            }
            permutations = permsTemp;
        }
        return permutations;
    }
}
