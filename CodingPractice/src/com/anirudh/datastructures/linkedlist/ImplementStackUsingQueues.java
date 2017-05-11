package com.anirudh.datastructures.linkedlist;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by paanir on 3/2/17.
 */
/*
225. Implement Stack using Queues Add to List
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Notes:
You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack)
 */
public class ImplementStackUsingQueues {

    //push() - O(n)
    //pop() - O(1)

    public class MyStack {

        Queue<Integer> q1; //is always empty *just* before new element is inserted. Else, has ALL the elements
        Queue<Integer> q2;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) { //O(n)
            //interchange qs such that new element gets inserted in an empty queue
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            //insert in q1
            q1.add(x);
            //transfer rest elements from q2 to q1
            while (!q2.isEmpty()) {
                q1.add(q2.remove());
            }
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return q1.remove();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return q1.element();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return q1.isEmpty();
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
}
