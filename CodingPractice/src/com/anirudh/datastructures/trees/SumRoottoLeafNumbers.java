package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 2/7/17.
 */

/*

129. Sum Root to Leaf Numbers
Description  Submission  Solutions  Add to List
Total Accepted: 100635
Total Submissions: 284644
Difficulty: Medium
Contributors: Admin
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.

 */
public class SumRoottoLeafNumbers {

    private int sumArray(String[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; ++i) {
            sum += Integer.parseInt(arr[i]);
        }
        return sum;
    }

    public static String[] getNumbers(String yet, TreeNode node) {
        if (node == null)
            return new String[0];

        String curr = yet + String.valueOf(node.val);

        String[] lefts = getNumbers(curr, node.left);

        String[] rights = getNumbers(curr, node.right);

        if (lefts.length == 0 && rights.length == 0) {
            String[] single = new String[1];
            single[0] = curr;
            return single;
        } else if (lefts.length != 0 && rights.length == 0)
            return lefts;
        else if (lefts.length == 0 && rights.length != 0)
            return rights;
        else { //both non-zero
            String[] all = new String[lefts.length + rights.length];
            System.arraycopy(lefts, 0, all, 0, lefts.length);
            System.arraycopy(rights, 0, all, lefts.length, rights.length);
            return all;
        }
    }

    public int sumNumbers(TreeNode root) {

        if (root == null)
            return 0;

        //preorder traversal
        String curr = String.valueOf(root.val);

        String[] lefts = getNumbers(curr, root.left);

        String[] rights = getNumbers(curr, root.right);

        if (lefts.length == 0 && rights.length == 0)
            return root.val;
        else if (lefts.length != 0 && rights.length == 0)
            return sumArray(lefts);
        else if (lefts.length == 0 && rights.length != 0)
            return sumArray(rights);
        else { //both non-zero
            String[] all = new String[lefts.length + rights.length];
            System.arraycopy(lefts, 0, all, 0, lefts.length);
            System.arraycopy(rights, 0, all, lefts.length, rights.length);
            return sumArray(all);
        }
    }
}
