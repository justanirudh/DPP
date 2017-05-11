package com.anirudh.general_algos.basics;

/**
 * Created by anirudh on 10/12/16.
 */
public class MinStack {

    class StackEmptyException extends Exception
    {
        public StackEmptyException(String message)
        {
            super(message);
        }
    }

    private class Node {
        int elem;
        int minYet;
        Node next;
        public Node(int elem, int minYet){
            this.elem = elem;
            this.minYet = minYet;
        }
    }

    private Node head;

    public MinStack() {
        head = null;
    }

    public void push(int x) {
        int newMin;
        if(head == null)
            head = new Node(x, x);
        else{
            if(x < topMin())
                newMin = x;
            else
                newMin = topMin();
            Node newHead = new Node(x, newMin);
            newHead.next = head;
            head = newHead;
        }
    }
    public void pop() throws StackEmptyException{
        if(head == null)
            throw new StackEmptyException("Stack is empty. Cannot pop");
        else
            head = head.next;
    }

    private int topMin() {
        return head.minYet;
    }

    public int top() {
        return head.elem;
    }

    public int getMin() {
        return head.minYet;
    }

    public static void main(String[] args)throws  StackEmptyException{
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());   //--> Returns -3.
        minStack.pop();
        System.out.println(minStack.top());      //--> Returns 0.
        System.out.println(minStack.getMin());   //--> Returns -2.

    }
}
