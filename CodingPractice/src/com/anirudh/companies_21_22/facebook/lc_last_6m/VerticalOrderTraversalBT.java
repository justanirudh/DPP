package com.anirudh.companies_21_22.facebook.lc_last_6m;

import com.anirudh.datastructures.trees.TreeNode;

import java.util.ArrayList;
import java.util.List;

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

    class Location {
        int x, y, val;

        Location(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    public void preOrderTraversal(TreeNode node, int x, int y) {
        if (node != null) {
            locations.add(new Location(x, y, node.val));
            preOrderTraversal(node.left, x - 1, y + 1);
            preOrderTraversal(node.right, x + 1, y + 1);
        }
    }

    List<Location> locations;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // Each location is a node'number x position, y position, and value
        locations = new ArrayList<>();

        preOrderTraversal(root, 0, 0); // populate Locations list

        //TODO: For Problem 314, instead of comparing values in last "else",
        // compare left node vs right node. One wa to do it is pass path to ndoes as you go
        // Eg. "LLR" vs "LRL", first one would take precedence
        locations.sort((o1, o2) -> {
            if (o1.x != o2.x) //first check x
                return Integer.compare(o1.x, o2.x);
            else if (o1.y != o2.y) //then check y
                return Integer.compare(o1.y, o2.y);
            else //then check value
                return Integer.compare(o1.val, o2.val);
        });//sort it

        /*
        After that, the impl is simple. just go through sorted array and lump objects with same x value together
         */

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<Integer>());

        int prev = locations.get(0).x;

        for (Location loc : locations) {
            // If the x value changed, it'number part of a new report.
            if (loc.x != prev) {
                prev = loc.x;
                ans.add(new ArrayList<Integer>());
            }

            // We always add the node'number value to the latest report.
            ans.get(ans.size() - 1).add(loc.val);
        }

        return ans;
    }
}
