package com.anirudh.datastructures;

/**
 * Created by paanir on 1/31/17.
 */
public class LCA_BST {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null)
            return null;
        if (p == null && q == null)
            return root;
        else if (p == null)
            return q;
        else if (q == null)
            return p;
        else { //all three are non=null
            if (p.val <= root.val && q.val >= root.val)
                return root;
            if (q.val <= root.val && p.val >= root.val)
                return root;
            if (p.val <= root.val && q.val <= root.val)
                return lowestCommonAncestor(root.left, p, q);
            else //(p.val >= root.val && q.val >= root.val)
                return lowestCommonAncestor(root.right, p, q);
        }
    }
}
