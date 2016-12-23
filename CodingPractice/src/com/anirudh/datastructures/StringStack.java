package com.anirudh.datastructures;

/**
 * Created by paanir on 12/22/16.
 */
public class StringStack {

    private class Node {
        String elem;
        Node next;
        public Node(String elem){
            this.elem = elem;
        }
    }

    private Node head;

    public StringStack() {
        head = null;
    }

    public void push(String x) {
        if(head == null)
            head = new Node(x);
        else{
            Node newHead = new Node(x);
            newHead.next = head;
            head = newHead;
        }
    }

    public String pop(){
        if(head == null){
            return null;
        }
        else{
            String s = head.elem;
            head = head.next;
            return s;
        }
    }

    public String top() {
        return head.elem;
    }

    public boolean isEmpty(){
        return head == null;
    }
}
