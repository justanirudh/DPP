package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 3/4/17.
 */
/*
237. Delete Node in a Linked List Add to List

Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should
become 1 -> 2 -> 4 after calling your function.
 */
public class DeleteNodeLinkedList {
    public void deleteNode(ListNode node) {
        int temp = node.next.val;
        node.next = node.next.next;
        node.val = temp;
    }
}
