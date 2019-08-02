package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 1/5/18.
 */
/*
https://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/

Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root. For example,
boundary traversal of the following tree is “20 8 4 10 14 25 22”



Recommended: Please solve it on “PRACTICE” first, before moving on to the solution.
We break the problem in 3 parts:
1. Print the left boundary in top-down manner.
2. Print all leaf nodes from left to right, which can again be sub-divided into two sub-parts:
…..2.1 Print all leaf nodes of left sub-tree from left to right.
…..2.2 Print all leaf nodes of right subtree from left to right.
3. Print the right boundary in bottom-up manner.
 */
public class BoundaryTraversalofBT {

    void getLeftBoundary(Node node, List<String> boundary) {
        if (node == null)
            return;
        if (node.left != null) { //first check left
            boundary.add(Integer.toString(node.data));
            getLeftBoundary(node.left, boundary);
        } //left is null, so go to right
        else if (node.right != null) {
            boundary.add(Integer.toString(node.data));
            getLeftBoundary(node.right, boundary);
        }
        //both right and left node null => leaf. leave this for getLeaves function
    }

    void getRightBoundary(Node node, List<String> boundary) {
        if (node == null)
            return;
        if (node.right != null) { //first check right
            //first recursive call and then add to list( as adding in reverse order)
            getRightBoundary(node.right, boundary);
            boundary.add(Integer.toString(node.data));
        } else if (node.left != null) {
            getRightBoundary(node.left, boundary);
            boundary.add(Integer.toString(node.data));
        }
        //dont add leaf
    }

    void getLeaves(Node node, List<String> boundary) {
        //do inorder (even preorder or postorder would do as all add L to R)
        if (node == null)
            return;
        getLeaves(node.left, boundary);
        if (node.left == null && node.right == null)
            boundary.add(Integer.toString(node.data));
        getLeaves(node.right, boundary);
    }

    void printBoundary(Node node) {
        if (node == null)
            return;
        List<String> boundary = new ArrayList<>();
        boundary.add(Integer.toString(node.data));//add root

        getLeftBoundary(node.left, boundary);
        //Not doing getLeaves(node, boundary) because it will add the root again, which has already been added above
        getLeaves(node.left, boundary); //from left to right
        getLeaves(node.right, boundary); //from left to right
        getRightBoundary(node.right, boundary);

        System.out.print(String.join(" ", boundary));
    }
}
