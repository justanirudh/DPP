package com.anirudh.companies_21_22.facebook.lc_last_6m.anki;

import com.anirudh.datastructures.trees.TreeNode;

import java.util.*;

/*
314. Binary Tree Vertical Order Traversal
Medium

1998

238

Add to List

Share
Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Example 2:


Input: root = [3,9,8,4,0,1,7]
Output: [[4],[9],[3,0,1],[8],[7]]
Example 3:


Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]
 */

/*
RichNode: node.val, x, y, posFromRoot (LLR, LRL, etc)
Do preOrder traversal while putting rich nodes inside a PQ such that:
    x is sorted from left to right
    y is sorted from top to bottom
    posFromRoot is sorted lexicographically
 */
public class BTVerticalOrderTraversal {
    static class RichNode {
        int val;
        int x;
        int y;
        String posFromRoot; //LLR, RLR; L is lexicographically smaller than R, so works for us

        RichNode(int val, int x, int y, String posFromRoot) {
            this.val = val;
            this.x = x;
            this.y = y;
            this.posFromRoot = posFromRoot;
        }
    }

    Queue<RichNode> order;

    static class CompareRichNodes implements Comparator<RichNode> {
        public int compare(RichNode n1, RichNode n2) {
            if (n1.x != n2.x)
                return n1.x - n2.x; //x from left to right
            else if (n1.y != n2.y)
                return n2.y - n1.y; // y should be top to down
            else
                return n1.posFromRoot.compareTo(n2.posFromRoot);
        }
    }

    void preOrder(TreeNode node, int x, int y, String pos) {
        if (node == null)
            return;
        order.offer(new RichNode(node.val, x, y, pos));
        preOrder(node.left, x - 1, y - 1, pos + "L");
        preOrder(node.right, x + 1, y - 1, pos + "R");
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        order = new PriorityQueue<>(new CompareRichNodes());
        // do preOrder
        preOrder(root, 0, 0, ""); //nlogn as inserting into PQ
        //create result
        List<List<Integer>> res = new ArrayList<>();
        int prevX = Integer.MIN_VALUE;
        while (!order.isEmpty()) {
            RichNode n = order.poll();
            if (n.x > prevX) //start of new list
                res.add(new ArrayList<>());
            res.get(res.size() - 1).add(n.val);
            prevX = n.x;
        }
        return res;
    }
}
