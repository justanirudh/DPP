package com.anirudh.companies_21_22.google.lc_last_6m;

import com.anirudh.datastructures.trees.TreeNode;

import java.util.*;

/*
652. Find Duplicate Subtrees
Medium

2775

276

Add to List

Share
Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.



Example 1:


Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]
Example 2:


Input: root = [2,1,1]
Output: [[1]]
Example 3:


Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]


Constraints:

The number of the nodes in the tree will be in the range [1, 10^4]
-200 <= Node.val <= 200
Accepted
127,923
Submissions
232,256
 */

/*
Serialize each tree. Put in hashmap
Do postorder/preorder serialization generation, not inorder as 2 diff trees can have same inorder serialization
 */
public class FindDuplicateSubtrees {
    Map<String, Integer> freq;
    List<TreeNode> res;

    String postOrder(TreeNode node) {
        if(node == null)
            return "#";
        String left = postOrder(node.left);
        String right = postOrder(node.right);
        String hash = left + "," + right + "," + node.val;
        freq.put(hash, freq.getOrDefault(hash, 0) + 1);
        if(freq.get(hash) == 2)
            res.add(node);
        return hash;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        freq = new HashMap<>();
        res = new ArrayList<>();
        postOrder(root);
        return res;
    }
}
