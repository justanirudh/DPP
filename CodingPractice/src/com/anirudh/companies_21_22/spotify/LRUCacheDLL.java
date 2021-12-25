package com.anirudh.companies_21_22.spotify;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/*
146. LRU Cache
Medium

11304

442

Add to List

Share
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.



Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
 */
public class LRUCacheDLL {

    static class DLLNode {
        int key;
        int value;
        DLLNode next;
        DLLNode prev;

        DLLNode(int k, int v) {
            key = k;
            value = v;
            next = null;
            prev = null;
        }
    }

    Map<Integer, DLLNode> cache;
    int capacity;
    int size;
    DLLNode head;
    DLLNode tail;

    public LRUCacheDLL(int capacity) {
        cache = new HashMap<>();
        head = new DLLNode(-1, -1); //sentinel
        tail = new DLLNode(-1, -1); //sentinel
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        size = 0;
    }

    void addFirst(DLLNode node) {
        DLLNode newNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = newNext;
        newNext.prev = node;
    }

    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;
        DLLNode node = cache.get(key);
        //remove from DLL
        DLLNode prev = node.prev;
        DLLNode next = node.next;
        prev.next = next;
        next.prev = prev;
        //move it to head
        addFirst(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.get(key).value = value;
            get(key); //for side effects
            return;
        }
        if (size == capacity) {
            //remove LRU elem
            DLLNode last = tail.prev;
            DLLNode prev = last.prev;
            prev.next = tail;
            tail.prev = prev;
            size--;
            cache.remove(last.key);
        }
        DLLNode node = new DLLNode(key, value);
        addFirst(node); //put new elem at head;
        size++;
        cache.put(key, node);
    }
}
