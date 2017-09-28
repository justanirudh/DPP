package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 2/15/17.
 */
/*
142. Linked List Cycle II

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

 */
public class DetectCycle {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return head;
        ListNode slow = head.next;
        ListNode fast = null;
        if (slow == null)
            return null;
        else
            fast = slow.next;
        while (slow != null && fast != null && slow != fast) {
            slow = slow.next;
            fast = fast.next;
            if (fast == null)
                break;
            else
                fast = fast.next;
        }
        if (slow == null || fast == null)
            return null; //no cycle
        //slow == fast right now
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
