package com.anirudh.leetcode;

import java.util.*;

/**
 * Created by anirudh on 27/11/16.
 */
/*
LeetCode – Triangle_DP (Java)

Given a triangle, find the minimum path sum from top to bottom.
Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */
public class Triangle_DP {

    //Memoization,else would be O(2^n)
    static HashMap<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();


    static int getOrCalculate(List<List<Integer>> triangle, int row, int index) {
        if(map.containsKey(row) && map.get(row).containsKey(index))
            return map.get(row).get(index);
        else{
            int sum = getSum(triangle, row, index); //calculating
            if(map.containsKey(row)){
                map.get(row).put(index, sum);
            }
            else{
                HashMap<Integer, Integer> m2 = new HashMap<>();
                m2.put(index, sum);
                map.put(row, m2);
            }
            return sum;
        }
    }

    public static int getSum(List<List<Integer>> triangle, int row, int index){
        if(row < triangle.size()){
            int sumleft, sumright;
            //had to make a map of map becuase java does not support tuples
            //basically checking if its in hashmap, else calculating
            sumleft = getOrCalculate(triangle, row + 1, index);
            sumright = getOrCalculate(triangle, row + 1, index + 1);
            
            return triangle.get(row).get(index) + Math.min(sumleft, sumright);
        }
        else
            return 0;
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.isEmpty())
            return 0;
        int sum = triangle.get(0).get(0);
        return sum + Math.min(getSum(triangle, 1, 0), getSum(triangle, 1, 1));
    }

    public static void main(String[] args){
        List<List<Integer>> triangle = Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3,4),
                Arrays.asList(6,5,7),
                Arrays.asList(4,1,8,3));
        System.out.println(minimumTotal(triangle));
    }
}
