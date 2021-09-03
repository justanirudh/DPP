package com.anirudh.interview_prep_2021.facebook.lc_last_6m;

import com.anirudh.datastructures.trees.TreeNode;

/**
 * Created by paanir on 1/31/17.
 */

//like preorder traversal. O(n) time O(h) space
// do lca in left and right both
public class LCA_BT {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null)
            return null;
        if (p == null && q == null)
            return root;
        else if (p == null)
            return q;
        else if (q == null)
            return p;
        else { //all three are non-null
            if (root.val == p.val || root.val == q.val)
                return root;

            //cant make a decision like BST, so go both ways
            TreeNode lcaLeft = lowestCommonAncestor(root.left, p, q);
            TreeNode lcaRight = lowestCommonAncestor(root.right, p, q);

            if (lcaLeft != null && lcaRight != null) //both non-null
                return root;
            else if (lcaLeft == null && lcaRight == null) //both null
                return null; //elements are not in this subtree
            else if (lcaLeft == null)
                return lcaRight;
            else
                return lcaLeft;

        }
    }
}
