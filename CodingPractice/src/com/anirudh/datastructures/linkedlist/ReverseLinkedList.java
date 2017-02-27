package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 2/26/17.
 */
/*
206. Reverse Linked List Add to List

Reverse a singly linked list
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode prev = null;
        ListNode curr = head;
        ListNode succ = head.next;
        while(true){
            curr.next = prev;
            prev = curr;
            curr = succ;
            if(curr == null)
                break;
            succ = succ.next;
        }
        return prev;
    }
}
