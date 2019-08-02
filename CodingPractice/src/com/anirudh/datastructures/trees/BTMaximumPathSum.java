package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 7/22/19.
 */
/*
124. Binary Tree Maximum Path Sum
Hard

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6

Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42


 */
/*
At every node we check
1. sum starting from the root to one of the leaves
2. sum that goes through the root to bpoth leaves
And find max of the two
 */
public class BTMaximumPathSum {

    int max = Integer.MIN_VALUE;

    public int maxPathSumAux(TreeNode root) {
        if (root == null)
            return 0;
        int sumFromLeftChild = Math.max(maxPathSumAux(root.left), 0);
        int sumFromRightChild = Math.max(maxPathSumAux(root.right), 0);

        int sumThroughRoot = root.val + sumFromLeftChild + sumFromRightChild;
        max = Math.max(max, sumThroughRoot);

        int sumStartingFromRoot = root.val + Math.max(sumFromLeftChild, sumFromRightChild);
        return sumStartingFromRoot;
    }

    public int maxPathSum(TreeNode root) {
        maxPathSumAux(root);
        return max;
    }
}
