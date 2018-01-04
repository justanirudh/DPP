package com.anirudh.datastructures.trees;

/**
 * Created by paanir on 1/3/18.
 */
//under-construction
public class LargestBSTSubtreeInaBT {

    class ChildInfo {
        boolean isBST;
        int min;
        int max;
        int size;

        public ChildInfo(boolean isBST, int min, int max, int size) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.size = size;
        }
    }

    ChildInfo postOrder(Node node, boolean isLeft) {
        if (node == null)
            return null;

        ChildInfo left = postOrder(node.left, true);
        ChildInfo right = postOrder(node.right, false);

        if(left == null && right == null){

        }

        if (left.isBST && right.isBST) {
            if (left.max <= node.data && node.data <= right.min)
                return new ChildInfo(true, , left.size + right.size + 1);
            else
                return left.size > right.size ? left.size : right.size;
        } else if (left.isBST)
            return left.size;
        else if (right.isBST)
            return right.size;
        else
            return 0;

    }

    public int largestBst(Node node) {
        if (node == null)
            return 0;
        ChildInfo left = postOrder(node.left, true);
        ChildInfo right = postOrder(node.right, false);
        if (left.isBST && right.isBST) {
            if (left.max <= node.data && node.data <= right.min)
                return left.size + right.size + 1;
            else
                return left.size > right.size ? left.size : right.size;
        } else if (left.isBST)
            return left.size;
        else if (right.isBST)
            return right.size;
        else
            return 0;
    }
}
