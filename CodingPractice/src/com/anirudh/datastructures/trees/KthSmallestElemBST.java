package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 5/22/17.
 */
/*
230. Kth Smallest Element in a BST
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ? k ? BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
How would you optimize the kthSmallest routine?
 */
public class KthSmallestElemBST {

    //O(n) time
    int rank;

    public int inOrder(TreeNode root) {
        if(root == null)
            return -1;
        int left = inOrder(root.left);
        if(left != -1)
            return left;
        --rank;
        if(rank == 0)
            return root.val;
        return inOrder(root.right);
    }

    public int kthSmallestSlow(TreeNode root, int k) {
        rank = k;
        return inOrder(root);
    }
    //-----------------------------------------------
    //Still O(n), might take more time
    public int countNodes(TreeNode node) {
        if(node == null)
            return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public int kthSmallest(TreeNode root, int k) {
        if(root == null)
            return 0;
        int numNodes = countNodes(root.left);
        if(numNodes == k - 1)
            return root.val;
        else if(numNodes > k-1)
            return kthSmallest(root.left, k);
        else
            return kthSmallest(root.right, k-numNodes - 1);
    }

    //Use augmented DS: https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/ 2nd solution
    //memoize count in the tree node

}
