package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 3/6/17.
 */
/*
25. Reverse Nodes in k-Group Add to List

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a '
multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
 */
public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;
        ListNode prev = null;
        ListNode curr = head;
        ListNode succ = curr.next;
        ListNode currHead = curr;
        ListNode prevHead = null;
        int count;
        while(true){
            //checking if k elements left or not
            count = k;
            ListNode temp = curr;
            while(count != 0){
                if(temp == null)
                    break;
                temp = temp.next;
                count--;
            }
            if(count != 0)
                break;
            //list still has at least k elements
            //reversing k elems
            count = k;
            while(count != 0){
                curr.next = prev;
                prev = curr;
                curr = succ;
                if(curr == null)
                    break;
                succ = succ.next;
                count--;
            }
            //k nodes reversed
            //fixing pointers
            if(prevHead == null){
                prevHead = currHead;
                currHead.next = curr;
                head = prev;
                prev = currHead;
                currHead = curr;
            }
            else{
                prevHead.next = prev;
                currHead.next = curr;
                prevHead = currHead;
                prev = currHead;
                currHead = curr;
            }
            if(curr == null)
                break;
        }
        return head;
    }
}
