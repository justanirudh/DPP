package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 4/25/17.
 */
/*
404. Sum of Left Leaves
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */

public class SumOfLeftLeaves {

    public int sum(TreeNode node, boolean isLeft) {
        if (node == null)
            return 0;
        if (node.left != null || node.right != null) { //not a leaf
            return sum(node.left, true) + sum(node.right, false);
        } else { // is a leaf
            if (isLeft)
                return node.val;
            else
                return 0;
        }
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        return sum(root.left, true) + sum(root.right, false);

    }
}
