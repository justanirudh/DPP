package com.anirudh.companies_21_22.google.lc_last_6m;

import com.anirudh.datastructures.trees.TreeNode;

/*
2096. Step-By-Step Directions From a Binary Tree Node to Another
Medium

415

31

Add to List

Share
You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.



Example 1:


Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
Example 2:


Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.


Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue
 */
/*
1. Find path of each start and end node from root: LRRLR, LRLLR
2. shave off first common parts of the 2 paths: LR removed: RLR, LLR; This node is the LCA
3. Convert start's path to U's (go from start to LCA) and append ends path: UUU + LLR (from LCA to end)

If start->end is parent-child; start to LCA will be empty string, no Us, only LR which is correct
If start->end is child-parent; LCA to end will be empty string, only Us

Use StringBuilder to not get MLE as we will create many strings for a big tree
 */
public class StepByStepDirectionsBT {

    boolean preOrder(TreeNode node, int val, StringBuilder pathTillNow) { //find, add when popping
        if (node == null)
            return false;
        if (node.val == val)
            return true;
        if (preOrder(node.left, val, pathTillNow)) {
            pathTillNow.insert(0, 'L'); //gets added when go from node to root; ee want path from root to node
        } else if (preOrder(node.right, val, pathTillNow)) {
            pathTillNow.insert(0, 'R');
        }
        return pathTillNow.length() > 0;
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder rootToStart = new StringBuilder();
        StringBuilder rootToEnd = new StringBuilder();
        preOrder(root, startValue, rootToStart); //LRRLR
        preOrder(root, destValue, rootToEnd); // LRLLR

        int i = 0;
        for (; i < Math.min(rootToStart.length(), rootToEnd.length()); ++i) {
            if (rootToStart.charAt(i) != rootToEnd.charAt(i)) {
                break;
            }
        }

        int numUs = rootToStart.length() - i; //change this to U's
        String rootToEndDiff = rootToEnd.substring(i);

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < numUs; ++j) {
            sb.append("U");
        }
        return sb + rootToEndDiff;
    }
}
