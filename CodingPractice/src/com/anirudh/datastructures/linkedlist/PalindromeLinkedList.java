package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 3/3/17.
 */
/*
234. Palindrome Linked List Add to List

Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {
    //reversing second half of the list and comparing front and back elements
    //O(n) time, O(1) space
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            length++;
        }
        ListNode prev = head;
        int half = length / 2 - 1;
        while (half != 0) {
            prev = prev.next;
            half--;
        }
        curr = prev.next;
        ListNode succ = curr.next;
        //reversing second half of list
        while (true) {
            curr.next = prev;
            prev = curr;
            curr = succ;
            if (curr == null)
                break;
            succ = succ.next;
        }
        //checking if palindrome or not
        ListNode back = prev;
        ListNode front = head;
        int count = 0;
        while (count != length / 2) {
            if (back.val != front.val)
                return false;
            back = back.next;
            front = front.next;
            count++;
        }
        return true;
    }
}
