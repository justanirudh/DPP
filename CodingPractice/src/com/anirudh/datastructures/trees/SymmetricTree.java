package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 1/13/17.
 */
/*
101. Symmetric Tree

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
 */
public class SymmetricTree {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static boolean isSymAux(TreeNode leftRoot, TreeNode rightRoot) {
        boolean state;

        if (leftRoot.right == null && rightRoot.left == null)
            state = true;
        else if (leftRoot.right == null || rightRoot.left == null)
            state = false;
        else { //both non-null
            if (leftRoot.right.val == rightRoot.left.val)
                state = isSymAux(leftRoot.right, rightRoot.left);
            else
                state = false;
        }

        if (!state)
            return state;

        if (leftRoot.left == null && rightRoot.right == null)
            state = true;
        else if (leftRoot.left == null || rightRoot.right == null)
            state = false;
        else { //both non-null
            if (leftRoot.left.val == rightRoot.right.val)
                state = isSymAux(leftRoot.left, rightRoot.right);
            else
                state = false;
        }

        return state;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        if (root.left == null && root.right == null)
            return true;
        else if (root.left == null || root.right == null)
            return false;
        else {
            if (root.left.val == root.right.val) //both are non null and equal
                return isSymAux(root.left, root.right);
            else
                return false;
        }
    }
}
