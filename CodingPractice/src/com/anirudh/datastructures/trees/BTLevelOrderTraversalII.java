package com.anirudh.datastructures.trees;

import java.util.*;

/**
 * Created by paanir on 6/1/19.
 */
/*
107. Binary Tree Level Order Traversal II
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
 */
public class BTLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        /*
        1. use a stack to push each level
        2. For each level, do level-order traversal
        3. pop each level and append to a list before returning
         */
        Deque<List<Integer>> levelsS = new ArrayDeque<>(); //levels Stack for the result

        if(root == null)
            return new ArrayList<>(levelsS);

        Queue<TreeNode> nodesQ = new ArrayDeque<>(); //queue for the treenodes

        nodesQ.offer(root);
        while(!nodesQ.isEmpty()) {
            int sizeQ = nodesQ.size(); //use to track levels
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < sizeQ; ++i){
                TreeNode tn = nodesQ.poll();
                level.add(tn.val); //add it to current level
                if(tn.left != null)
                    nodesQ.offer(tn.left); // add left element
                if(tn.right != null)
                    nodesQ.offer(tn.right); //add right element
            }
            levelsS.push(level);
        }
        return new ArrayList<>(levelsS);
    }
}
