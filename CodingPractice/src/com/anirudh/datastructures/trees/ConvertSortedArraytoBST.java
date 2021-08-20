package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 2/12/17.
 */
/*
108. Convert Sorted Array to Binary Search Tree

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedArraytoBST {

    public TreeNode createBST(int[] nums, int start, int end) {
        if (start > end)
            return null;
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = createBST(nums, start, mid - 1);
        node.right = createBST(nums, mid + 1, end);
        return node;
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return null;
        return createBST(nums, 0, len - 1);
    }
}
