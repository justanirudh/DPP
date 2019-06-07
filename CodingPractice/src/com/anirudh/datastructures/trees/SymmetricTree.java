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

    private boolean areSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        else if (left == null || right == null)
            return false;
        else { //both non-null
            return left.val == right.val &&
                    areSymmetric(left.right, right.left) &&
                    areSymmetric(left.left, right.right);
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return areSymmetric(root.left, root.right);
    }
}
