package com.anirudh.a_prep_2023.practice;

/**
 * Created by paanir on 8/3/19.
 */
/*
297. Serialize and Deserialize Binary Tree
Hard

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored
in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or
another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to
a string and this string can be deserialized to the original tree structure.

Example:

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"

Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to
follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

 */

import com.anirudh.datastructures.trees.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringJoiner;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

/*
Problem 297
Only use PREORDER. Important thing is to also encode null values.
Both pre/post and inorder are required to uniqely identify a tree IF the null values have not been encoded
 */
public class SerializeandDeserializeBT {

    private void preOrder(TreeNode tn, StringJoiner sj) {
        if (tn == null) {
            sj.add("null"); //dont use empty string. new ArrayDeque<>(Arrays.asList(data.split(","))); does work as expected, null strings get ignored
            return;
        }
        sj.add(Integer.toString(tn.val));
        preOrder(tn.left, sj);
        preOrder(tn.right, sj);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringJoiner sj = new StringJoiner(",");
        preOrder(root, sj);
        return sj.toString();
    }

    private TreeNode createTree(Deque<String> pre) {
        String curr = pre.poll();
        if (curr.equals("null"))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(curr));
        root.left = createTree(pre);
        root.right = createTree(pre);
        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> pre = new ArrayDeque<>(Arrays.asList(data.split(",")));
        return createTree(pre);

    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));