package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 7/20/17.
 */
/*
114. Flatten Binary Tree to Linked List
Given a binary tree, flatten it to a linked list **in-place**.

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

    void move(TreeNode root) {
        if (root == null)
            return;
        move(root.left);
        move(root.right);
        if (root.left != null) {
            TreeNode curr = root.left;
            while (curr.right != null)
                curr = curr.right;
            curr.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }

    public void flatten(TreeNode root) {
        move(root);
    }
}
