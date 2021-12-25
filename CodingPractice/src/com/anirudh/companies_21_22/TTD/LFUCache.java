package com.anirudh.companies_21_22.TTD;

/*
460. LFU Cache
Hard

2473

171

Add to List

Share
Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not already present.
When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting
a new item.
For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache.
The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation).
The use counter for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.

Example 1:

Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[3,4], cnt(4)=2, cnt(3)=3
 */

import java.util.HashMap;
import java.util.Map;

/*
Use a Map< Key -> MyQueueNode> | this is cacheMap
Use a Map <frequency -> DLL> | this is LRUMap
Use a DoubleLinkedList (DLL) with Node(k, v, prev, next, freq)
Use a minFreq var to track minimum freq at any point

Node(k, v, prev, next, freq)
DLL(head, tail): addFirst(), removeLast(), remove(node) | This will be the LRU cache for same frequency nodes

Put(k,v):
    if k exists in cacheMap
        k -> Node -> DLL
        Node: update value
        DLL: remove(node), graduate it to {freq+1 -> DLL}
        if(minFreq == freq && LRUMap{minFreq -> DLL} is empty list)
            minFreq++;
    if k doesnt exist
        if we have not reached capacity
            minFreq = 1
            add it cache
            add it to LRUMap, added in {1 -> DLL} using addFirst()
        if we have reached capacity
            minFreq = 1
            Go to LRUMap{minFreq -> DLL}, Do removeLast()
            Remove from Cache
            add new Node to cache
            add it to LRUMap, added in {1 -> DLL} using addFirst()
Get(k):
    if k doesnt exist in cache
        return -1;
    else
        k -> Node
        (return) get value from Node
        Node -> freq -> DLL
        DLL: remove(node), graduate it to {freq+1 -> DLL}
        if(freq == min && LRUMap{1 -> DLL} is empty list)
            minFreq++;

 */
public class LFUCache {

    class QNode {
        int k;
        int v;
        int freq;
        QNode prev;
        QNode next;

        QNode(int k, int v) {
            this.k = k;
            this.v = v;
            freq = 1;
        }
    }

    class DLL {
        QNode head;
        QNode tail;
        int size;

        DLL() {
            head = new QNode(0, 0); //sentinel head
            tail = new QNode(0, 0); //sentinel tail
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void addFirst(QNode node) {
            QNode next = head.next;
            head.next = node;
            node.prev = head;
            node.next = next;
            next.prev = node;
            size++;
        }

        QNode removeLast() {
            QNode node = tail.prev;
            QNode prev = node.prev;
            prev.next = tail;
            tail.prev = prev;
            size--;
            return node;
        }

        void remove(QNode node) {
            QNode prev = node.prev;
            QNode next = node.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }
    }

    Map<Integer, QNode> cacheMap;
    Map<Integer, DLL> freqMap;
    int minFreq;
    int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>();
        freqMap = new HashMap<>();
        minFreq = 0;
    }

    private void graduateNode(QNode node) {
        int oldFreq = node.freq;
        DLL oldDLL = freqMap.get(oldFreq);
        oldDLL.remove(node);
        node.freq = oldFreq + 1;
        DLL newDLL = freqMap.getOrDefault(node.freq, new DLL());
        newDLL.addFirst(node);
        freqMap.put(node.freq, newDLL);

        //check if minFreq needs to be updated
        if (minFreq == oldFreq && oldDLL.size == 0)
            minFreq = node.freq;
    }

    public int get(int key) {
        if(!cacheMap.containsKey(key)) {
            return -1;
        }
        QNode qn = cacheMap.get(key);
        graduateNode(qn);
        return qn.v;
    }

    public void put(int key, int value) {
        if(capacity == 0)
            return;
        if (cacheMap.containsKey(key)) {
            QNode node = cacheMap.get(key);
            //update value
            node.v = value;
            graduateNode(node);
            return;
        }
        if (cacheMap.size() == capacity) {
            DLL dll = freqMap.get(minFreq);
            QNode qn = dll.removeLast(); //LRU
            cacheMap.remove(qn.k);
        }
        minFreq = 1;
        DLL dll = freqMap.getOrDefault(minFreq, new DLL());
        QNode qn = new QNode(key, value);
        dll.addFirst(qn);
        freqMap.put(minFreq, dll);
        cacheMap.put(key, qn);
    }
}
