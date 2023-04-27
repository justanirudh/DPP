package com.anirudh.a_prep_2023.practice;

import com.anirudh.datastructures.trees.TreeNode;

/**
 * Created by paanir on 11/9/17.
 */
/*
111. Minimum Depth of Binary Tree
Easy

713

383

Favorite

Share
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.
 */
public class MinimumDepthBT {
    private int minDepthAux(TreeNode node, int depth) {
        if (node == null)
            return depth;
        if (node.left == null && node.right == null) { //both null
            return depth + 1;
        } else if (node.left == null) {
            return minDepthAux(node.right, depth + 1);
        } else if (node.right == null) {
            return minDepthAux(node.left, depth + 1);
        } else {
            return Math.min(minDepthAux(node.left, depth + 1), minDepthAux(node.right, depth + 1));
        }
    }

    public int minDepth(TreeNode root) {
        return minDepthAux(root, 0);
    }
}
