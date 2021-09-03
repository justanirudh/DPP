package com.anirudh.interview_prep_2021.facebook.lc_last_6m;

import com.anirudh.datastructures.trees.TreeNode;

/**
 * Created by paanir on 8/30/21.
 */
/*
543. Diameter of Binary Tree
Easy

5594

337

Add to List

Share
Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

Example 1:


Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
Example 2:

Input: root = [1,2]
Output: 1
 */

//Similar to Binary Tree Maximum path sum #124
/*
1. At every node we check the sum starting from left child to leaves and right child to leaves: ls and rs
2. we update the diameter by rs + ls
3. we return 1 + max(ls,rs) for the recursion
 */
public class DiameterofBinaryTree {
    int diameter = 0;

    public int getMaxLenStartingFrom(TreeNode root) {
        if(root == null)
            return 0;

        int leftPathLen = getMaxLenStartingFrom(root.left);
        int rightPathLen = getMaxLenStartingFrom(root.right);

        //not doing left + right +1 because path is number of edges, not number of nodes
        diameter = Math.max(diameter, leftPathLen + rightPathLen);

        return 1 + Math.max(leftPathLen, rightPathLen);

    }

    public int diameterOfBinaryTree(TreeNode root) {
        getMaxLenStartingFrom(root);
        return diameter;
    }
}
