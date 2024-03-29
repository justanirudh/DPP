package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/*
366. Find Leaves of Binary Tree
Medium

1811

33

Add to List

Share
Given the root of a binary tree, collect a tree's nodes as if you were doing this:

Collect all the leaf nodes.
Remove all the leaf nodes.
Repeat until the tree is empty.
 */
/*
depth = distance from root => different for all leaves
height = distance from leaf => same for all leaves

Do post-order traversal and find height of each node
Collect as you do the traversal
 */
public class FindLeavesBinaryTree {
    List<List<Integer>> res;

    int postOrder(TreeNode node) {
        if (node == null)
            return 0;
        int leftHeight = postOrder(node.left);
        int rightHeight = postOrder(node.right);
        int nodeHeight = Math.max(leftHeight, rightHeight) + 1;
        if (nodeHeight > res.size()) { //new height
            res.add(new ArrayList<>());
        }
        res.get(nodeHeight - 1).add(node.val);

        return nodeHeight;

    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        res = new ArrayList<>();
        postOrder(root);
        return res;
    }
}
