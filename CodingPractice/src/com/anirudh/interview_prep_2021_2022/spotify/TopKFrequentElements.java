package com.anirudh.interview_prep_2021_2022.spotify;

import java.util.*;

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

/*
Create a minHeap of size K with this comparator
    class CompareFrequency implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            return a.freq - b.freq;
        }
    }
    Pass in the comparator to PriorityQueue
 */
public class TopKFrequentElements {

    class Pair {
        int num;
        int freq;

        Pair(int num) {
            this.num = num;
            freq = 0;
        }
    }

    class CompareFrequency implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            return a.freq - b.freq;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        //calculate frequencies
        Map<Integer, Pair> counts = new HashMap<>();
        for (int num : nums) {
            if (!counts.containsKey(num))
                counts.put(num, new Pair(num));
            counts.get(num).freq++;
        }

        //T = O(nlogk)
        Queue<Pair> minHeap = new PriorityQueue<>(new CompareFrequency());
        for (Pair p : counts.values()) {
            minHeap.offer(p);
            if (minHeap.size() > k)
                minHeap.poll(); //remove the least frequent
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            res.add(minHeap.poll().num);
        }
        return res;
    }

    public static void main(String[] args) {
        TopKFrequentElements tk = new TopKFrequentElements();
        int[] arr = {1, 1, 2, 3, 4, 1, 1, 2, 2, 3, 4, 5, 5, 4, 2};
        System.out.println(tk.topKFrequent(arr, 2));
    }
}
