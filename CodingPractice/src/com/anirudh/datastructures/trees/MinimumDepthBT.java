package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 11/9/17.
 */
public class MinimumDepthBT {
    public int minDepthAux(TreeNode root, int depth) {
        if (root == null)
            return depth;
        else {
            if (root.left == null && root.right == null)
                return depth + 1;
            else if (root.left != null && root.right == null)
                return minDepthAux(root.left, depth + 1);
            else if (root.left == null && root.right != null)
                return minDepthAux(root.right, depth + 1);
            else
                return Math.min(minDepthAux(root.left, depth + 1), minDepthAux(root.right, depth + 1));
        }

    }

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        else {
            if (root.left == null && root.right == null)
                return 1;
            else if (root.left != null && root.right == null)
                return minDepthAux(root.left, 1);
            else if (root.left == null && root.right != null)
                return minDepthAux(root.right, 1);
            else
                return Math.min(minDepthAux(root.left, 1), minDepthAux(root.right, 1));
        }
    }
}
