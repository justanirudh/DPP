package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by paanir on 9/25/17.
 */
//LC: 449, under-construction
public class SerializeAndDeserializeBST {

    void inOrder(TreeNode root, List<String> list) {
        if (root == null)
            return;
        inOrder(root.left, list);
        list.add(Integer.toString(root.val));
        inOrder(root.right, list);
    }

    void preOrder(TreeNode root, List<String> list) {
        if (root == null)
            return;
        list.add(Integer.toString(root.val));
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> list = new ArrayList<>();
        inOrder(root, list);
        List<String> list2 = new ArrayList<>();
        preOrder(root, list2);
        String joined1 = String.join(",", list);
        String joined2 = String.join(",", list2);
        System.out.println(joined1 + ";" + joined2);
        return joined1 + ";" + joined2;
    }

    class TreeNodeAndPreIndex {
        TreeNode node;
        int preIndex;

        TreeNodeAndPreIndex(TreeNode node, int preIndex) {
            this.node = node;
            this.preIndex = preIndex;
        }
    }

    int binarySearch(int rootVal, int[] inOrder, int start, int end) { //is sorted so can do binary search
        if (start < end) {
            int mid = start + (end - start) / 2;
            if (rootVal == inOrder[mid])
                return mid;
            else if (rootVal > inOrder[mid])
                return binarySearch(rootVal, inOrder, mid + 1, end);
            else
                return binarySearch(rootVal, inOrder, start, mid - 1);
        }
        return -1;
    }

    TreeNodeAndPreIndex constructTree(int[] inOrder, int inStart, int inEnd, int[] preOrder, int preIndex) {
        if (inStart > inEnd || preIndex >= preOrder.length) {
            return new TreeNodeAndPreIndex(null, preIndex);
        }
        int rootVal = preOrder[preIndex];
        TreeNode root = new TreeNode(rootVal);
        preIndex++;

        int inIndex = binarySearch(rootVal, inOrder, inStart, inEnd);
        TreeNodeAndPreIndex tiL = constructTree(inOrder, inStart, inIndex - 1, preOrder, preIndex);
        root.left = tiL.node;
        TreeNodeAndPreIndex tiR = constructTree(inOrder, inIndex + 1, inEnd, preOrder, tiL.preIndex);
        root.right = tiR.node;
        return new TreeNodeAndPreIndex(root, tiR.preIndex);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] traversals = data.split(";");
        int[] inOrder = Arrays.stream(traversals[0].split(",")).mapToInt(Integer::parseInt).toArray();
        int[] preOrder = Arrays.stream(traversals[1].split(",")).mapToInt(Integer::parseInt).toArray();
        return (constructTree(inOrder, 0, inOrder.length - 1, preOrder, 0)).node;
    }
}
