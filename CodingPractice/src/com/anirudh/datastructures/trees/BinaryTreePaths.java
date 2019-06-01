package com.anirudh.datastructures.trees;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 2/10/17.
 */
/*
257. Binary Tree Paths
Given a binary tree, return all node-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All node-to-leaf paths are:

["1->2->5", "1->3"]
 */
public class BinaryTreePaths {

    public List<String> getBinaryPaths(TreeNode node, String str, List<String> result) {

        if (node == null) { //can only be root as we will never traverse to a null node because of checks below
            return result;
        }

        String tillNow;
        if (str == null) //root
            tillNow = Integer.toString(node.val);
        else
            tillNow = str + "->" + node.val;

        if (node.left == null && node.right == null) { //if both null, we have reached a leaf
            result.add(tillNow);
            return result;
        } else if (node.left != null && node.right == null) { //if left is not null and right is null, only go to left
            return getBinaryPaths(node.left, tillNow, result);
        } else if (node.left == null) {  //if left is null and right is not null, only go to right
            return getBinaryPaths(node.right, tillNow, result);
        } else { //if both children non-null, go to both
            List<String> leftResult = getBinaryPaths(node.left, tillNow, result); //get left result and pass it to right
            return  getBinaryPaths(node.right, tillNow, leftResult);
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        return getBinaryPaths(root, null, result);
    }
}




/*

class Solution {

    List<String> paths;

    public void addPath(TreeNode node, String tillNow){

        if(node.left != null && node.right != null){
            addPath(node.left, tillNow + node.val + "->");
            addPath(node.right, tillNow + node.val + "->");

        }
        else if (node.left == null && node.right != null){
            addPath(node.right, tillNow + node.val + "->");

        }
        else if (node.left != null && node.right == null){
            addPath(node.left, tillNow + node.val + "->");
        }
        else{
            paths.add(tillNow + node.val);
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        paths = new ArrayList<>();
        if(root == null)
            return paths;
        String tillNow = "";
        if(root.left != null && root.right != null){
            addPath(root.left, tillNow + root.val + "->");
            addPath(root.right, tillNow + root.val + "->");

        }
        else if (root.left == null && root.right != null){
            addPath(root.right, tillNow + root.val + "->");

        }
        else if (root.left != null && root.right == null){
            addPath(root.left, tillNow + root.val + "->");
        }
        else{
            paths.add(tillNow + root.val);
        }
        return paths;

    }
}
 */
