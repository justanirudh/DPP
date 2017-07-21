package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 7/20/17.
 */
/*
114. Flatten Binary Tree to Linked List
Given a binary tree, flatten it to a linked list in-place.

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



    //---------------------------------------------------
    List<TreeNode> ordered;

    void preOrderTraversal(TreeNode root) {
        if (root != null) {
            ordered.add(root);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    public void flattenNotInPlace(TreeNode root) {
        if (root == null)
            return;
        //NOT in place
        ordered = new ArrayList<>();
        preOrderTraversal(root);
        TreeNode curr = root;
        //create list
        for (int i = 1; i < ordered.size(); ++i) {
            curr.right = ordered.get(i);
            curr.left = null;
            curr = curr.right;
        }

    }
}
