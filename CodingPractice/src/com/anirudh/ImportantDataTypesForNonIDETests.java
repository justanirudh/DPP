package com.anirudh;

import java.util.*;

/**
 * Created by paanir on 1/19/17.
 */
public class ImportantDataTypesForNonIDETests {

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(s);
        s.intern();

        Integer.toString(Integer.parseInt(s));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            sb.append(Integer.toString(i));
        }
        System.out.println(sb.toString());

        StringJoiner sj = new StringJoiner(",");
        for (int i = 0; i < 10; ++i) {
            sj.add(Integer.toString(i));
        }
        System.out.println(sj.toString());


        ArrayList<Integer> arrl = new ArrayList<>();
        System.out.println(arrl);
        Iterator arrlIter = arrl.iterator();

        int k = 5;
        int[] arr2 = new int[k];

        int[] arr = new int[]{2,3,4};
        System.out.println(arr);


        //3 kinds of hashmaps/hashsets
        //1
        Map<Integer, String> hm = new HashMap<>();
        Set<Integer> hs = new HashSet<>();
        System.out.println(hm);

        //2 Red-Black tree. log(n) time cost for the containsKey, get, put and remove operations
        Map<Integer, String> tm = new TreeMap<>(); //sorted keys:
        TreeSet<Integer> ts = new TreeSet<>();//sorted keys

        //3 https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html
        //linked list with hasmap. can be used to create LRU caches
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

        //3 kinds of queue
        //1
        Queue<Node> queue = new LinkedList<Node>();
        System.out.println(queue);

        //Resizable-array implementation of the Deque interface.
        //This class is likely to be faster than Stack when used as a stack, and faster than LinkedList when used as a queue.
        Queue<Character> betterQueue = new ArrayDeque<>();
        betterQueue.offer('4');
        betterQueue.poll();

        Deque<Integer> betterStack = new ArrayDeque<>(); //Stack<Integer> s = new ArrayDeque<>(); doesnt work
        betterStack.push(5);
        betterStack.pop();

        //2
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
        //comparator: Returns a negative integer, zero, or a positive integer as the first argument is less than,
        // equal to, or greater than the second.
        PriorityQueue<Pair> pQueue = new PriorityQueue<>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return a.count-b.count;
            }
        });
        priorityQueue.poll();
        priorityQueue.offer(3);

        //3
        //The preferred way to represent stacks/queues in java is the deque interface.
        Deque<Integer> deq = new LinkedList<>(); //doubly linked list, can be used for both stack and queue

    }
}

/*
kinds of maps
TreeMap - RB tree
EnumMap - array
hashMap - Hashtable based (array)
LinkedHashMap - Linked List + HashMap
 */

/*
Arraylist: resizable array/vector
LinkedList: doubly linked list
ArrayDeque: backed by array, faster than Stack for stack ops and faster than LinkedList for queue ops.
 */