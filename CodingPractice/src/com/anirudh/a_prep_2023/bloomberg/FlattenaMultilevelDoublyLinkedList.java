package com.anirudh.a_prep_2023.bloomberg;

/*
430. Flatten a Multilevel Doubly Linked List
Medium
4.5K
296
company
Bloomberg
company
Amazon
company
LinkedIn
You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer, and an additional child pointer. This child pointer may or may not point to a separate doubly linked list, also containing these special nodes. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure as shown in the example below.

Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level, doubly linked list. Let curr be a node with a child list. The nodes in the child list should appear after curr and before curr.next in the flattened list.

Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.



Example 1:


Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]
Explanation: The multilevel linked list in the input is shown.
After flattening the multilevel linked list it becomes:

Example 2:


Input: head = [1,2,null,3]
Output: [1,3,2]
Explanation: The multilevel linked list in the input is shown.
After flattening the multilevel linked list it becomes:

Example 3:

Input: head = []
Output: []
Explanation: There could be empty list in the input.


Constraints:

The number of Nodes will not exceed 1000.
1 <= Node.val <= 105
 */
public class FlattenaMultilevelDoublyLinkedList {
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    public Node flatten(Node head) {
        if (head == null)
            return head;
        Node curr = head;
        while (curr != null) {
            if (curr.child == null) { //no child, move on
                curr = curr.next;
            } else { //has child, add the whole child level to the list and then traverse like normal
                Node firstChild = curr.child;
                curr.child = null;
                Node currChild = firstChild;
                while (currChild.next != null) { //go to the last node of this level
                    currChild = currChild.next;
                }
                currChild.next = curr.next; //now point last node to curr's next, and curr to firstChild
                if (curr.next != null) //currs next previous to last node of the level
                    curr.next.prev = currChild;
                curr.next = firstChild; //same stuff for curr and first child
                firstChild.prev = curr;
                curr = curr.next; //move on like normal
            }
        }
        return head;
    }
}
