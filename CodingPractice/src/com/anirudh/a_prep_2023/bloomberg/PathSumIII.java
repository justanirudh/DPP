package com.anirudh.a_prep_2023.bloomberg;

import com.anirudh.datastructures.trees.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 8/3/19.
 */
/*
437. Path Sum III
Medium

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
//TODO

}

