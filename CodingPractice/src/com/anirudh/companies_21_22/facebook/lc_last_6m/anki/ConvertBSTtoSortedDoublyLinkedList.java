package com.anirudh.companies_21_22.facebook.lc_last_6m.anki;


/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
 Medium

 1586

 135

 Add to List

 Share
 Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

 You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

 We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.



 Example 1:



 Input: root = [4,2,5,1,3]


 Output: [1,2,3,4,5]

 Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

 Example 2:

 Input: root = [2,1,3]
 Output: [1,2,3]
 Example 3:

 Input: root = []
 Output: []
 Explanation: Input is an empty tree. Output is also an empty Linked List.
 Example 4:

 Input: root = [1]
 Output: [1]


 Constraints:

 The number of nodes in the tree is in the range [0, 2000].
 -1000 <= Node.val <= 1000
 All the values of the tree are unique.
 Accepted
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * it says in-place, not constant space
 * use normal inorder traversal with a special way to handle joining first and last elements OR
 * use iterative inorder. Easy to get first element first and no special handling required
 * iterative inorder:
 * keep pushing left until null
 * then pop
 * then push node.right and then keep pushing left until null
 * so on and so forth
 */
public class ConvertBSTtoSortedDoublyLinkedList {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    Deque<Node> stack;

    private void populateStack(Node node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public Node treeToDoublyList(Node root) {
        if(root == null)
            return root;
        stack = new ArrayDeque<>();
        Node node = root;
        //initialize
        populateStack(node);
        Node prev = null;
        Node head = null;
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            if (prev == null) { //first
                head = curr;
            } else {
                prev.right = curr;
                curr.left = prev;
            }
            populateStack(curr.right);
            prev = curr;
        }
        head.left = prev; //close the loop
        prev.right = head;
        return head;
    }
}
