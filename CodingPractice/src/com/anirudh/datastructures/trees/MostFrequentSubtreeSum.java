package com.anirudh.datastructures.trees;

import java.util.*;

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

    class CompareFrequency implements Comparator<Map.Entry<Integer, Integer>> { // max heap
        public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
            return b.getValue() - a.getValue();
        }
    }

    private Map<Integer, Integer> frequency = new HashMap<>(); //make hashmap to count each sum's frequency

    private TreeNode postOrderCreate(TreeNode tn) {
        if (tn == null)
            return null;
        TreeNode leftNode = postOrderCreate(tn.left);
        TreeNode rightNode = postOrderCreate(tn.right);

        int leftSum = leftNode == null ? 0 : leftNode.val;
        int rightSum = rightNode == null ? 0 : rightNode.val;
        int subtreeSum = leftSum + rightSum + tn.val;
        TreeNode root = new TreeNode(subtreeSum); //in the new tree, val is sum of subtree
        root.left = leftNode;
        root.right = rightNode;

        //Count frequency of each subtreeSum
        frequency.put(subtreeSum, frequency.getOrDefault(subtreeSum, 0) + 1);

        return root;
    }

    private int[] findMostFrequentSums() { //Use max heap to get most frequent sum
        Queue<Map.Entry<Integer, Integer>> sumMaxHeap = new PriorityQueue<>(new CompareFrequency());
        for (Map.Entry<Integer, Integer> e: frequency.entrySet()) {
            sumMaxHeap.offer(e);
        }
        int currMax = sumMaxHeap.peek().getValue();

        List<Integer> res = new ArrayList<>();

        while (!sumMaxHeap.isEmpty()) {
            Map.Entry<Integer, Integer> currSum = sumMaxHeap.poll();
            int curr = currSum.getValue();
            if (curr == currMax)
                res.add(currSum.getKey());
            else
                break;
        }

        return res.stream().mapToInt(i -> i).toArray();

    }

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null)
            return new int[0];

        postOrderCreate(root); //create + calculate sum of (children + curr) in 1 pass + calculate frequency

        return findMostFrequentSums();

    }
}
