package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 2/17/17.
 */
/*
98. Validate Binary Search Tree Add to List
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.

 */

//Preorder traversal
public class BTisBST {

    class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        void addLeft(TreeNode tn) {
            this.left = tn;
        }

        void addRight(TreeNode tn) {
            this.right = tn;
        }
    }

    public boolean preOrder(TreeNode curr, double min, double max) {

        if (curr == null)
            return true;

        if (curr.val <= min || curr.val >= max) //if the current node violates the BST property
            return false;

        return preOrder(curr.left, min, curr.val) && preOrder(curr.right, curr.val, max);

    }

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;

        double min = Double.NEGATIVE_INFINITY;
        double max = Double.POSITIVE_INFINITY;

        //Also passing a range to each side of the tree left: [math.min, root] and right: [root, math.pathSum]
        return preOrder(root, min, max);
    }
}
