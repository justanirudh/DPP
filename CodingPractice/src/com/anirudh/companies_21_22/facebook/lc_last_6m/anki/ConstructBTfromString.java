package com.anirudh.companies_21_22.facebook.lc_last_6m.anki;

import com.anirudh.datastructures.trees.TreeNode;

import java.util.Arrays;
import java.util.List;

/*
536. Construct Binary Tree from String
Medium

850

134

Add to List

Share
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.



Example 1:


Input: s = "4(2(3)(1))(6(5))"
Output: [4,2,6,3,1,5]
Example 2:

Input: s = "4(2(3)(1))(6(5)(7))"
Output: [4,2,6,3,1,5,7]
Example 3:

Input: s = "-4(2(3)(1))(6(5)(7))"
Output: [-4,2,6,3,1,5,7]


Constraints:

0 <= s.length <= 3 * 104
s consists of digits, '(', ')', and '-' only.
Accepted
69,938
Submissions
126,549
 */

/*
    Use preorder search
        do recursive preorder and increment index naturally
    construct numbers

    Look at impl
 */
public class ConstructBTfromString {

    class TreeNodeAndIndex {

        TreeNode tn;
        int index;
        TreeNodeAndIndex(TreeNode tn, int index) {
            this.tn = tn;
            this.index = index;
        }

    }

    List<Integer> getValueAndIndex(String s, int idx) {
        boolean isNegative = false;

        if(s.charAt(idx) == '-') {
            isNegative = true;
            idx++;
        }

        int val = 0;
        while(idx < s.length() && Character.isDigit(s.charAt(idx))) {
            val = val * 10 + (s.charAt(idx) - '0');
            idx++;
        }
        if (isNegative)
            val = -1 * val;
        return Arrays.asList(val, idx);
    }

    TreeNodeAndIndex preOrder(String s, int idx) {
        if(idx == s.length())
            return new TreeNodeAndIndex(null, idx);

        List<Integer> valueAndIdx = getValueAndIndex(s, idx);
        TreeNode node = new TreeNode(valueAndIdx.get(0));
        idx = valueAndIdx.get(1);

        if(idx < s.length() && s.charAt(idx) == '(') {
            idx++;
            TreeNodeAndIndex tni = preOrder(s, idx);
            node.left = tni.tn;
            idx = tni.index;
        }

        if(idx < s.length() && node.left != null && s.charAt(idx) == '(') {
            idx++;
            TreeNodeAndIndex tni = preOrder(s, idx);
            node.right = tni.tn;
            idx = tni.index;
        }

        if(idx < s.length() && s.charAt(idx) == ')') {
            idx++;
        }

        return new TreeNodeAndIndex(node, idx);
    }

    public TreeNode str2tree(String s) {
        return preOrder(s, 0).tn;
    }
}
