package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 6/21/17.
 */
/*
2. Add Two Numbers

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order
and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode num1 = l1;
        ListNode num2 = l2;
        ListNode res = null;
        ListNode curr = null;
        int carry = 0;
        while(num1 != null && num2 != null){
            int sum = num1.val + num2.val + carry;
            if(sum >= 10){
                carry = 1;
                sum -=10;
            }
            else
                carry = 0;
            if(res == null){
                res = new ListNode(sum);
                curr = res;
            }
            else{
                ListNode ln = new ListNode(sum);
                curr.next = ln;
                curr = curr.next;
            }
            num1 = num1.next;
            num2 = num2.next;
        }
        if(num1 == null && num2 == null){
            if(carry == 1){
                ListNode ln = new ListNode(1);
                curr.next = ln;
            }
            return res;
        }
        else if(num1 == null){ //num2 != null
            while(num2 != null){
                int sum = carry + num2.val;
                if(sum >= 10){
                    carry = 1;
                    sum -=10;
                }
                else
                    carry = 0;
                ListNode ln = new ListNode(sum);
                curr.next = ln;
                curr = curr.next;
                num2 = num2.next;
            }
            if(carry == 1){
                ListNode ln = new ListNode(1);
                curr.next = ln;
                curr = curr.next;
            }
            return res;
        }
        else{ //num2 == null and num1 != null
            while(num1 != null){
                int sum = carry + num1.val;
                if(sum >= 10){
                    carry = 1;
                    sum -=10;
                }
                else
                    carry = 0;
                ListNode ln = new ListNode(sum);
                curr.next = ln;
                curr = curr.next;
                num1 = num1.next;
            }
            if(carry == 1){
                ListNode ln = new ListNode(1);
                curr.next = ln;
                curr = curr.next;
            }
            return res;
        }
    }
}
