package com.anirudh.datastructures.trees;

import java.util.ArrayList;

/**
 * Created by paanir on 2/1/17.
 */
/*

116. Populating Next Right Pointers in Each Node   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 117423
Total Submissions: 318267
Difficulty: Medium
Contributors: Admin
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL

 */
public class PopulatingNextPointersBT {

    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    public class TNH {
        TreeLinkNode tn;
        int h;

        public TNH(TreeLinkNode tn, int height) {
            this.tn = tn;
            this.h = height;
        }
    }

    ArrayList<TNH> queue = new ArrayList<>();

    public void connect(TreeLinkNode root) {

        if (root == null)
            return;
        //space = O(n)
        queue.add(new TNH(root, 0));
        while (!queue.isEmpty()) {
            TNH tnh = queue.get(0);
            System.out.println(queue.size());
            if (queue.size() == 1) {
                tnh.tn.next = null;
            } else {
                if (tnh.h + 1 == queue.get(1).h)
                    tnh.tn.next = null;
                else
                    tnh.tn.next = queue.get(1).tn;
            }
            queue.remove(0);
            if (tnh.tn.left != null)
                queue.add(new TNH(tnh.tn.left, tnh.h + 1));
            if (tnh.tn.right != null)
                queue.add(new TNH(tnh.tn.right, tnh.h + 1));
        }
    }

    //Space = O(1): http://www.programcreek.com/2014/05/leetcode-populating-next-right-pointers-in-each-node-java/
    public void connect2(TreeLinkNode root) {
        if(root == null)
            return;

        TreeLinkNode lastHead = root;//prevous level's head
        TreeLinkNode lastCurrent = null;//previous level's pointer
        TreeLinkNode currentHead = null;//currnet level's head
        TreeLinkNode current = null;//current level's pointer

        while(lastHead!=null){
            lastCurrent = lastHead;

            while(lastCurrent!=null){
                if(currentHead == null){
                    currentHead = lastCurrent.left;
                    current = currentHead;
                }else{
                    current.next = lastCurrent.left;
                    current = current.next;
                }

                if(currentHead != null){
                    current.next = lastCurrent.right;
                    current = current.next;
                }

                lastCurrent = lastCurrent.next;
            }

            //update last head
            lastHead = currentHead;
            currentHead = null;
        }

    }
}
