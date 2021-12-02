package com.anirudh.interview_prep_2021_2022.two_sigma;

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
Map to DIY queue
Map has keys as elements to QueueNode {k,v,next, prev}
 */
public class LRUCache_DIY {
    //Using a Map with a custom queue
    //Or use LinkedHashMap with orderAccess argument as true (LRU gets enabled) and override
    //removeEldestEntry when we hit cache size. EPI Prob.12.3

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

    private Map<Integer, QueueNode> cache = new HashMap<>(); //KEY to the queuenode in the queue
    private int capacity;
    QueueNode head = null; //MRU
    QueueNode tail = null; //LRU

    public LRUCache_DIY(int capacity) {
        this.capacity = capacity;
    }

    void addNodeToHead(QueueNode qn) {
        qn.next = head;
        if (head != null)
            head.prev = qn;
        head = qn;
    }

    void moveNodeToHead(QueueNode qn) {
        if (head == qn)
            return;
        QueueNode prev = qn.prev;
        QueueNode next = qn.next;
        if (prev != null)
            prev.next = next;
        if (next != null)
            next.prev = prev;
        if (tail == qn) // decrement tail
            tail = prev;
        addNodeToHead(qn);
    }

    QueueNode removeTailNode() {
        QueueNode last = tail;
        QueueNode prev = tail.prev;
        if (prev != null)
            prev.next = null;
        tail = prev;
        return last;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        QueueNode qn = cache.get(key);
        moveNodeToHead(qn);
        return qn.val;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            QueueNode qn = cache.get(key);
            moveNodeToHead(qn);
            qn.val = value;
            return;
        }
        if (cache.size() == capacity) {
            QueueNode qn = removeTailNode();
            cache.remove(qn.key);
        }
        QueueNode qn = new QueueNode(key, value);
        cache.put(key, qn);
        if (cache.size() == 1) {// base case
            head = qn;
            tail = qn;
        } else {
            addNodeToHead(qn);
        }
    }

    public static void main(String[] args) {
        LRUCache_DIY cache = new LRUCache_DIY(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
