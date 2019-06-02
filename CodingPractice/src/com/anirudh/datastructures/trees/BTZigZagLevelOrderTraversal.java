package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by paanir on 5/30/17.
 */
/*
103. Binary Tree Zigzag Level Order Traversal

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right
to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]

 */

/*

BAD IMPLEMENTATION. NO NEED TO HAVE A LEVEL FIELD

LOOK AT 107. Binary Tree Level Order Traversal II

 */
public class BTZigZagLevelOrderTraversal {

    class Node {
        TreeNode tn;
        int level;

        public Node(TreeNode tn, int level) {
            this.tn = tn;
            this.level = level;
        }

        public TreeNode getTreeNode() {
            return tn;
        }

        public int getLevel() {
            return level;
        }
    }

    //O(n) time, O(n) space
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> zigzag = new ArrayList<>();
        if (root == null)
            return zigzag;

        int currLevel = -1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root, 0));

        while (!queue.isEmpty()) {
            Node head = queue.remove();

            if (head.level == currLevel + 1) { //if new level, add a new empty list at end
                zigzag.add(new ArrayList<>());
                currLevel = head.getLevel();
            }

            Integer curr = head.getTreeNode().val;
            if (currLevel % 2 == 0) //if even number of level, addition is left to right
                zigzag.get(zigzag.size() - 1).add(curr);
            else
                zigzag.get(zigzag.size() - 1).add(0, curr); //else right to left. hence, prepending

            if (head.getTreeNode().left != null)
                queue.add(new Node(head.getTreeNode().left, currLevel + 1)); //passing level info this way! :)
            if (head.getTreeNode().right != null)
                queue.add(new Node(head.getTreeNode().right, currLevel + 1));
        }
        return zigzag;
    }
}
