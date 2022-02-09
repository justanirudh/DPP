package com.anirudh.companies_21_22.facebook.lc_last_6m.anki;

import com.anirudh.datastructures.trees.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
515. Find Largest Value in Each Tree Row
Medium

1842

80

Add to List

Share
Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).



Example 1:


Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]
Example 2:

Input: root = [1,2,3]
Output: [1,3]


Constraints:

The number of nodes in the tree will be in the range [0, 104].
-231 <= Node.val <= 231 - 1
Accepted
176,674
Submissions
275,348
 */
/*
do level order search, track max at each level
 */
public class FindLargestValueinEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int len = q.size();
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < len; ++i) {
                TreeNode node = q.poll();
                max = Math.max(max, node.val);
                if(node.left != null)
                    q.offer(node.left);
                if(node.right != null)
                    q.offer(node.right);
            }
            res.add(max);
        }
        return res;
    }
}
