package com.anirudh.datastructures.heaps;

import scala.Int;

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
            if (minHeap.size() == k + 1)
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
//        Map<Integer, Integer> initMap = new HashMap<>();
//        for (Integer elem : arr) {
//            if (initMap.containsKey(elem))
//                initMap.put(elem, initMap.get(elem) + 1);
//            else
//                initMap.put(elem, 1);
//        }
//        Map<Integer, List<Integer>> map = new TreeMap<>(); //reverse initmap
//        for (Map.Entry<Integer, Integer> pair : initMap.entrySet()) {
//            int value = pair.getValue();
//            int key = pair.getKey();
//            if (!map.containsKey(value)) {
//                List<Integer> elems = new ArrayList<>();
//                elems.add(key);
//                map.put(value, elems);
//            } else {
//                List<Integer> elems = map.get(value);
//                elems.add(key);
//                map.put(value, elems);
//            }
//        }
        //Now the map is sorted by key, which is the frequency of each number
//        int count = k;
//        while (count != 0) {
//
//        }

    }
}
