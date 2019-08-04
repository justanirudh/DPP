package com.anirudh.datastructures.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 8/3/19.
 */
/*
437. Path Sum III
Easy

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

 */

/*
Like 560. Subarray Sum Equals K: find subarray whos numbers sum to a given number:
T: O(N)
S : O(N^2) {N + N-1 + N-2 + . . . }

 */
public class PathSumIII {

    private int sum;

    private int calculatePathSums(Map<Integer, Integer> map, TreeNode tn, int runningSum) {
        if (tn == null)
            return 0;
        runningSum += tn.val; //add to running sum

        int complement = runningSum - sum; //get complement
        int res = map.containsKey(complement) ? map.get(complement) : 0; //first get res, then add to map

        map.putIfAbsent(runningSum, 0);  //add running sum to map
        map.put(runningSum, map.get(runningSum) + 1);

        return res +
                calculatePathSums(new HashMap<>(map), tn.left, runningSum) +
                calculatePathSums(new HashMap<>(map), tn.right, runningSum);

    }

    public int pathSum(TreeNode root, int sum) {
        this.sum = sum;

        Map<Integer, Integer> map = new HashMap<>(); //running_sum map
        map.put(0, 1); //base case. Required if we want to include root in a path as well

        return calculatePathSums(map, root, 0);
    }
}

