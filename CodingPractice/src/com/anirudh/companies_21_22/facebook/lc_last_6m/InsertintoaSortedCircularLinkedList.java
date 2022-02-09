package com.anirudh.companies_21_22.facebook.lc_last_6m;

import java.util.HashSet;
import java.util.Set;

/*
708. Insert into a Sorted Circular Linked List
Medium

830

560

Add to List

Share
Given a Circular Linked List node, which is sorted in ascending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.

If the list is empty (i.e., the given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the originally given node.



Example 1:



Input: head = [3,4,1], insertVal = 2
Output: [3,4,1,2]
Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.



Example 2:

Input: head = [], insertVal = 1
Output: [1]
Explanation: The list is empty (given head is null). We create a new single circular list and return the reference to that single node.
Example 3:

Input: head = [1], insertVal = 0
Output: [1,0]


Constraints:

The number of nodes in the list is in the range [0, 5 * 104].
-106 <= Node.val, insertVal <= 106
Accepted
98,702
Submissions
289,955
 */
/*
1. open the list: get min and max pointer
2. put element in the right place
3. close the list based on min and max pointer

Use visited set when opening list to capture usecases like [1] or [3,3,3]

Gotchas:
    1. ig head is null, create a single node, create self loop and return
    2. Use visited set when opening loop to capture usecases like [1] or [3,3,3]
 */
class Node {
    public int val;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
}

public class InsertintoaSortedCircularLinkedList {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node n = new Node(insertVal);
            n.next = n;
            return n;
        }

        //open the list
        Node curr = head;
        Node succ = curr.next;
        Set<Node> visited = new HashSet<>(); //if there are duplicate elements eg. [1], [3,3,3]
        while (succ.val >= curr.val && !visited.contains(curr)) { //will not be null as circular
            visited.add(curr);
            curr = succ;
            succ = curr.next;
        }
        Node min = succ;
        Node max = curr;
        max.next = null; //required to mark end of list

        //add value
        curr = min;
        Node prev = null;
        while (curr != null && insertVal > curr.val) {
            prev = curr;
            curr = curr.next;
        }
        Node n = new Node(insertVal);
        if (prev == null) { //if first
            n.next = min;
            min = n;
        } else {
            prev.next = n;
            n.next = curr;
            if (curr == null) // if last elem
                max = n;
        }

        //close the list
        max.next = min;

        //return
        return head;
    }
}
