package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 7/22/19.
 */
/*
124. Binary Tree Maximum Path Sum
Hard

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
the parent-child connections.The path must contain at least one node and does not need to go through the root.

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
1. sum starting from the node to one of the leaves
2. sum that goes through the node to both leaves
And find pathSum of the two
 */
//Same as diameter of the tree: #543
public class BTMaximumPathSum {

    int pathSum = Integer.MIN_VALUE;

    private int getMaxSumStartingFrom(TreeNode node) {
        if (node == null)
            return 0;

        int sumFromLeftChild = Math.max(getMaxSumStartingFrom(node.left), 0); // 0 if we are not taking the branch in case it is negative
        int sumFromRightChild = Math.max(getMaxSumStartingFrom(node.right), 0);

        pathSum = Math.max(pathSum, node.val + sumFromLeftChild + sumFromRightChild); //pathSum until now vs current sum through node

        return node.val + Math.max(sumFromLeftChild, sumFromRightChild); //return sum starting from node, for recursion
    }

    public int maxPathSum(TreeNode root) {
        getMaxSumStartingFrom(root);
        return pathSum;
    }
}
