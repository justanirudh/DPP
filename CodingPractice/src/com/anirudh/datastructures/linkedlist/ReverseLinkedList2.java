package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 2/27/17.
 */
/*
92. Reverse Linked List II

Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
 */
public class ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m <= 0 || n <= 0 || n <= m)
            return head;
        if (head == null || head.next == null)
            return head;
        ListNode left = head; //left sentinel
        ListNode prev;
        ListNode curr;
        ListNode succ;
        int count;
        if (m == 1) { // reversing from head of list
            prev = null;
            curr = head;
            succ = head.next;
        } else { //normal case
            count = 0;
            while (count != m - 2) {
                left = left.next;
                count++;
            }
            prev = left;
            curr = prev.next;
            succ = curr.next;
        }
        count = 0;
        while (count != n - m + 1) { //reverse between the given nums
            curr.next = prev;
            prev = curr;
            curr = succ;
            if (curr == null)
                break;
            succ = succ.next;
            count++;
        }
        if (m == 1) { //if reversing from head
            head = prev;
            left.next = curr;
            return head;
        }
        ListNode right = left.next;
        left.next = prev;
        right.next = curr;
        return head;
    }
}
