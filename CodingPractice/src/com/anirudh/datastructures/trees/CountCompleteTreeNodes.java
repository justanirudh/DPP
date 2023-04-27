package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 2/9/17.
 */
/*
222. Count Complete Tree Nodes

Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level
are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

 */
public class CountCompleteTreeNodes {

    /*

    Time complexity : O(h^2) = O((log N)^2)
    Space complexity : O(1)

     */
    class Solution {
        // Return tree depth in O(d) time.
        public int computeDepth(TreeNode node) {
            int d = 0;
            while (node.left != null) {
                node = node.left;
                ++d;
            }
            return d;
        }

        // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
        // Return True if last level node idx exists.
        // Binary search with O(d) complexity.
        public boolean exists(int idx, int d, TreeNode node) {
            int left = 0, right = (int)Math.pow(2, d) - 1;
            int pivot;
            for(int i = 0; i < d; ++i) { //until reached the last level fo tree
                pivot = left + (right - left) / 2;
                if (idx <= pivot) {
                    node = node.left;
                    right = pivot;
                }
                else {
                    node = node.right;
                    left = pivot + 1;
                }
            }
            return node != null; //returns if the leaf node exists or not
        }

        public int countNodes(TreeNode root) {
            // if the tree is empty
            if (root == null)
                return 0;

            int d = computeDepth(root);

            // if the tree contains 1 node
            if (d == 0)
                return 1;

            int sizeMinusLastLevel = (int)Math.pow(2, d) - 1;

            // Last level nodes are enumerated from 1 to 2**d - 1 (left -> right).
            // Perform binary search <></>o check how many nodes exist.
            /*
            Enumerate 1 to 2^d-1. For each number check if a leaf node exists
            if it does, look at right subarray, else check in left subarray
             */
            int left = 1, right = (int)Math.pow(2, d) - 1; //enumerate all leaves, left is 1 as last level cannot be non-empty
            int pivot;
            while (left <= right) {
                pivot = left + (right - left) / 2;
                if (exists(pivot, d, root))
                    left = pivot + 1;
                else
                    right = pivot - 1;
            }

            // The tree contains 2**d - 1 nodes on the first (d - 1) levels
            // and left nodes on the last level.
            return sizeMinusLastLevel + left;
        }
    }

//----------------------------------------------------------------------
    /*
        Best case T= O(logn)
        Worst case T = O(n)
     */

    private int getLeftHeight(TreeNode tn) {
        int count = 0;
        if(tn == null)
            return count;
        while(tn != null){
            tn = tn.left;
            count++;
        }
        return count;
    }

    private int getRightHeight(TreeNode tn) {
        int count = 0;
        if(tn == null)
            return count;
        while(tn != null){
            tn = tn.right;
            count++;
        }
        return count;
    }

    public int countNodes(TreeNode root) {

        if(root == null)
            return 0;

        int leftH = getLeftHeight(root.left); //#nodes for height
        int rightH = getRightHeight(root.right);

        if(leftH == rightH) //perfect/full binary tree
            return ((2 <<leftH) - 1); //Or Math.pow(2, leftH + 1) - 1. Math.pow(2, a) is same as 2 << (a-1) == 1 << a, yup.
        else
            return countNodes(root.left) + countNodes(root.right) + 1;
    }

    //-----------------------------------------------
    //O(n)
    int traverseInorderSlow(TreeNode tn, int count) {
        if (tn == null)
            return count;
        count = traverseInorderSlow(tn.left, count);
        count++;
        return traverseInorderSlow(tn.right, count);
    }

    public int countNodesSlow(TreeNode root) {
        return traverseInorderSlow(root, 0);
    }


}
