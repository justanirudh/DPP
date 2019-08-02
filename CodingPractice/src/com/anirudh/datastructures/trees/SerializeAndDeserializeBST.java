package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

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
    //use inorder and preorder to uniquely identify a tree

    private void inOrder(TreeNode node, StringJoiner sj) {
        if (node == null)
            return;
        inOrder(node.left, sj);
        sj.add(Integer.toString(node.val));
        inOrder(node.right, sj);
    }

    private void preOrder(TreeNode node, StringJoiner sj) {
        if (node == null)
            return;
        sj.add(Integer.toString(node.val));
        preOrder(node.left, sj);
        preOrder(node.right, sj);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //i,n,o,r,d,e,r;p,r,e,o,r,d,e,r
        if (root == null)
            return null;
        StringJoiner in = new StringJoiner(",");
        StringJoiner pre = new StringJoiner(",");
        inOrder(root, in);
        preOrder(root, pre);
        return in.toString() + ";" + pre.toString();
    }


    private List<Integer> inOrder;
    private List<Integer> preOrder;

    private int doBinarySearch(int start, int end, int val) {
        if (start > end)
            return -1; //not found
        int mid = start + (end - start) / 2;
        int midElem = inOrder.get(mid);
        if (val == midElem)
            return mid;
        else if (val < midElem)
            return doBinarySearch(start, mid - 1, val);
        else
            return doBinarySearch(mid + 1, end, val);
    }


    private int getLeftSubtreeSize(int rootValIdx, int inStartIdx) {
        return rootValIdx - inStartIdx + 1;
    }

    private TreeNode contructTree(int preIdx, int inStartIdx, int inEndIdx) {

        if (inStartIdx > inEndIdx || preIdx >= preOrder.size()) //it is a leaf
            return null;

        int rootVal = preOrder.get(preIdx); //root
        TreeNode root = new TreeNode(rootVal);
        int rootIdx = doBinarySearch(inStartIdx, inEndIdx, rootVal);

        root.left = contructTree(preIdx + 1, inStartIdx, rootIdx - 1);
        root.right = contructTree(preIdx + getLeftSubtreeSize(rootIdx, inStartIdx), rootIdx + 1, inEndIdx);
        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        /*
        1. Get the 1st element of preOrder list
        2. find the element in inOrder list (do binary search)
        3. thats the root
        4. root.left = repeat 1. and 2. (1) would be the Idx(1) + 1 in preOrder list
        5. root.right = repeat 1. and 2. (1) would be Idx(1) + 1 + size_of_left_subtree  in preOrder list
         */
        if (data == null)
            return null;
        String[] traversals = data.split(";");
        inOrder = Arrays.stream(traversals[0].split(",")).map(Integer::parseInt).collect(Collectors.toList());
        preOrder = Arrays.stream(traversals[1].split(",")).map(Integer::parseInt).collect(Collectors.toList());

        return contructTree(0, 0, inOrder.size() - 1);
    }

}
