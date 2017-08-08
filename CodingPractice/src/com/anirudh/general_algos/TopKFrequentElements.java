package com.anirudh.general_algos;

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

    class Pair {
        int num;
        int count;

        public Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        //count the frequency for each element
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // create a min heap
        Queue<Pair> queue = new PriorityQueue<>((Pair a, Pair b)  -> a.count - b.count); //sorting by count

        //maintain a heap of size k.
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Pair p = new Pair(entry.getKey(), entry.getValue());
            queue.add(p);
            if (queue.size() > k) {
                Pair pp = queue.remove(); //removes from head (smallest value)
                System.out.println(pp.num + ", count= " + pp.count);
            }
        }

        //get all elements from the heap
        List<Integer> result = new ArrayList<>();
        while (queue.size() > 0) {
            result.add(queue.remove().num);
        }
        //reverse the order
        Collections.reverse(result);

        return result;
    }

    public static void main(String[] args) {
        TopKFrequentElements tk = new TopKFrequentElements();
        tk.topKFrequent(new int[]{1,1,1,2,2,3}, 2);
    }
}
