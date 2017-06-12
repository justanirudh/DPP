package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 6/11/17.
 */
/*
61. Rotate List
DescriptionHintsSubmissionsSolutions
Total Accepted: 106495
Total Submissions: 438503
Difficulty: Medium
Contributor: LeetCode
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)
            return null;
        int len = 0;
        ListNode curr = head;
        while (curr.next != null) {
            ++len;
            curr = curr.next;
        }
        ListNode join = curr;
        ++len;
        int rot = k % len;
        if (rot == 0)
            return head;
        int distFromStart = len - rot;
        curr = head;
        for (int i = 0; i < distFromStart - 1; ++i)
            curr = curr.next;

        ListNode newHead = curr.next;
        curr.next = null;
        join.next = head;
        head = newHead;
        return head;
    }
}
