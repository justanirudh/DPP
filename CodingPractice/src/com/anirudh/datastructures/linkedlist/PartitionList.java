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
        ListNode lessHead = new ListNode(-1);
        ListNode lessCurr = lessHead;
        ListNode moreHead = new ListNode(-1);
        ListNode moreCurr = moreHead;

        ListNode curr = head;
        if(head == null || head.next == null)
            return head;

        while (curr != null) {
            if(curr.val  < x) {
                lessCurr.next = curr;
                lessCurr = curr;
            }
            else {
                moreCurr.next = curr;
                moreCurr = curr;
            }
            curr = curr.next;
        }
        moreCurr.next = null;
        lessCurr.next = moreHead.next; //skip sentinel
        return lessHead.next; // skip sentinel
    }
}
