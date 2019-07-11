package com.anirudh.datastructures.heaps;

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

public class TopKFrequentElements {

    private class Pair {
        int num;
        int count;

        Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    private class CompareByCount implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            return a.count - b.count;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        //O(n) time
        Map<Integer, Pair> counts = new HashMap<>(); //num ->counts
        for (int num : nums) {
            if (!counts.containsKey(num))
                counts.put(num, new Pair(num, 0));
            counts.put(num, new Pair(num, counts.get(num).count + 1));
        }

        Queue<Pair> pairs = new PriorityQueue<>(new CompareByCount());

        //O(nlogK)
        for (Pair value : counts.values()) {
            pairs.offer(value);
            if (pairs.size() > k)
                pairs.poll(); //remove the least frequent
        }

        //O(n)
        List<Integer> res = new ArrayList<>();
        for (Pair p : pairs) {
            res.add(p.num);
        }
        return res;
    }

    public static void main(String[] args) {
        TopKFrequentElements tk = new TopKFrequentElements();
        tk.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
    }
}
