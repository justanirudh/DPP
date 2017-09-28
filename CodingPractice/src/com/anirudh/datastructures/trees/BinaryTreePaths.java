package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 2/10/17.
 */
/*
257. Binary Tree Paths
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
 */
public class BinaryTreePaths {

    public static List<String> getPaths(String yet, TreeNode node) {
        if (node == null)
            return new ArrayList<>();

        String curr = yet + "->" +Integer.toString(node.val);

        List<String> lefts = getPaths(curr, node.left);

        List<String> rights = getPaths(curr, node.right);

        if(lefts.isEmpty() && rights.isEmpty()){
            List<String> l = new ArrayList<>();
            l.add(curr);
            return l;
        }
        else{
            lefts.addAll(rights);
            return lefts;
        }

    }


    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        String curr = Integer.toString(root.val);

        List<String> lefts = getPaths(curr, root.left);

        List<String> rights = getPaths(curr, root.right);

        if(lefts.isEmpty() && rights.isEmpty()){
            List<String> l = new ArrayList<>();
            l.add(curr);
            return l;
        }
        else
        {
            lefts.addAll(rights);
            return lefts;
        }
    }
}
