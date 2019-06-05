package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 1/1/18.
 */
class Node {
    int data;
    Node left, right, next, parent;

    Node(int item) {
        data = item;
        left = right = next = parent = null;
    }
}
