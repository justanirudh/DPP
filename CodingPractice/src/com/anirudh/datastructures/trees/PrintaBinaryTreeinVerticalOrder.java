package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by paanir on 1/1/18.
 */
/*
Given a binary tree, your task is to complete the function verticalOrder which takes one argument the root of the
binary tree and prints the node of the binary tree in vertical order .

          1
       /     \
     2       3
   /        /
4       5

The nodes of the above tree printed in vertical order will be
4
2
1 5
3
Your output should be 4 $2 $1 5 $3 $

Note: Each vertical line will be separated by a "$" without quotes.

Input:

The task is to complete the method which takes one argument, root of Binary Tree. There are multiple test cases.
For each test case, this method will be called individually.

Output:
The function should print nodes in vertical order where  each vertical line is separated by a "$" without quotes.

Constraints:
1 <=T<= 30
1 <= Number of nodes<= 20


Example:
Input:
2
2
1 2 R 1 3 L
4
10 20 L 10 30 R 20 40 L 20 60 R

Output:
3 $1 $2 $
40 $20 $10 60 $30 $


There are two test cases.  First case represents a tree with 3 nodes and 2 edges where root is 1, left child of 1 is 3
and right child of 1 is 2.   Second test case represents a tree with 4 edges and 5 nodes.


Example-2
           1
        /    \
       2      3
      / \    / \
     4   5  6   7
             \   \
              8   9


The output of print this tree vertically will be:
4
2
1 5 6
3 8
7
9
 */

/*
Logic
I attach a number with each node. I subtract 1 if I go left and add 1 if I go right. Start with root with num as 0.
use a treemap (sorted wrt key) with {this num -> all nodes having that num}. Then just traverse the map and print
 */
public class PrintaBinaryTreeinVerticalOrder {

    void print(Map<Integer, List<Integer>> map) {
        for (List<Integer> vals : map.values()) {
            for (int data : vals) {
                System.out.print(data + " ");
            }
            System.out.print("$");
        }
    }

    void traverse(Node node, Map<Integer, List<Integer>> map, int num) {
        if (node == null)
            return;
        if (map.containsKey(num)) {
            map.get(num).add(node.data);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(node.data);
            map.put(num, list);
        }
        traverse(node.left, map, num - 1);
        traverse(node.right, map, num + 1);
    }

    void verticalOrder(Node node) {
        if (node == null)
            return;
        Map<Integer, List<Integer>> map = new TreeMap<>();
        int num = 0;

        List<Integer> list = new ArrayList<>();
        list.add(node.data);
        map.put(num, list);

        traverse(node.left, map, num - 1);
        traverse(node.right, map, num + 1);

        print(map);
    }
}
