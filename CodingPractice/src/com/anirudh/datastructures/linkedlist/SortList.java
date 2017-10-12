package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 10/11/17.
 */
/*
148. Sort List
Sort a linked list in O(n log n) time using constant space complexity.
 */
public class SortList {
    public ListNode merge(ListNode l, ListNode r) { //both l and r are non-null
        ListNode head = null, curr = null;

        //set head and curr
        if (l.val <= r.val) {
            head = l;
            curr = l;
            l = l.next;
        } else {
            head = r;
            curr = r;
            r = r.next;
        }
        //remake list
        while (l != null || r != null) {
            if (l == null) {
                curr.next = r;
                curr = r;
                r = r.next;
            } else if (r == null) {
                curr.next = l;
                curr = l;
                l = l.next;
            } else {//both non-null
                if (l.val <= r.val) {
                    curr.next = l;
                    curr = l;
                    l = l.next;
                } else {
                    curr.next = r;
                    curr = r;
                    r = r.next;
                }
            }

        }
        return head;
    }

    public ListNode sortList(ListNode head) {
        //base cases
        if (head == null || head.next == null)
            return head;
        if (head.next.next == null) { //2 nodes, sort and return
            ListNode first = head;
            ListNode second = head.next;
            if (first.val > second.val) {
                second.next = first;
                first.next = null;
                head = second;
            }
            return head;
        }

        ListNode slow = head, fast = head, prev = null;
        //dividing list into 2 parts
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; //breaking the linked lists
        //merge sort
        ListNode left = sortList(head);
        ListNode right = sortList(slow);
        return merge(left, right);
    }
}
