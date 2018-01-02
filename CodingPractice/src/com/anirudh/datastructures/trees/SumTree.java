package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 12/31/17.
 */
/*
Write a function that returns true if the given Binary Tree is SumTree else false. A SumTree is a Binary Tree where
value of every node x is equal to sum of nodes present in its left subtree and right subtree of x. An empty tree is
SumTree and sum of an empty tree can be considered as 0. A leaf node is also considered as SumTree.

Following is an example of SumTree.

          26
        /    \
      10      3
    /   \   /   \
   4     6  1    2


Input:

The task is to complete the method which takes one argument, root of Binary Tree. There are multiple test cases.
For each test case, this method will be called individually.

Output:
The function should return true if passed tree is Sum Tree, else false

Constraints:
1 <=T<= 30
1 <=Number of nodes<= 1000
1 <=Data of a node<= 1000

Example:
Input:
2
2
3 1 L 3 2 R
4
10 20 L 10 30 R 20 40 L 20 60 R

Output:
1
0
*/
//https://practice.geeksforgeeks.org/problems/sum-tree/1
public class SumTree {

    //return sum and is valid together
    class Result {
        boolean isSumTree;
        int sum;

        Result(boolean isSumTree, int sum) {
            this.isSumTree = isSumTree;
            this.sum = sum;
        }
    }

    Result postOrder(Node node) {
        if (node == null)
            return new Result(true, 0);
        if (node.left == null && node.right == null)
            return new Result(true, node.data);
        Result left = postOrder(node.left);
        if (!left.isSumTree)
            return left;
        Result right = postOrder(node.right);
        if (!right.isSumTree)
            return right;
        if (node.data == left.sum + right.sum)
            return new Result(true, node.data + left.sum + right.sum);
        else
            return new Result(false, -1);
    }

    boolean isSumTree(Node node) {
        if (node == null)
            return true;
        if (node.left == null && node.right == null)
            return true;
        Result res = postOrder(node);
        return res.isSumTree;
    }


}



