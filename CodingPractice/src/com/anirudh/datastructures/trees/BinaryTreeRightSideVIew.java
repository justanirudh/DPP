package com.anirudh.datastructures.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by paanir on 1/30/17.
 */
/*
199. Binary Tree Right Side View
Medium

1038

45

Favorite

Share
Given a binary tree, imagine yourself standing on the right side of it,
return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
 */

//level order traversal with first node on right
//same as boundary traversal
public class BinaryTreeRightSideVIew {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class NodeWithHeight {
        int height;
        TreeNode node;

        public NodeWithHeight(TreeNode node, int height) {
            this.node = node;
            this.height = height;
        }
    }


    public List<Integer> rightSideViewBad(TreeNode root) {

        ArrayList<NodeWithHeight> queue = new ArrayList<>();
        ArrayList<Integer> rightSides = new ArrayList<>();
        //Level order traversal

        if (root == null)
            return rightSides;
        NodeWithHeight nwh = new NodeWithHeight(root, 0);

        queue.add(nwh);

        while (!queue.isEmpty()) {
            NodeWithHeight curr = queue.get(0);
            //if last elem, it is right side
            if (queue.size() == 1) {
                rightSides.add(curr.node.val);
            } else { //see if next elem's height one more than curr's height. If so, it is rightmost elem
                if (queue.get(1).height == curr.height + 1)
                    rightSides.add(curr.node.val);
            }
            queue.remove(0);
            if (curr.node.left != null)
                queue.add(new NodeWithHeight(curr.node.left, curr.height + 1));
            if (curr.node.right != null)
                queue.add(new NodeWithHeight(curr.node.right, curr.height + 1));
        }
        return rightSides;
    }

    //------------------------------------------------------------------------------

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        if (root == null) return r;
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);
        TreeNode cur;
        int size = 0;

        while (!dq.isEmpty()) {
            size = dq.size();
            cur = null;
            for (int i = 0; i < size; ++i) {
                cur = dq.poll();
                if (cur.left != null) dq.offer(cur.left);
                if (cur.right != null) dq.offer(cur.right);
            }
            r.add(cur.val);
        }
        return r;
    }
}
