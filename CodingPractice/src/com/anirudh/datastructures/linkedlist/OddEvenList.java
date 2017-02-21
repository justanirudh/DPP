package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 2/19/17.
 */
/*
328. Odd Even Linked List
Description  Submission  Solutions  Add to List
Total Accepted: 57583
Total Submissions: 136536
Difficulty: Medium
Contributors: Admin
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking
about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,

return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...

 */
public class OddEvenList {

    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null || head.next.next == null)
            return head;
        //need 4 pointers: one pointing to last odd num (lO), one to first even num (fE)and 2 on either sides (lS and rS)
        // of the odd number being transported to the left side
        //Eg: Midway just before transporting 5 to even side
        // 1--->3--->2--->4--->5---->null
        //      lO   fE   lS          rS
        ListNode lastOdd = head;
        ListNode firstEven = lastOdd.next;
        ListNode leftSentinel = firstEven;
        ListNode rightSentinel = firstEven.next.next; //can be null
        while (true) {
            //make the change
            ListNode change = leftSentinel.next;
            leftSentinel.next = rightSentinel;
            lastOdd.next = change;
            change.next = firstEven;
            //restore pointers;
            lastOdd = lastOdd.next;
            leftSentinel = rightSentinel;
            if (rightSentinel == null)
                break;
            rightSentinel = rightSentinel.next;
            if (rightSentinel == null)
                break;
            rightSentinel = rightSentinel.next; //can be null
        }
        return head;
    }
}
