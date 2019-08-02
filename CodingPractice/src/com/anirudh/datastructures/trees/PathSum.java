package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 5/25/17.
 */
/*
112. Path Sum
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along
the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class PathSum {

    public boolean hasSum(TreeNode node, int sumLeft) {
        if (node.left == null && node.right == null)  //leaf
            return sumLeft - node.val == 0;
        else if (node.right == null) //left not null
            return hasSum(node.left, sumLeft - node.val);
        else if (node.left == null) //right not null
            return hasSum(node.right, sumLeft - node.val);
        else//both non-null
            return hasSum(node.left, sumLeft - node.val) || hasPathSum(node.right, sumLeft - node.val);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        return hasSum(root, sum);
    }
}
