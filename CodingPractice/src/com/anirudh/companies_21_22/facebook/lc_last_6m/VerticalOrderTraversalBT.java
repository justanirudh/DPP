package com.anirudh.companies_21_22.facebook.lc_last_6m;

import com.anirudh.companies_21_22.facebook.lc_last_6m.anki.BTVerticalOrderTraversal;
import com.anirudh.datastructures.trees.TreeNode;

import java.util.*;

/**
 * Created by paanir on 1/1/18.
 */
/*
987. Vertical Order Traversal of a Binary Tree
Medium

103

244

Favorite

Share
Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.



Example 1:



Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation:
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).
Example 2:



Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.


Note:

The tree will have between 1 and 1000 nodes.
Each node'number value will be between 0 and 1000.
 */

/*
1. give coordinates to each node as you do preorder traversal in a class called location
2. in location class, override compareTo() to first check x, then y then left node or right node
3. to check left, right, pass the path down during preorder traversal
 */

class VerticalOrderTraversalBT {
    static class RichNode {
        int val;
        int x;
        int y;

        RichNode(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
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
                return n1.val - n2.val;
        }
    }

    void preOrder(TreeNode node, int x, int y) {
        if (node == null)
            return;
        order.offer(new RichNode(node.val, x, y));
        preOrder(node.left, x - 1, y - 1);
        preOrder(node.right, x + 1, y - 1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        order = new PriorityQueue<>(new CompareRichNodes());
        // do preOrder
        preOrder(root, 0, 0); //nlogn as inserting into PQ
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
