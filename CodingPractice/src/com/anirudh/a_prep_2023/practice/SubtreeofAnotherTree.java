package com.anirudh.a_prep_2023.practice;

import com.anirudh.datastructures.trees.TreeNode;

/**
 * Created by paanir on 12/9/17.
 */
/*
572. Subtree of Another Tree

Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a
subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s
could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
 */
public class SubtreeofAnotherTree {

    public boolean checkSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null)
            return true;
        else if (s == null || t == null)
            return false;
        else { //both are non-null
            if (s.val == t.val)
                return checkSameTree(s.left, t.left) && checkSameTree(s.right, t.right);
            else {
                return false;
            }
        }
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null)
            return true;
        else if (s == null || t == null)
            return false;
        else { //both are non-null
            if (s.val == t.val) {
                boolean isSame = checkSameTree(s.left, t.left) && checkSameTree(s.right, t.right);
                if (isSame)
                    return true;
            }
            //look at rest of the tree for the subtree
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        }
    }
}
