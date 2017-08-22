package com.anirudh.dynamic_programming;

/**
 * Created by paanir on 8/21/17.
 */

import com.anirudh.datastructures.trees.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//LeetCode #337 Under-construction
public class HouseRobber3 {

    public class Node {
        TreeNode tn;
        int height;

        Node(TreeNode tn, int height) {
            this.tn = tn;
            this.height = height;
        }
    }

    public int rob(TreeNode root) {
        //get sum of all nodes in the same level, make an array of it
        //now the probem is similar to HouseRobber-1
        if (root == null)
            return 0;

        List<Integer> levelSums = new ArrayList<>();
        levelSums.add(0);

        Node node = new Node(root, 0);

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node head = queue.remove();
            if (head.tn.left != null)
                queue.add(new Node(head.tn.left, head.height + 1));
            if (head.tn.right != null)
                queue.add(new Node(head.tn.right, head.height + 1));

            levelSums.set(levelSums.size() - 1, levelSums.get(levelSums.size() - 1) + head.tn.val); //add head to last number

            if (!queue.isEmpty() && queue.element().height == head.height + 1)
                levelSums.add(0); //set stage for next level
        }
        //now the problem is similar to HouseRobber-1
        return new HouseRobber2().maxRob(levelSums.stream().mapToInt(i -> i).toArray(),
                0,
                levelSums.size() - 1);

    }
}
