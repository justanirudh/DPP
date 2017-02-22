package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 2/21/17.
 */
/*
86. Partition List Add to List

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
*/

public class PartitionList {
    public ListNode partition(ListNode head, int x) {

        if (head == null)
            return null;

        ListNode ltHead = null; //less than list
        ListNode ltCurr = null;
        ListNode geHead = null; //greater than equal to list
        ListNode geCurr = null;

        ListNode curr = head;
        while (curr != null) {
            int val = curr.val;
            if (val < x) {
                if (ltHead == null) {
                    ltHead = new ListNode(val);
                    ltCurr = ltHead;
                } else {
                    ListNode node = new ListNode(val);
                    ltCurr.next = node;
                    ltCurr = node;
                }
            } else {
                if (geHead == null) {
                    geHead = new ListNode(val);
                    geCurr = geHead;
                } else {
                    ListNode node = new ListNode(val);
                    geCurr.next = node;
                    geCurr = node;
                }
            }
            curr = curr.next;
        }
        if (ltHead != null) {
            ltCurr.next = geHead;
            return ltHead;
        } else
            return geHead;
    }
}
