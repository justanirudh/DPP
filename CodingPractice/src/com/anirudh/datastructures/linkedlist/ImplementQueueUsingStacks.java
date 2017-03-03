package com.anirudh.datastructures.linkedlist;

import java.util.Stack;

/**
 * Created by paanir on 3/2/17.
 */
/*
232. Implement Queue using Stacks Add to List

Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */
public class ImplementQueueUsingStacks {

    //add - O(1)
    //remove = amortized O(1)
    
    public class MyQueue {

        Stack<Integer> stackPush;
        Stack<Integer> stackPop;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            stackPush.push(x); //normal case
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (stackPop.isEmpty()) { //lazy popping
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.pop(); //normal case
        }

        /**
         * Get the front element.
         */
        public int peek() {
            int lastElem = pop(); //pop and then push back
            stackPop.push(lastElem);
            return lastElem;
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return stackPush.isEmpty() && stackPop.isEmpty();
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
}
