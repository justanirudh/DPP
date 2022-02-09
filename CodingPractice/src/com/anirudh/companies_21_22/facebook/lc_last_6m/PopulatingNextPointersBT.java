package com.anirudh.companies_21_22.facebook.lc_last_6m;

/**
 * Created by paanir on 2/1/17.
 */
/*

116. Populating Next Right Pointers in Each Node
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL

 */
/*
At every node, make connections for next level
 */
public class PopulatingNextPointersBT {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
       if (root == null)
           return null;

       Node leftMost = root;

       while(leftMost.left != null) { //last level of all leaves
           Node curr = leftMost;
           while(curr != null) {
               curr.left.next = curr.right; //left child to right child
               if(curr.next != null)
                   curr.right.next = curr.next.left; //across different parents
               curr = curr.next;
           }
            leftMost = leftMost.left;
       }
       return root;
    }

}
