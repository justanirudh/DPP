package com.anirudh.companies_21_22.two_sigma;

import java.util.LinkedHashMap;
import java.util.Map;

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

/*
A special constructor is provided to create a linked hash map whose order of iteration is the order in which its
entries were last accessed, from least-recently accessed to most-recently (access-order).
This kind of map is well-suited to building LRU caches.
Invoking the put, putIfAbsent, get, getOrDefault, compute, computeIfAbsent, computeIfPresent, or merge methods results
in an access to the corresponding entry (assuming it exists after the invocation completes).
The replace methods only result in an access of the entry if the value is replaced. The putAll method generates one entry access for each mapping in the specified map, in the order that key-value mappings are provided by the specified map's entry set iterator. No other methods generate entry accesses. In particular, operations on collection-views do not affect the order of iteration of the backing map.

The removeEldestEntry(Map.Entry) method may be overridden to impose a policy for removing stale mappings automatically when new mappings are added to the map.

Things to remember:
1. Extending LinkedHashMap
2. in constructor: super(capacity, load=  0.5F, accessOrder = true)
3. Use super to access get and put methods
4. Override removeEldestEntry method to when super.size > capacity

Option 2: /*
Map to DIY queue
Map has {elements -> QueueNode {k,v,next, prev} }
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {

    int capacity;
    public LRUCache(int capacity) {
        super(capacity, 0.5F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return super.size() > capacity;
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
