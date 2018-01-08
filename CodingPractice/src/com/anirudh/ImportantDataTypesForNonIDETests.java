package com.anirudh;

import java.util.*;

/**
 * Created by paanir on 1/19/17.
 */
public class ImportantDataTypesForNonIDETests {

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(s);

        Integer.toString(Integer.parseInt(s));

        StringBuilder sb = new StringBuilder();
        System.out.println(sb);

        ArrayList<Integer> arrl = new ArrayList<>();
        System.out.println(arrl);

        int k = 5;
        int[] arr2 = new int[k];

        int[] arr = new int[]{2,3,4};
        System.out.println(arr);


        //3 kinds of hashmaps
        Map<Integer, String> hm = new HashMap<>();
        System.out.println(hm);

        Map<Integer, String> tm = new TreeMap<>(); //sorted keys
        TreeSet<Integer> ts = new TreeSet<>();//sorted keys

        Map<Integer, String> lhm = new LinkedHashMap<>(); //in the same order as inserted
        LinkedHashSet<Integer> lhs = new LinkedHashSet<>(); //in the same order as inserted

        class Node {
            int val;
            Node next;
            Node(int val) {
                this.val = val;
                this.next = null;
            }
        }

        Stack<Node> stack = new Stack<Node>();
        System.out.println(stack);

        //2 kinds of queue
        Queue<Node> queue = new LinkedList<Node>();
        System.out.println(queue);

        class Pair{
            int num;
            int count;
            public Pair(int num, int count){
                this.num=num;
                this.count=count;
            }
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // create a min heap
        PriorityQueue<Pair> pQueue = new PriorityQueue<>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return a.count-b.count;
            }
        });
        priorityQueue.poll();
        priorityQueue.offer(3);

    }
}

/*
kinds of maps
TreeMap - RB tree
EnumMap - array
hashMap - Hashtable based (array)
LinkedHashMap - Linked List + HashMap
 */