package com.anirudh.datastructures.heaps;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by paanir on 10/1/17.
 */
/*

295. Find Median from Data Stream
Median is the middle value in an ordered integer list. If the size of the list is even,
there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2
 */
//https://leetcode.com/problems/find-median-from-data-stream/solution/#approach-3-two-heaps-accepted
public class FindMedianFromDataStream {
    //using two balanced heaps. first heap is a max heap containing lower half of array
    //second heap is a min heap contating upper half of the array
    //if we keep heaps balanced,
    //if the size of two heaps is same, avg of min and max elems will give us the median
    //if size of max heap (smaller elems) > sie of min heap (higher elems), just return max elem of max heap as odd num. of elems
    //balancing is important and alwaus,
    // size of smaller elems heap  = size of larger elems heap OR (even elems)
    //size of smaller elems heap  = size of larger elems heap + 1 (odd elems)

    Queue<Integer> lo; //maxHeap
    Queue<Integer> high; //minHeap

    /**
     * initialize your data structure here.
     */
    public FindMedianFromDataStream() {
        lo = new PriorityQueue<>(Collections.reverseOrder()); //maxheap
        high = new PriorityQueue<>(); //minHeap
    }

    //logn
    public void addNum(int num) {
        lo.offer(num); //adding to maxHeap
        high.offer(lo.poll()); //removing max from maxHeap and adding to minHeap (balancing)
        if (lo.size() < high.size()) //if size of maxheap < size of minHeap, remove from minheap and add to max heap
            lo.offer(high.poll());
    }

    //O(1)
    public double findMedian() {
        if (lo.size() == high.size())
            return (lo.peek() + high.peek()) / 2.0; //2.0 is imp. to get fractional values
        else //lo.size > high.size
            return lo.peek();
    }
}
