package com.anirudh.interview_prep_2021_2022.facebook.lc_last_6m;

import com.anirudh.datastructures.trees.TreeNode;

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

//Same as diameter of the tree: 543
/*
1. At every node we check the sum starting from left child to leaves and right child to leaves: ls and rs
2. we update the maxSum by node + rs + ls
3. we return max(ls,rs) for the recursion
 */
class BTMaximumPathSum {
    int pathSum = Integer.MIN_VALUE;

    int getMaxSumStartingFrom(TreeNode node) {
        if(node == null)
            return 0;

        int leftPathSum =  Math.max(getMaxSumStartingFrom(node.left), 0);
        int rightPathSum = Math.max(getMaxSumStartingFrom(node.right), 0);

        pathSum = Math.max(pathSum, node.val + leftPathSum + rightPathSum);

        return node.val + Math.max(leftPathSum, rightPathSum);
    }

    public int maxPathSum(TreeNode root) {
        getMaxSumStartingFrom(root);
        return pathSum;
    }


}
