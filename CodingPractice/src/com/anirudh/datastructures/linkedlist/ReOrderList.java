package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 2/14/17.
 */
/*

143. Reorder List
Description  Submission  Solutions  Add to List
Total Accepted: 84369
Total Submissions: 340550
Difficulty: Medium
Contributors: Admin

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */
public class ReOrderList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //Go till the end for every element and bring it to the first half

    //O(n^2) T, O(1) S
    public void reorderListSlow(ListNode head) {
        if (head == null)
            return;
        ListNode curr = head;
        ListNode succ = head.next;
        if (succ == null)
            return;
        while (succ != null && succ.next != null) {
            ListNode prevLast = null;
            ListNode last = head;
            while (last.next != null) {
                prevLast = last;
                last = last.next;
            }
            //move last node
            curr.next = last;
            if (prevLast != null)
                prevLast.next = null;
            last.next = succ;

            //go to start state
            curr = succ;
            succ = succ.next;
        }
    }

    //Reverse second half of the list in O(n)
    //then traverse in O(n) from both sides and change the LL

    //O(n) in time and O(1) in space
    public void reorderList(ListNode head) {
        if (head == null)
            return;
        //navigate to mid of list
        int size = 0;
        ListNode curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }

        if (size == 1 || size == 2)
            return;

        int mid = size / 2;
        ListNode prev = head;

        //traverse till mid of list
        while (mid != 0) {
            prev = prev.next;
            mid--;
        }

        curr = prev.next;
        ListNode succ = curr.next;

        prev.next = null; //mid element's next points to nothing

        //reverse right half of LL
        while (curr != null) {
            curr.next = prev;
            prev = curr;
            curr = succ;
            if (succ != null)
                succ = succ.next;
        }

        //prev is pointing to last element
        ListNode last = prev;
        ListNode first = head;
        ListNode firstNext = first.next;
        ListNode lastNext = last.next;

        //do interchanging
        while (lastNext != null) {
            first.next = last;
            last.next = firstNext;
            last = lastNext;
            lastNext = lastNext.next;
            first = firstNext;
            firstNext = firstNext.next;
        }
    }


}
