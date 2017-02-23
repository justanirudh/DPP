package com.anirudh.datastructures.linkedlist;

import java.util.HashMap;

/**
 * Created by paanir on 2/22/17.
 */
/*
146. LRU Cache Add to List

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

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
public class LRUCache {

    Queue queue;
    static HashMap<Integer, QueueNode> map;
    int capacity;
    int numElems;

    //TODO: Instead of homemade queue, we can use ArrayList<KeyValueClass>
    class Queue {
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

    public LRUCache(int capacity) {
        queue = new Queue();
        map = new HashMap<>();
        this.capacity = capacity;
        this.numElems = 0;
    }

    private void moveToHead(QueueNode qn) {
        qn.next = queue.head;
        qn.prev = null;
        queue.head.prev = qn;
        queue.head = qn;
    }

    private void removeFromTail(QueueNode qn){
        QueueNode prev = qn.prev;
        prev.next = null;
        queue.tail = prev;
    }

    private void addToMap(int key, QueueNode qn){
        map.put(key, qn);
        numElems++;
    }

    private void removeFromMap(int key){
        map.remove(key);
        numElems--;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        else { //get key, change its position in Queue, remap map(nothing needs to be done as Java)
            QueueNode qn = map.get(key);
            int val = qn.val;
            //change position in Queue to head
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
        if (map.containsKey(key)) {
            get(key); //doing for the side effects, bring it to head
            queue.head.val = value; //update value
            return;
        }
        if (numElems == capacity) { //no space, delete LRU queue node and add the new value to head
            if (capacity == 1) { // only 1 capacity
                //remove LRU node from queue
                QueueNode qn = queue.tail; //= queue.head
                removeFromMap(qn.key);
                //add new node to front of queue
                QueueNode qnNew = new QueueNode(key, value);
                queue.head = qnNew;
                queue.tail = qnNew;
                addToMap(key, qnNew);
                return;
            }
            //remove LRU node from queue
            QueueNode qn = queue.tail;
            removeFromTail(qn);
            removeFromMap(qn.key);
            //add new node to front of queue
            QueueNode qnNew = new QueueNode(key, value);
            moveToHead(qnNew);
            addToMap(key, qnNew);
        } else { //space is there
            QueueNode qn = new QueueNode(key, value);
            if (numElems == 0) {
                //add to front of queue
                queue.head = qn;
                queue.tail = qn;
                addToMap(key, qn);
                return;
            }
            //add to front of queue
            moveToHead(qn);
            addToMap(key, qn);
        }
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(1 /* capacity */);

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
