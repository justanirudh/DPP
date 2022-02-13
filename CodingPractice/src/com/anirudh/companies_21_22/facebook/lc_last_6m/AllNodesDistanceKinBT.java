package com.anirudh.companies_21_22.facebook.lc_last_6m;

import com.anirudh.datastructures.trees.TreeNode;

import java.util.*;

/*
863. All Nodes Distance K in Binary Tree
Medium

5615

117

Add to List

Share
Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.



Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
Example 2:

Input: root = [1], target = 1, k = 3
Output: []


Constraints:

The number of nodes in the tree is in the range [1, 500].
0 <= Node.val <= 500
All the values Node.val are unique.
target is the value of one of the nodes in the tree.
0 <= k <= 1000
 */

/*
Do preOrder to get node->parent pointers. Do BFS to get distance

1. Create a Map{node -> parent}
    Do preOrder to create this map
2. Now start doing BFS from target node
    create queue
    create visited set
        add null to set so that root's parent is never in the queue
    Start doing BFS
        For each node
            check left
            check right
            check parent using map
    Break when dist of nodes in queue is k
    put queue in list and return
 */
public class AllNodesDistanceKinBT {


    void preOrder(TreeNode node, TreeNode par, Map<TreeNode, TreeNode> map) {
        if (node != null) {
            map.put(node, par);
            preOrder(node.left, node, map);
            preOrder(node.right, node, map);
        }
    }


    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();

        if (k == 0) {
            res.add(target.val);
            return res;
        }

        Map<TreeNode, TreeNode> nodeToParent = new HashMap<>();
        preOrder(root, null, nodeToParent);

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(target);

        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        visited.add(null); //so that we dont add root's parent (null) in the queue

        int dist = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                TreeNode node = q.poll();

                if (node.left != null && !visited.contains(node.left)) {
                    visited.add(node.left);
                    q.offer(node.left);
                }
                if (node.right != null && !visited.contains(node.right)) {
                    visited.add(node.right);
                    q.offer(node.right);
                }
                if (nodeToParent.containsKey(node) && !visited.contains(nodeToParent.get(node))) {
                    visited.add(nodeToParent.get(node));
                    q.offer(nodeToParent.get(node));
                }

            }
            dist++; //distance of nodes in the queue right now
            if (dist == k) {
                break;
            }
        }
        while (!q.isEmpty()) {
            res.add(q.poll().val);
        }
        return res;
    }
}
