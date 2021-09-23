package com.anirudh.interview_prep_2021.spotify;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 2/22/17.
 */
/*
146. LRU Cache Add to List

Design and implement a data structure for Least Recently Used (LRU) cache.
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it
should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

 */
/*
Map to DIY double ended queue implemented as Double Linked List
Map has keys as elements to QueueNode {k,v,next, prev}
 */
public class LRUCacheDLL {
    //Using a Map with a custom queue
    //Or use LinkedHashMap with orderAccess argument as true (LRU gets enabled) and override
    //removeEldestEntry when we hit cache size. EPI Prob.12.3

    class MyQueue {
        QueueNode head = null; //MRU
        QueueNode tail = null; //LRU
    }

    class QueueNode {
        int key;
        int val;
        QueueNode next;
        QueueNode prev;

        QueueNode(int key, int value) {
            this.key = key;
            this.val = value;
        }
    }

    private MyQueue queue = new MyQueue();
    private Map<Integer, QueueNode> cache = new HashMap<>(); //KEY to the queuenode in the queue
    private int capacity;
    private int size = 0;

    public LRUCacheDLL(int capacity) {
        this.capacity = capacity;
    }

    private void moveToHead(QueueNode qn) {
        qn.next = queue.head;
        qn.prev = null;
        queue.head.prev = qn;
        queue.head = qn;
    }

    private void removeFromTail(QueueNode qn) {
        QueueNode prev = qn.prev;
        if (prev != null)
            prev.next = null;
        queue.tail = prev;
    }

    private void addToMap(int key, QueueNode qn) {
        cache.put(key, qn);
        size++;
    }

    private void removeFromMap(int key) {
        cache.remove(key);
        size--;
    }

    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;
        else { //get key, change its position in MyQueue
            QueueNode qn = cache.get(key);
            int val = qn.val;
            //change position in MyQueue to head
            if (queue.head == qn) {
                //if head, DO nothing
            } else if (queue.tail == qn) { //if tail, bring it to head
                removeFromTail(qn);
                moveToHead(qn);
            } else { // in between
                //remove from between
                QueueNode prev = qn.prev;
                QueueNode next = qn.next;
                prev.next = next;
                next.prev = prev;
                moveToHead(qn);
            }
            return val;
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            get(key); //doing for the side effects, bring it to head
            queue.head.val = value; //update value for the key, if changed
            return;
        }
        //LRU does not have the key
        QueueNode qn = new QueueNode(key, value);
        if (size == capacity) { //no space, delete LRU queue node and add the new value to head
            QueueNode qnLRU = queue.tail; //remove LRU node from queue
            removeFromTail(qnLRU);
            removeFromMap(qnLRU.key);
            if (capacity == 1) { //add new node to front of queue
                queue.head = qn;
                queue.tail = qn;
            } else
                moveToHead(qn);
        } else { //space is there
            if (size == 0) {
                queue.head = qn;
                queue.tail = qn;
            } else //add to front of queue
                moveToHead(qn);
        }
        addToMap(key, qn);
    }


    public static void main(String[] args) {
        LRUCacheDLL cache = new LRUCacheDLL(1 /* capacity */);

        cache.put(2, 1);
        System.out.println(cache.get(2));       // returns 1
        cache.put(3, 2);
        System.out.println(cache.get(2));       // returns -1
        System.out.println(cache.get(3));       // returns 2

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
