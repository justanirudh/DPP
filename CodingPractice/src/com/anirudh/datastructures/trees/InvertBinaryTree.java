package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 9/28/17.
 */
/*
226. Invert Binary Tree
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1
Trivia:
This problem was inspired by this original tweet by Max Howell:
Google: 90% of our engineers use the software you wrote (Homebrew),
but you can’t invert a binary tree on a whiteboard so fuck off.
 */
public class InvertBinaryTree {
    TreeNode preOrder(TreeNode root) {
        if (root == null)
            return null;
        TreeNode tn = new TreeNode(root.val);
        tn.right = preOrder(root.left);
        tn.left = preOrder(root.right);
        return tn;
    }

    public TreeNode invertTree(TreeNode root) {
        //preorder in original tree; reverse preorder in new tree
        if (root == null)
            return null;
        return preOrder(root);
    }
}
