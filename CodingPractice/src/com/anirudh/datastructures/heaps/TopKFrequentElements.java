package com.anirudh.datastructures.heaps;

import scala.Int;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by paanir on 8/6/17.
 */
/*
347. Top K Frequent Elements
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note:
You may assume k is always valid, 1 < k < number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */

public class TopKFrequentElements {

    static class CompareFrequency implements Comparator<Map.Entry<Integer, Integer>> {
        public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
            return a.getValue() - b.getValue();
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        //calculate frequencies
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
                counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        //T = O(nlogk)
        Queue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(new CompareFrequency()); //queue of k size
        for (Map.Entry<Integer, Integer> p : counts.entrySet()) {
            minHeap.offer(p); //log k operation
            if (minHeap.size() == k + 1)
                minHeap.poll(); //remove the least frequent, logk operation
        }

        return new ArrayList<>(minHeap).stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        TopKFrequentElements tk = new TopKFrequentElements();
        int[] arr = {1, 1, 2, 3, 4, 1, 1, 2, 2, 3, 4, 5, 5, 4, 2};
        System.out.println(tk.topKFrequent(arr, 2));
    }
}
