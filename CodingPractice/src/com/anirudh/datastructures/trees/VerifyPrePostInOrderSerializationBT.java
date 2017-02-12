package com.anirudh.datastructures.trees;

import java.util.ArrayList;

import static com.anirudh.datastructures.trees.VerifyPrePostInOrderSerializationBT.Order.INORDER;
import static com.anirudh.datastructures.trees.VerifyPrePostInOrderSerializationBT.Order.PREORDER;

/**
 * Created by paanir on 2/1/17.
 */
/*
331. Verify Preorder Serialization of a Binary Tree   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 29395
Total Submissions: 83211
Difficulty: Medium
Contributors: Admin
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false



 */

//solution: http://www.programcreek.com/2015/01/leetcode-verify-preorder-serialization-of-a-binary-tree-java/
public class VerifyPrePostInOrderSerializationBT {

    public enum Order {PREORDER, INORDER, POSTORDER}


    public static boolean isValidSerialization(String str, Order o) {

        String[] nodes = str.split(",");
        ArrayList<String> elems = new ArrayList<>();

        for (int i = 0; i < nodes.length; ++i) {
            elems.add(nodes[i]);

            if (o == PREORDER) { //convert 3## to # till the entire string is converted to #
                while (elems.size() >= 3
                        && elems.get(elems.size() - 1).equals("#")
                        && elems.get(elems.size() - 2).equals("#")
                        && !elems.get(elems.size() - 3).equals("#")) {
                    elems.remove(elems.size() - 1);
                    elems.remove(elems.size() - 1); //all -1 because size also decreasing
                    elems.remove(elems.size() - 1);
                    elems.add("#");
                }
            } else if (o == INORDER) { //convert #3# to # till the entire string is converted to #
                while (elems.size() >= 3
                        && elems.get(elems.size() - 1).equals("#")
                        && !elems.get(elems.size() - 2).equals("#")
                        && elems.get(elems.size() - 3).equals("#")) {
                    elems.remove(elems.size() - 1);
                    elems.remove(elems.size() - 1);
                    elems.remove(elems.size() - 1);
                    elems.add("#");
                }
            } else {//postorder, convert ##3 to # till the entire string is converted to #
                while (elems.size() >= 3
                        && !elems.get(elems.size() - 1).equals("#")
                        && elems.get(elems.size() - 2).equals("#")
                        && elems.get(elems.size() - 3).equals("#")) {
                    elems.remove(elems.size() - 1);
                    elems.remove(elems.size() - 1);
                    elems.remove(elems.size() - 1);
                    elems.add("#");
                }
            }
        }

        if(elems.size() == 1 && elems.get(0).equals("#"))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        String str = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println(isValidSerialization(str, PREORDER));
    }
}
