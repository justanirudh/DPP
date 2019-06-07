package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 2/13/17.
 */
/*
99. Recover Binary Search Tree

Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
*/

//O(1) space, O(n) time
//track the previous node, keep two pointers, one for each elem exchanged
// Eg 1 4 3 2 5
public class RecoverBST {

    TreeNode prev, node1, node2;

    private void inOrderTraversal(TreeNode currNode) {
        if (currNode == null)
            return;

        inOrderTraversal(currNode.left);

        int currElem = currNode.val;
        if (prev != null && prev.val > currElem) { //4(prev) > 3(curr) in 1 4 3 2 5
            if (node1 == null) { //if first elem hasnt been discovered yet
                node1 = prev; // 4 is the swapped elem
                node2 = currNode; //3 can be the potential swapped elem until we find another one.
            } else
                node2 = currNode; //found the other swapped elem as 3(prev) > 2 (curr)
        }
        prev = currNode; //updating prev

        inOrderTraversal(currNode.right);
    }

    public void recoverTree(TreeNode root) {
        inOrderTraversal(root); //as sorted array is obtained by inorder traversal

        //now swap
        if (node1 != null && node2 != null) {
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }
    }
}
