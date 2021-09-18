package com.anirudh.interview_prep_2021.spotify;

import com.anirudh.datastructures.linkedlist.ListNode;

/*
83. Remove Duplicates from Sorted List
Easy

3244

157

Add to List

Share
Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

Example 1:


Input: head = [1,1,2]
Output: [1,2]
Example 2:


Input: head = [1,1,2,3,3]
Output: [1,2,3]
 */
public class RemoveDuplicatesSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            while (fast != null && fast.val == slow.val)
                fast = fast.next;
            slow.next = fast;
            slow = fast;
        }
        return head;
    }
}
