package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 2/12/17.
 */
/*
108. Convert Sorted Array to Binary Search Tree
Description  Submission  Solutions  Add to List
Total Accepted: 106094
Total Submissions: 260691
Difficulty: Easy
Contributors: Admin
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedArraytoBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode createBST(int nums[], int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode left = createBST(nums, start, mid - 1);
        TreeNode right = createBST(nums, mid + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return null;
        int mid = len / 2;
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode left = createBST(nums, 0, mid - 1);
        TreeNode right = createBST(nums, mid + 1, len - 1);
        root.left = left;
        root.right = right;
        return root;
    }
}
