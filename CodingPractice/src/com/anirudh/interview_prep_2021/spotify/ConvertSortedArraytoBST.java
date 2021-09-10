package com.anirudh.interview_prep_2021.spotify;

import com.anirudh.datastructures.trees.TreeNode;

/**
 * Created by paanir on 2/12/17.
 */
/*
108. Convert Sorted Array to Binary Search Tree

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */

/*
    preorder traversal

*/
public class ConvertSortedArraytoBST {

    int[] nums;

    public TreeNode createBST(int start, int end) {
        if (start > end)
            return null;
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = createBST(start, mid - 1);
        node.right = createBST(mid + 1, end);
        return node;
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        int len = nums.length;
        if (len == 0)
            return null;
        return createBST(0, len - 1);
    }
}
