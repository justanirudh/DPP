package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by paanir on 7/31/17.
 */
/*
508. Most Frequent Subtree Sum
Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as
the sum of all the node values formed by the subtree rooted at that node (including the node itself).
So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 */
public class MostFrequentSubtreeSum {

    private TreeNode postOrderCreate(TreeNode tn) {
        if (tn == null)
            return null;
        TreeNode leftNode = postOrderCreate(tn.left);
        TreeNode rightNode = postOrderCreate(tn.right);

        int leftSum = leftNode == null ? 0 : leftNode.val;
        int rightSum = rightNode == null ? 0 : rightNode.val;
        TreeNode root = new TreeNode(leftSum + rightSum + tn.val);
        root.left = leftNode;
        root.right = rightNode;

        return root;
    }

    private void inOrderCount(TreeNode n, Map<Integer, Integer> frequency) {
        if (n == null)
            return;
        inOrderCount(n.left, frequency);

        Integer val = frequency.putIfAbsent(n.val, 1);
        if (val != null) //n.val mapping was already present
            frequency.put(n.val, frequency.get(n.val) + 1);

        inOrderCount(n.right, frequency);
    }

    private int[] findMostFrequentSums(Map<Integer, Integer> frequency) {
        int max = Integer.MIN_VALUE;
        List<Integer> sumsList = new ArrayList<>();
        for (int key : frequency.keySet()) { //get ones with highest frequency
            int value = frequency.get(key);
            if (value > max) {
                max = value;
                sumsList = new ArrayList<>();
                sumsList.add(key);
            } else if (value == max)
                sumsList.add(key);
        }
        return sumsList.stream().mapToInt(i -> i).toArray();//convert list of Integers to int arrays //Awesome!!
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null)
            return new int[0];

        TreeNode rootN = postOrderCreate(root); //create + calculate sum of (children + curr) in 1 pass

        Map<Integer, Integer> frequency = new HashMap<>();
        inOrderCount(rootN, frequency); //make hashmap to count each sum's frequency

        return findMostFrequentSums(frequency);

    }
}
