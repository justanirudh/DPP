package com.anirudh.companies_21_22.spotify;

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

    TreeNode createTree(int start, int end) {
        if (start > end)
            return null;
        int mid = start + (end - start) / 2;
        TreeNode tn = new TreeNode(nums[mid]);
        tn.left = createTree(start, mid - 1);
        tn.right = createTree(mid + 1, end);
        return tn;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return createTree(0, nums.length - 1);
    }
}
