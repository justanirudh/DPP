package com.anirudh.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 1/30/17.
 */
public class BinaryTreeRightSideVIew {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class NodeWithHeight {
        int height;
        TreeNode node;

        public NodeWithHeight(TreeNode node, int height) {
            this.node = node;
            this.height = height;
        }
    }


    public List<Integer> rightSideView(TreeNode root) {

        ArrayList<NodeWithHeight> queue = new ArrayList<>();
        ArrayList<Integer> rightSides = new ArrayList<>();
        //Level order traversal

        if (root == null)
            return rightSides;
        NodeWithHeight nwh = new NodeWithHeight(root, 0);

        queue.add(nwh);

        while (!queue.isEmpty()) {
            NodeWithHeight curr = queue.get(0);
            //if last elem, it is right side
            if (queue.size() == 1) {
                rightSides.add(curr.node.val);
            } else { //see if next elem's height one more than curr's height. If so, it is rightmost elem
                if (queue.get(1).height == curr.height + 1)
                    rightSides.add(curr.node.val);
            }
            queue.remove(0);
            if (curr.node.left != null)
                queue.add(new NodeWithHeight(curr.node.left, curr.height + 1));
            if (curr.node.right != null)
                queue.add(new NodeWithHeight(curr.node.right, curr.height + 1));
        }
        return rightSides;
    }
}
