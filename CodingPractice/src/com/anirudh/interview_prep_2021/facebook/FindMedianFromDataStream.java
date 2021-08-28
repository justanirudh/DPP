package com.anirudh.interview_prep_2021.facebook;

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

    /*
    pproach 3: Two Heaps

Intuition

The above two approaches gave us some valuable insights on how to tackle this problem. Concretely, one can infer two things:

    If we could maintain direct access to median elements at all times, then finding the median would take a constant amount of time.
    If we could find a reasonably fast way of adding numbers to our containers, additional penalties incurred could be lessened.

But perhaps the most important insight, which is not readily observable, is the fact that we only need a consistent way to access the median elements. Keeping the entire input sorted is not a requirement.

    Well, if only there were a data structure which could handle our needs.

As it turns out there are two data structures for the job:

    Heaps (or Priority Queues [1])
    Self-balancing Binary Search Trees (we'll talk more about them in Approach 4)

Heaps are a natural ingredient for this dish! Adding elements to them take logarithmic order of time. They also give direct access to the maximal/minimal elements in a group.

If we could maintain two heaps in the following way:

    A max-heap to store the smaller half of the input numbers
    A min-heap to store the larger half of the input numbers

This gives access to median values in the input: they comprise the top of the heaps!

Wait, what? How?

If the following conditions are met:

    Both the heaps are balanced (or nearly balanced)
    The max-heap contains all the smaller numbers while the min-heap contains all the larger numbers

then we can say that:

    All the numbers in the max-heap are smaller or equal to the top element of the max-heap (let's call it xxx)
    All the numbers in the min-heap are larger or equal to the top element of the min-heap (let's call it yyy)

Then xxx and/or yyy are smaller than (or equal to) almost half of the elements and larger than (or equal to) the other half. That is the definition of median elements.

This leads us to a huge point of pain in this approach: balancing the two heaps!

Algorithm

    Two priority queues:
        A max-heap lo to store the smaller half of the numbers
        A min-heap hi to store the larger half of the numbers

    The max-heap lo is allowed to store, at worst, one more element more than the min-heap hi. Hence if we have processed kkk elements:
        If k=2∗n+1(∀ n∈Z)k = 2*n + 1 \quad (\forall \, n \in \mathbb{Z})k=2∗n+1(∀n∈Z), then lo is allowed to hold n+1n+1n+1 elements, while hi can hold nnn elements.
        If k=2∗n(∀ n∈Z)k = 2*n \quad (\forall \, n \in \mathbb{Z})k=2∗n(∀n∈Z), then both heaps are balanced and hold nnn elements each.

    This gives us the nice property that when the heaps are perfectly balanced, the median can be derived from the tops of both heaps. Otherwise, the top of the max-heap lo holds the legitimate median.

    Adding a number num:
        Add num to max-heap lo. Since lo received a new element, we must do a balancing step for hi. So remove the largest element from lo and offer it to hi.
        The min-heap hi might end holding more elements than the max-heap lo, after the previous operation. We fix that by removing the smallest element from hi and offering it to lo.

    The above step ensures that we do not disturb the nice little size property we just mentioned.

A little example will clear this up! Say we take input from the stream [41, 35, 62, 5, 97, 108]. The run-though of the algorithm looks like this:

Adding number 41
MaxHeap lo: [41]           // MaxHeap stores the largest value at the top (index 0)
MinHeap hi: []             // MinHeap stores the smallest value at the top (index 0)
Median is 41
=======================
Adding number 35
MaxHeap lo: [35]
MinHeap hi: [41]
Median is 38
=======================
Adding number 62
MaxHeap lo: [41, 35]
MinHeap hi: [62]
Median is 41
=======================
Adding number 4
MaxHeap lo: [35, 4]
MinHeap hi: [41, 62]
Median is 38
=======================
Adding number 97
MaxHeap lo: [41, 35, 4]
MinHeap hi: [62, 97]
Median is 41
=======================
Adding number 108
MaxHeap lo: [41, 35, 4]
MinHeap hi: [62, 97, 108]
Median is 51.5

     */
public class FindMedianFromDataStream {
    //using two balanced heaps. first heap is a max heap containing lower half of array
    //second heap is a min heap containing upper half of the array
    //if we keep heaps balanced,
    //if the size of two heaps is same, avg of min and max elems will give us the median
    //if size of max heap (smaller elems) > size of min heap (higher elems), just return max elem of max heap as odd num. of elems
    //balancing is important and always,
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
        /*
        The balancing step is required. lets say a number comes that is greater than element of both heaps
        just adding it to lo will be wrong as now we have an elem in low that is greater than an elem in high
        hence, do a high.offer(lo.poll()) so that all elems of low are smaller than all elems in high
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
