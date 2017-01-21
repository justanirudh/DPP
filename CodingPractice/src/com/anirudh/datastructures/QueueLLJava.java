package com.anirudh.datastructures;

/**
 * Created by paanir on 12/17/16.
 */
public class QueueLLJava<T> {

    static class BadImplementationException extends RuntimeException
    {
        public BadImplementationException(String message)
        {
            super(message);
        }
    }

    static class EmptyQueueException extends RuntimeException
    {
        public EmptyQueueException(String message)
        {
            super(message);
        }
    }

    private class Node {
        T elem;
        Node next;
        public Node (T elem){
            this.elem = elem;
        }
    }

    private Node head = null;
    private Node tail = null;

    //enqueue, dequeue, getfront, getback, print, isEmpty

    public void enqueue(T elem) /*throws BadImplementationException*/{
        Node newNode = new Node(elem);
        if(head == null && tail==null){
            head = newNode;
            tail = head;
        }
        else if(head != null && tail!=null) {
            tail.next = newNode;
            tail = newNode;
        }
        else
            throw new BadImplementationException("something wrong");
    }

    public T dequeue()/* throws EmptyQueueException */{
        if(head == null && tail==null){
            throw new EmptyQueueException("Queue empty. Nothing to dequeue");
        }
        else if (head == tail){
            T elem = head.elem;
            head = null;
            tail = null;
            return elem;
        }
        else {
            T elem = head.elem;
            head = head.next;
            return elem;
        }
    }

    public T getfront()  throws EmptyQueueException{
        if(head == null && tail==null){
            throw new EmptyQueueException("Queue empty. Nothing to dequeue");
        }
        else
            return head.elem;
    }

    public T getback()  throws EmptyQueueException{
        if(head == null && tail==null){
            throw new EmptyQueueException("Queue empty. Nothing to dequeue");
        }
        else
            return tail.elem;
    }

    public void printPlease(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.elem + "->");
            temp = temp.next;
        }
        System.out.print("null\n");
    }

    public boolean isEmpty(){
        return (head == null && tail == null);
    }

    public static void main(String[] args) /*throws BadImplementationException, EmptyQueueException*/{
        QueueLLJava q = new QueueLLJava<Integer>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
//        q.enqueue(4);
//        q.enqueue(5);
//        q.dequeue();
        q.dequeue();
        q.printPlease();
        System.out.print(q.isEmpty());
    }
}
