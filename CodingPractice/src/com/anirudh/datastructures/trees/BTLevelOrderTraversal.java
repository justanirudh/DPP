package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by paanir on 6/5/17.
 */
/*
102. Binary Tree Level Order Traversal

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 */

/*

BAD IMPLEMENTATION. NO NEED TO HAVE A LEVEL FIELD

LOOK AT 107. Binary Tree Level Order Traversal II

 */
public class BTLevelOrderTraversal {
    public class Node {
        TreeNode tn;
        int level;

        public Node(TreeNode tn, int level) {
            this.tn = tn;
            this.level = level;
        }
        //getters
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root, 0));
        int currLevel = -1;
        while (!queue.isEmpty()) {
            Node n = queue.remove();
            if (n.level == currLevel + 1) {
                List<Integer> newLevel = new ArrayList<>();
                newLevel.add(n.tn.val);
                res.add(newLevel);
                currLevel = n.level;
            } else {
                res.get(res.size() - 1).add(n.tn.val);
            }
            if (n.tn.left != null)
                queue.add(new Node(n.tn.left, currLevel + 1));
            if (n.tn.right != null)
                queue.add(new Node(n.tn.right, currLevel + 1));
        }
        return res;
    }
}
