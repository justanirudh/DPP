package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 3/1/17.
 */
/*
19. Remove Nth Node From End of List Add to List

Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
 */
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return head;
        ListNode slow = head;
        ListNode fast = slow;
        int dist = n;
        while (dist != 0 && fast != null) {
            fast = fast.next;
            --dist;
        }
        if (fast == null) { //removing the first element
            head = head.next;
            return head;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //node after slow needs to be removed
        slow.next = slow.next.next;
        return head;
    }
}
