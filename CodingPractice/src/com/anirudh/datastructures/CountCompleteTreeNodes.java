package com.anirudh.datastructures;

/**
 * Created by paanir on 2/9/17.
 */
/*
222. Count Complete Tree Nodes
Description  Submission  Solutions  Add to List
Total Accepted: 53737
Total Submissions: 198903
Difficulty: Medium
Contributors: Admin
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Subscribe to see which companies asked this question.

 */
public class CountCompleteTreeNodes {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //O(logn)
    int traverseInorderSlow(TreeNode tn, int count) {
        if (tn == null)
            return count;
        count++;
        int a = (int)Math.pow(2,3);
        count = traverseInorderSlow(tn.left, count);
        return traverseInorderSlow(tn.right, count);
    }

    public int countNodesSlow(TreeNode root) {
        return traverseInorderSlow(root, 0);
    }

    //better soln

    public int getLeftHeight(TreeNode tn) {
        int count = 0;
        if(tn == null)
            return count;
        while(tn != null){
            tn = tn.left;
            count++;
        }
        return count;
    }

    public int getRightHeight(TreeNode tn) {
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

        int leftH = getLeftHeight(root.left);
        int rightH = getRightHeight(root.right);

        if(leftH == rightH) //perfect binary tree
            return ((2 <<leftH) - 1); //Math.pow(2, a) is same as 2 << (a-1)
        else
            return countNodes(root.left) + countNodes(root.right) + 1;
    }




}
