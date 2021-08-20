package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 7/22/19.
 */
/*
124. Binary Tree Maximum Path Sum
Hard

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The path must contain at least one node and does not need to go through the root.

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
And find max of the two
 */
public class BTMaximumPathSum {

    int max = Integer.MIN_VALUE;

    private void updateMaxSum(TreeNode node, int sumLeft, int sumRight) {
        int sumThroughNode = node.val + sumLeft + sumRight; //compute gain for starting a new path going through the node
        max = Math.max(max, sumThroughNode); // Math.max(old_path, start a new path through the node and ditch the old path)
    }

    private int getMaxSumStartingFrom(TreeNode node) {
        if (node == null)
            return 0;
        // 0 if we are not taking the branch in case it is negative
        int sumFromLeftChild = Math.max(getMaxSumStartingFrom(node.left), 0); //9
        int sumFromRightChild = Math.max(getMaxSumStartingFrom(node.right), 0); //35

        updateMaxSum(node, sumFromLeftChild, sumFromRightChild);

        int sumStartingFromNode = node.val + Math.max(sumFromLeftChild, sumFromRightChild); //maximum gain from the node

        return sumStartingFromNode;
    }

    public int maxPathSum(TreeNode root) {
        getMaxSumStartingFrom(root);
        return max;
    }
}
