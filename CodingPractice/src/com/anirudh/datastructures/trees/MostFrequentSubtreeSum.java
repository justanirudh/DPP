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

    int postOrder(TreeNode node, Map<Integer, Integer> sumCount) {
        if(node == null)
            return 0;
        int left = postOrder(node.left, sumCount);
        int right = postOrder(node.right, sumCount);
        int sum = left + right + node.val;
        sumCount.put(sum, sumCount.getOrDefault(sum, 0) + 1);
        return sum;
    }


    class CompareSums implements Comparator<Map.Entry<Integer,Integer>> { //max heap
        public int compare(Map.Entry<Integer,Integer> a, Map.Entry<Integer,Integer> b) {
            return b.getValue() - a.getValue();
        }
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        //Track MaxFreq
        if(root == null)
            return new int[0];
        Map<Integer, Integer> sumCount = new HashMap<>();
        postOrder(root, sumCount);

        //Now just find MaxFreq value in map
        Queue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>(new CompareSums());
        for (Map.Entry<Integer,Integer> e : sumCount.entrySet()) {
            pq.offer(e);
        }
        int maxCount = pq.peek().getValue();
        List<Integer> resList = new ArrayList<>();
        while(!pq.isEmpty() && pq.peek().getValue() == maxCount) {
            resList.add(pq.peek().getKey());
            pq.poll();
        }
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); ++i) {
            res[i] = resList.get(i);
        }
        return res;
    }
}
