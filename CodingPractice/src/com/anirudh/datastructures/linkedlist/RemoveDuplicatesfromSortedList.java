package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 2/20/17.
 */
/*
83. Remove Duplicates from Sorted List Add to List

Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

 */
public class RemoveDuplicatesfromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return head;
        if (head.next == null)
            return head;
        ListNode last = head;
        ListNode curr = head;
        while (curr != null) {
            while (curr != null && curr.val == last.val)
                curr = curr.next;
            last.next = curr;
            last = curr;
        }
        return head;
    }
}
