package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by paanir on 9/25/17.
 */
/*
449. Serialize and Deserialize BST
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your
serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to
a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeAndDeserializeBST {

    private void inOrder(TreeNode root, List<String> list) {
        if (root == null)
            return;
        inOrder(root.left, list);
        list.add(Integer.toString(root.val));
        inOrder(root.right, list);
    }

    private void preOrder(TreeNode root, List<String> list) {
        if (root == null)
            return;
        list.add(Integer.toString(root.val));
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        List<String> list = new ArrayList<>();
        inOrder(root, list);
        List<String> list2 = new ArrayList<>();
        preOrder(root, list2);
        String joined1 = String.join(",", list);
        String joined2 = String.join(",", list2);
        System.out.println(joined1 + ";" + joined2);
        return joined1 + ";" + joined2;
    }

    private int binarySearch(int rootVal, int[] inOrder, int start, int end) { //is sorted so can do binary search
        if (start <= end) {
            int mid = start + (end - start) / 2;
            if (rootVal == inOrder[mid])
                return mid;
            else if (rootVal > inOrder[mid])
                return binarySearch(rootVal, inOrder, mid + 1, end);
            else
                return binarySearch(rootVal, inOrder, start, mid - 1);
        }
        return -1;
    }

    private TreeNode constructTree(int[] inOrder, int inStart, int inEnd, int[] preOrder, int preIndex) {
        if (inStart > inEnd || preIndex >= preOrder.length) {
            return null;
        }
        int rootVal = preOrder[preIndex];
        TreeNode root = new TreeNode(rootVal);
        int inIndex = binarySearch(rootVal, inOrder, inStart, inEnd);
        root.left = constructTree(inOrder, inStart, inIndex - 1, preOrder, preIndex + 1);
        root.right = constructTree(inOrder, inIndex + 1, inEnd, preOrder, preIndex + (inIndex - inStart + 1)); //preindex + number of left nodes
        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals(""))
            return null;
        String[] traversals = data.split(";");
        int[] inOrder = Arrays.stream(traversals[0].split(",")).mapToInt(Integer::parseInt).toArray();
        int[] preOrder = Arrays.stream(traversals[1].split(",")).mapToInt(Integer::parseInt).toArray();
        return constructTree(inOrder, 0, inOrder.length - 1, preOrder, 0);
    }

}
