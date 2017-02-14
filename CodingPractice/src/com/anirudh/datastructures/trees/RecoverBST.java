package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 2/13/17.
 */
/*
99. Recover Binary Search Tree
Description  Submission  Solutions  Add to List
Total Accepted: 67187
Total Submissions: 232346
Difficulty: Hard
Contributors: Admin
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */

//O(1) space, O(n) time
//track the previous nodee, keep two pointers for each elem exchanged
// Eg 1 4 3 2 5
public class RecoverBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    TreeNode prev, node1, node2;

    void inOrderTraversal(TreeNode root) {
        if (root == null)
            return;
        inOrderTraversal(root.left);
        int currElem = root.val;

        if (prev != null && currElem < prev.val) { //4 > 3 in 1 4 3 2 5
            if (node1 == null) { //if first node hasnt been discovered yet
                node1 = prev; // 4 is the swapped elem
                node2 = root; //3 can be the potential swapped elem until we find another one.
            } else
                node2 = root; //found the other swapped elem as 3 > 2
        }
        prev = root; //updating prev
        inOrderTraversal(root.right);
    }

    public void recoverTree(TreeNode root) {
        inOrderTraversal(root);
        if (node1 != null && node2 != null) {
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }
    }
}
