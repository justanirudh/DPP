package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
1110. Delete Nodes And Return Forest
Medium

2490

72

Add to List

Share
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest. You may return the result in any order.



Example 1:


Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
Example 2:

Input: root = [1,2,4,null,3], to_delete = [3]
Output: [[1,2,4]]


Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
 */

/*
Do postorder so that leaves are removed before parents and wont cause complications where same sub-tree has multiple nodes
to be removed
 */
public class DeleteNodesAndReturnForest {

  Set<Integer> toDelete;
  List<TreeNode> res;

  //passing isRight to set correct child of parent to null when node is removed
  void postOrder(TreeNode node, TreeNode parent, boolean isRight) {
    if (node == null) {
      return;
    }
    postOrder(node.left, node, false);
    postOrder(node.right, node, true);
    if (toDelete.contains(node.val)) {
      if (node.left != null) {
        res.add(node.left);
      }
      if (node.right != null) {
        res.add(node.right);
      }
      if (parent != null) {  //if not root
        if (isRight) {
          parent.right = null;
        } else {
          parent.left = null;
        }
      }
    }

  }


  public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

    toDelete = new HashSet<>();
    for (int i : to_delete) {
      toDelete.add(i);
    }
    res = new ArrayList<>();
    postOrder(root, null, false);

    if(!toDelete.contains(root.val))
      res.add(root); //add root if root not part of to_be_deleted

    return res;
  }
}
