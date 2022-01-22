package com.anirudh.companies_21_22.facebook.lc_last_6m;

import com.anirudh.datastructures.trees.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
1382. Balance a Binary Search Tree
Medium

1510

52

Add to List

Share
Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.



Example 1:


Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
Example 2:


Input: root = [2,1,3]
Output: [2,1,3]


Constraints:

The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 105
Accepted
77,684
Submissions
97,537
 */

/*
1. change into sorted arr
2. create BST
Tx: O(n)
*/
public class BalanceaBST {

    void createSortedList(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        createSortedList(root.left, list);
        list.add(root.val);
        createSortedList(root.right, list);
    }

    TreeNode createBalancedBT(List<Integer> sorted, int start, int end) {
        if (start > end)
            return null;
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(sorted.get(mid));
        root.left = createBalancedBT(sorted, start, mid - 1);
        root.right = createBalancedBT(sorted, mid + 1, end);
        return root;
    }

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sorted = new ArrayList<>();
        createSortedList(root, sorted);
        return createBalancedBT(sorted, 0, sorted.size() - 1);
    }
}
