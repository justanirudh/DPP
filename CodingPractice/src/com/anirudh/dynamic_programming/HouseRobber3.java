package com.anirudh.dynamic_programming;

/**
 * Created by paanir on 8/21/17.
 */
/*
337. House Robber III

The thief has found himself a new place for his thievery again. There is only one entrance to this area,
called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart
thief realized that "all houses in this place forms a binary tree". It will automatically contact the police
if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.
 */

import com.anirudh.datastructures.trees.TreeNode;

import java.util.*;

public class HouseRobber3 {

    Map<TreeNode, Integer> map = new HashMap<>();

    public int rob(TreeNode root) {
        return robAux(root);
    }

    public int robAux(TreeNode root) {
        if (root == null)
            return 0;
        if (map.containsKey(root))
            return map.get(root);
        int val = 0;
        if (root.left != null)
            val += robAux(root.left.left) + robAux(root.left.right);
        if (root.right != null)
            val += robAux(root.right.left) + robAux(root.right.right);


        int total = Math.max(root.val + val, robAux(root.left) + robAux(root.right));
        map.put(root, total);
        return total;
    }

}
