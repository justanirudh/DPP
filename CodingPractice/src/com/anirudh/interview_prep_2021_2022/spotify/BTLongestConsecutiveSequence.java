package com.anirudh.interview_prep_2021_2022.spotify;

import com.anirudh.datastructures.trees.TreeNode;

/*
298. Binary Tree Longest Consecutive Sequence
Medium

747

178

Add to List

Share
Given the root of a binary tree, return the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path needs to be from parent to child (cannot be the reverse).

Example 1:


Input: root = [1,null,3,2,4,null,null,null,5]
Output: 3
Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
Example 2:


Input: root = [2,null,3,2,null,1]
Output: 2
Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 */
public class BTLongestConsecutiveSequence {

    int maxLen = Integer.MIN_VALUE;

    void preOrder(TreeNode curr, TreeNode parent, int currLen) {
        if (curr == null)
            return;
        if (parent == null || curr.val != parent.val + 1) {
            currLen = 1;
        } else {
            currLen = currLen + 1;
        }

        maxLen = Math.max(maxLen, currLen);
        preOrder(curr.left, curr, currLen);
        preOrder(curr.right, curr, currLen);
    }

    public int longestConsecutive(TreeNode root) {
        if (root == null)
            return 0;
        preOrder(root, null, 1);
        return maxLen;
    }
}
