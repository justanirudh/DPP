package com.anirudh.interview_prep_2021_2022.spotify;

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

//using two balanced heaps. first heap is a max heap containing lower half of array
//second heap is a min heap containing upper half of the array
//if we keep heaps balanced,
//if the size of two heaps is same, avg of min and max elems will give us the median
//if size of max heap (smaller elems) > size of min heap (higher elems), just return max elem of max heap as odd num. of elems
//balancing is important and always,
// size of smaller elems heap  = size of larger elems heap OR (even elems)
//size of smaller elems heap  = 1 + size of larger elems heap (odd elems)

public class FindMedianFromDataStream {

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
        /*
        The balancing step is required. lets say a number comes that is greater than element of both heaps
        just adding it to lo will be wrong as now we have an elem in low that is greater than an elem in high
        hence, do a high.offer(lo.poll()) after that so that all elems of low are smaller than all elems in high
         */
        lo.offer(num); //adding the new element to maxHeap
        high.offer(lo.poll()); //removing max element from maxHeap and add it to minHeap (balancing)
        /*
        now just making sure size of lo is >= size of high because during find median method, we resort to
        taking it from low
         */
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
