package com.anirudh.datastructures;

import java.util.ArrayList;
import java.util.Stack;

//O(n) space, O(1) time,O(1) time
public class BSTIterator {

    public class TNode {
        int val;
        TNode left;
        TNode right;

        TNode(int x) {
            val = x;
        }
    }

    private ArrayList<Integer> populateInOrderTraversal(TNode root, ArrayList<Integer> nums) {
        if (root != null) {
            nums = populateInOrderTraversal(root.left, nums);
            nums.add(root.val);
            nums = populateInOrderTraversal(root.right, nums);
        }
        return nums;
    }

    ArrayList<Integer> nums;
    int index;

    public BSTIterator(TNode root) {
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
            return -999999;
        else {
            return nums.get(index++);
        }

    }

//-------------------O(logn) space, O(1) time, O(logn) time-------------------------

    public class BSTIterator2 {

        public class TNode {
            int val;
            TNode left;
            TNode right;

            TNode(int x) {
                val = x;
            }
        }

        Stack<TNode> stack;

        public BSTIterator2(TNode root) {
            stack = new Stack<TNode>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TNode node = stack.pop();
            int result = node.val;
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            return result;
        }
    }

}

