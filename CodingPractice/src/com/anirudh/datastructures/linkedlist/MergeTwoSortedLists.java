package com.anirudh.datastructures.linkedlist;

/**
 * Created by paanir on 2/17/17.
 */
public class MergeTwoSortedLists {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return (l1 == null) ? l2 : l1;
        //both non-null
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        ListNode newList, curr;
        newList = curr = null;
        while (curr1 != null && curr2 != null) {
            int val1 = curr1.val;
            int val2 = curr2.val;
            if (val1 < val2) {
                ListNode ln = new ListNode(val1);
                if (newList == null) {
                    newList = ln;
                    curr = newList;
                } else {
                    curr.next = ln;
                    curr = curr.next;
                }
                curr1 = curr1.next;
            } else if (val2 < val1) {
                ListNode ln = new ListNode(val2);
                if (newList == null) {
                    newList = ln;
                    curr = newList;
                } else {
                    curr.next = ln;
                    curr = curr.next;
                }
                curr2 = curr2.next;
            } else { //both equal
                ListNode ln1 = new ListNode(val1);
                ListNode ln2 = new ListNode(val2);
                if (newList == null) {
                    newList = ln1;
                    newList.next = ln2;
                    curr = ln2;
                } else {
                    curr.next = ln1;
                    curr = curr.next;
                    curr.next = ln2;
                    curr = curr.next;
                }
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
        }
        if (curr1 == null && curr2 == null) {
            return newList;
        } else if (curr1 == null) { //copy rest of curr2
            while (curr2 != null) {
                int val2 = curr2.val;
                ListNode ln = new ListNode(val2);
                curr.next = ln;
                curr = curr.next;
                curr2 = curr2.next;
            }
            return newList;
        } else { //copy rest of curr1
            while (curr1 != null) {
                int val1 = curr1.val;
                ListNode ln = new ListNode(val1);
                curr.next = ln;
                curr = curr.next;
                curr1 = curr1.next;
            }
            return newList;
        }
    }
}
