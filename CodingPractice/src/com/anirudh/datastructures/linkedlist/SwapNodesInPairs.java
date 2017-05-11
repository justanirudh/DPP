package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 2/26/17.
 */
/*
24. Swap Nodes in Pairs Add to List

Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode prev = null;
        ListNode num1 = head;
        ListNode num2 = head.next;
        ListNode succ = num2.next;
        while (true) {
            if (prev == null) {//first swap
                num2.next = num1;
                num1.next = succ;
                head = num2;
            } else {
                num2.next = num1;
                num1.next = succ;
                prev.next = num2;
            }
            prev = num1;
            num1 = succ;
            if (num1 == null)
                break;
            num2 = succ.next;
            if (num2 == null)
                break;
            succ = num2.next;
        }
        return head;
    }
}
