package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 2/20/17.
 */
/*
82. Remove Duplicates from Sorted List II Add to List

Given a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

 */
public class RemoveDuplicatesfromSortedListII {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode prevLast = null;
        ListNode last = head;
        ListNode curr = head;
        while (curr != null) {
            int rep = 0;
            while (curr != null && curr.val == last.val) {
                ++rep;
                curr = curr.next;
            }
            if (rep == 1) {
                prevLast = last;
                last = curr;
            } else {
                if (prevLast == null) {
                    head = curr;
                    last = curr;
                } else {
                    prevLast.next = curr;
                    last = curr;
                }
            }
        }
        return head;
    }
}
