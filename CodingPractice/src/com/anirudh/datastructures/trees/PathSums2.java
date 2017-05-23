package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 5/24/17.
 */
/*
113. Path Sum II
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
 */
public class PathSums2 {
    List<List<Integer>> lists = new ArrayList<List<Integer>>();

    public void getSums(TreeNode node, List<Integer> list, int sumLeft) {
        if (node.left == null && node.right == null) { //leaf
            if (sumLeft - node.val == 0) {
                list.add(node.val);
                lists.add(list);
            }
        } else if (node.right == null) { //left not null
            list.add(node.val);
            getSums(node.left, list, sumLeft - node.val);
        } else if (node.left == null) { //right not null
            list.add(node.val);
            getSums(node.right, list, sumLeft - node.val);
        } else {//both non-null
            List<Integer> list2 = new ArrayList<>(list);
            list.add(node.val);
            list2.add(node.val);
            getSums(node.left, list, sumLeft - node.val);
            getSums(node.right, list2, sumLeft - node.val);
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null)
            return lists;
        getSums(root, new ArrayList<>(), sum);
        return lists;
    }
}
