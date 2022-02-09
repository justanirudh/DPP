package com.anirudh.companies_21_22.facebook.lc_last_6m.tbd;

//import com.anirudh.datastructures.trees.Node;

import com.anirudh.datastructures.trees.TreeNode;

import javax.xml.soap.Node;

/**
 * Created by paanir on 1/3/18.
 */
/*
333. Largest BST Subtree
Medium

Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST), where the largest means subtree has the largest number of nodes.

A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:

The left subtree values are less than the value of their parent (root) node's value.
The right subtree values are greater than the value of their parent (root) node's value.
Note: A subtree must include all of its descendants.

Follow up: Can you figure out ways to solve it with O(n) time complexity?

 */
public class LargestBSTSubtreeInaBT {

    class SubTreeInfo {
        int size;
        boolean isBST;

        SubTreeInfo(int size, boolean isBST) {
            this.size = size;
            this.isBST = isBST;
        }
    }

    int res;

    SubTreeInfo maxSize(TreeNode curr, int min, int max) { //post order
        if (curr == null)
            return new SubTreeInfo(0, true);

        SubTreeInfo left = maxSize(curr.left, min, curr.val);
        SubTreeInfo right = maxSize(curr.right, curr.val, max);

        if(left.size == 0 && right.size == 0) { //leaf
            res = Math.max(res, 1);
        }

        if (left.isBST && right.isBST && curr.val > min && curr.val < max) {
            int size = left.size + right.size + 1;
            res = Math.max(res, size);
            return new SubTreeInfo(size, true);
        } else if (left.isBST) {
            res = Math.max(res, left.size);
            return new SubTreeInfo(-1, false);
        } else if (right.isBST) {
            res = Math.max(res, right.size);
            return new SubTreeInfo(-1, false);
        } else {
            return new SubTreeInfo(-1, false);
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        res = 0;
        if(root == null)
            return res;
        maxSize(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return res;
    }

}
