package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 7/20/17.
 */
/*
114. Flatten Binary Tree to Linked List
Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node
in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
 */
public class FlattenBinaryTreeToLinkedList {

   private void move(TreeNode root) {
        if (root == null)
            return;
        move(root.left);
        move(root.right);
        if (root.left != null) {
            TreeNode curr = root.left;
            while (curr.right != null) //go to rightmost of the node
                curr = curr.right;
            curr.right = root.right;//move root.right below this node
            root.right = root.left;//move the entire left branch to right
            root.left = null; //put left to null
        }
    }

    public void flatten(TreeNode root) {
        move(root);
    }
}
