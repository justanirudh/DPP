package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by paanir on 4/7/17.
 */
/*
94. Binary Tree Inorder Traversal
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?
 */

//Same as BSTIterator solution (next() method)
public class BTInOrderIterative {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //----------------------
    public void traverse(List<Integer> list, TreeNode node) {
        if (node == null)
            return;
        traverse(list, node.left);
        list.add(node.val);
        traverse(list, node.right);
    }

    public List<Integer> inorderTraversalRec(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        traverse(list, root.left);
        list.add(root.val);
        traverse(list, root.right);
        return list;
    }

    //--------------------------
    public List<Integer> inorderTraversalIter(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        List<Integer> ls = new ArrayList<>();
        TreeNode node = root;
        while (node != null) {// push all left values
            st.push(node);
            node = node.left;
        }
        while (!st.isEmpty()) {
            TreeNode curr = st.pop();
            ls.add(curr.val);
            TreeNode curRight = curr.right; //if right is non-null, push it and add its left tree
            if (curRight != null) {
                st.push(curRight); //push right node
                TreeNode tn = curRight.left;
                while (tn != null) { //push its left tree
                    st.push(tn);
                    tn = tn.left;
                }
            }
        }
        return ls;
    }
    //---------------------------
}
