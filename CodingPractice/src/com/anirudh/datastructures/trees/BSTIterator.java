package com.anirudh.datastructures.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

/*
173. Binary Search Tree Iterator
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
 */

public class BSTIterator { //also a BT iterator as not using BST property at all
    /*
    Space: O(h) stack is full worst case with the full height
    Time:
    O(1) amortized time for next(): most times O(1), once in a while O(height of subtree)
    O(1) time for hasNext()
     */
    Deque<TreeNode> leftTreeStack;
    TreeNode ans;

    public BSTIterator(TreeNode root) { //O(logn) space, O(logn) time
        leftTreeStack = new ArrayDeque<>();
        populateStack(root);
    }

    private void populateStack(TreeNode node) {
        while (node != null) {
            leftTreeStack.push(node);
            node = node.left;
        }
    }

    public boolean hasNext() {
        return !leftTreeStack.isEmpty();
    }

    public int next() { //InOrder iterative
        ans = leftTreeStack.pop();
        populateStack(ans.right);
        return ans.val;
    }

//-------------------------------------------------------------------------------------------------------------------------
    public class BSTIteratorBad {
        //Option2: create array with sorted elem. O(n) space (for both), O(1) time for next ,O(1) time for hasNext, O(n) initialization time

        private ArrayList<Integer> populateInOrderTraversal(TreeNode root, ArrayList<Integer> nums) {
            if (root != null) {
                nums = populateInOrderTraversal(root.left, nums);
                nums.add(root.val);
                nums = populateInOrderTraversal(root.right, nums);
            }
            return nums;
        }

        ArrayList<Integer> nums;
        int index;

        public BSTIteratorBad(TreeNode root) {
            nums = populateInOrderTraversal(root, new ArrayList<>());
            index = 0;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            if (index >= nums.size())
                return false;
            else {
                return true;
            }

        }

        /**
         * @return the next smallest number
         */
        public int next() {
            if (index >= nums.size())
                throw new RuntimeException("Index out of bound");
            else {
                return nums.get(index++);
            }

        }
    }


}

