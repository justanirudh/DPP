package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 1/3/18.
 */
//under-construction
public class LargestBSTSubtreeInaBT {

    //    class ChildInfo {
//        boolean isBST;
//        int min;
//        int max;
//        int size;
//
//        public ChildInfo(boolean isBST, int min, int max, int size) {
//            this.isBST = isBST;
//            this.min = min;
//            this.max = max;
//            this.size = size;
//        }
//    }
//
//    ChildInfo postOrder(Node node, boolean isLeft) {
//        if (node == null)
//            return null;
//
//        ChildInfo left = postOrder(node.left, true);
//        ChildInfo right = postOrder(node.right, false);
//
//        if(left == null && right == null){
//
//        }
//
//        if (left.isBST && right.isBST) {
//            if (left.max <= node.data && node.data <= right.min)
//                return new ChildInfo(true, , left.size + right.size + 1);
//            else
//                return left.size > right.size ? left.size : right.size;
//        } else if (left.isBST)
//            return left.size;
//        else if (right.isBST)
//            return right.size;
//        else
//            return 0;
//
//    }
//
//    public int largestBst(Node node) {
//        if (node == null)
//            return 0;
//        ChildInfo left = postOrder(node.left, true);
//        ChildInfo right = postOrder(node.right, false);
//        if (left.isBST && right.isBST) {
//            if (left.max <= node.data && node.data <= right.min)
//                return left.size + right.size + 1;
//            else
//                return left.size > right.size ? left.size : right.size;
//        } else if (left.isBST)
//            return left.size;
//        else if (right.isBST)
//            return right.size;
//        else
//            return 0;
//    }
//    class Node {
//
//        int data;
//        Node left, right;
//
//        Node(int d) {
//            data = d;
//            left = right = null;
//        }
//    }

    class Value {

        int max_size = 0; // for size of largest BST
        boolean is_bst = false;
        int min = Integer.MAX_VALUE;  // For minimum value in right subtree
        int max = Integer.MIN_VALUE;  // For maximum value in left subtree
    }

    class BinaryTree {

        //        static Node root;
        Node root;
        Value val = new Value();

        /* Returns size of the largest BST subtree in a Binary Tree
         (efficient version). */
        int largestBST(Node node) {

            largestBSTUtil(node, val, val, val, val);

            return val.max_size;
        }

        /* largestBSTUtil() updates *max_size_ref for the size of the largest BST
         subtree.   Also, if the tree rooted with node is non-empty and a BST,
         then returns size of the tree. Otherwise returns 0.*/
        int largestBSTUtil(Node node, Value min_ref, Value max_ref,
                           Value max_size_ref, Value is_bst_ref) {

            /* Base Case */
            if (node == null) {
                is_bst_ref.is_bst = true; // An empty tree is BST
                return 0;    // Size of the BST is 0
            }

            int min = Integer.MAX_VALUE;

            /* A flag variable for left subtree property
            i.e., max(root->left) < root->data */
            boolean left_flag = false;

            /* A flag variable for right subtree property
            i.e., min(root->right) > root->data */
            boolean right_flag = false;

            int ls, rs; // To store sizes of left and right subtrees

        /* Following tasks are done by recursive call for left subtree
         a) Get the maximum value in left subtree (Stored in *max_ref)
         b) Check whether Left Subtree is BST or not (Stored in *is_bst_ref)
         c) Get the size of maximum size BST in left subtree (updates *max_size) */
            max_ref.max = Integer.MIN_VALUE;
            ls = largestBSTUtil(node.left, min_ref, max_ref, max_size_ref, is_bst_ref);
            if (is_bst_ref.is_bst && node.data > max_ref.max) {
                left_flag = true;
            }

        /* Before updating *min_ref, store the min value in left subtree. So that we
         have the correct minimum value for this subtree */
            min = min_ref.min;

        /* The following recursive call does similar (similar to left subtree)
         task for right subtree */
            min_ref.min = Integer.MAX_VALUE;
            rs = largestBSTUtil(node.right, min_ref, max_ref, max_size_ref, is_bst_ref);
            if (is_bst_ref.is_bst && node.data < min_ref.min) {
                right_flag = true;
            }

            // Update min and max values for the parent recursive calls
            if (min < min_ref.min) {
                min_ref.min = min;
            }
            if (node.data < min_ref.min) // For leaf nodes
            {
                min_ref.min = node.data;
            }
            if (node.data > max_ref.max) {
                max_ref.max = node.data;
            }

        /* If both left and right subtrees are BST. And left and right
         subtree properties hold for this node, then this tree is BST.
         So return the size of this tree */
            if (left_flag && right_flag) {
                if (ls + rs + 1 > max_size_ref.max_size) {
                    max_size_ref.max_size = ls + rs + 1;
                }
                return ls + rs + 1;
            } else {
                //Since this subtree is not BST, set is_bst flag for parent calls
                is_bst_ref.is_bst = false;
                return 0;
            }
        }
    }
}
