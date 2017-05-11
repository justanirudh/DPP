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

        HashMap<Integer, String> hm = new HashMap<>();
        System.out.println(hm);

        TreeMap<Integer, String> tm = new TreeMap<>(); //sorted keys

        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>(); //in the same order as inserted

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

        //https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html
        Queue<Node> queue = new LinkedList<Node>();
        System.out.println(queue);
    }
}
