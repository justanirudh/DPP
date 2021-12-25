package com.anirudh.companies_21_22.TTD;

import java.util.HashMap;
import java.util.Map;

public class MRUCache {

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

    public MRUCache(int capacity) {
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
        if (size == capacity) { //remove MRU instead of LRU
            DLLNode first = head.next;
            DLLNode next = first.next;
            head.next = next;
            next.prev = head;
            size--;
            cache.remove(first.key);
        }
        DLLNode node = new DLLNode(key, value);
        addFirst(node); //put new elem at head;
        size++;
        cache.put(key, node);
    }
}

