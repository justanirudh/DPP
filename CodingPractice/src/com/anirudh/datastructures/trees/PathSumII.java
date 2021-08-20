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
public class PathSumII {

    //precondition, tn != null
    private List<List<Integer>> pathSumAux(TreeNode tn, List<Integer> path, int remainingSum) {
        if (tn.left == null && tn.right == null) { //leaf
            if (remainingSum - tn.val == 0) { //found a solution
                List<List<Integer>> resList = new ArrayList<>();
                List<Integer> res = new ArrayList<>(path);
                res.add(tn.val);
                resList.add(res);
                return resList;
            } else
                return new ArrayList<>();
        } else if (tn.left == null) {
            path.add(tn.val);
            return pathSumAux(tn.right, path, remainingSum - tn.val);
        } else if (tn.right == null) {
            path.add(tn.val);
            return pathSumAux(tn.left, path, remainingSum - tn.val);
        } else { //both children non-null
            List<List<Integer>> resList = new ArrayList<>();
            path.add(tn.val);
            resList.addAll(pathSumAux(tn.right, new ArrayList<>(path), remainingSum - tn.val));
            resList.addAll(pathSumAux(tn.left, new ArrayList<>(path), remainingSum - tn.val));
            return resList;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null)
            return new ArrayList<>();
        return pathSumAux(root, new ArrayList<>(), sum);
    }
}
