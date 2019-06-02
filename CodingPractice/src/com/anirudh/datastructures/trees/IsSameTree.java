package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 2/11/17.
 */
/*
100. Same Tree
Description  Submission  Solutions  Add to List
Total Accepted: 181640
Total Submissions: 399909
Difficulty: Easy
Contributors: Admin
Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 */
public class IsSameTree {


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        else if (p == null || q == null)
            return false;
        else { //both are non-null
            //preorder traversal
            return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}
