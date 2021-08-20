package com.anirudh.datastructures.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

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
public class PopulatingNextPointersBT {
/*
time: O(n)
space: O(1) + stack(O(h))
 */

    //pre-order traversal
    private void connectNodes(Node node, Node right) {
        if (node == null)
            return;

        //root
        node.next = right;

        //left subtree
        connectNodes(node.left, node.right);//connect left child to right child

        //right subtree
        Node rightChildNext;
        if (right == null) //root's right is null, so no subtree there
            rightChildNext = null;
        else //root's right is another subtree
            rightChildNext = right.left; //For 5 in the example, it would be 6

        connectNodes(node.right, rightChildNext);//connect right child to the next subtree of left
    }

    public Node connect(Node root) {
        if (root == null)
            return null;
        connectNodes(root, null);
        return root;
    }

    //-----------------------------------------------------------------
    /*
    Time: O (n)
    Space: queue O (2^h)
     */
    public Node connectIntuitive(Node root) {
        if (root == null)
            return null;
        Deque<Node> nodes = new ArrayDeque<>();
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            int levelLen = nodes.size();
            for (int i = 0; i < levelLen; ++i) {
                //in a particular level
                Node head = nodes.poll();
                if (head.left != null)
                    nodes.offer(head.left);
                if (head.right != null)
                    nodes.offer(head.right);
                if (i != levelLen - 1)
                    head.next = nodes.peek();
                else
                    head.next = null;
            }
        }
        return root;
    }
}
