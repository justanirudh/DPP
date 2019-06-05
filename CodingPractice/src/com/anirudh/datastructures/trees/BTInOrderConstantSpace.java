package com.anirudh.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 6/4/19.
 */
/*
EPI problem 9.11, page 157,
Implement an InOrder Traversal using O(1) space
Assume Nodes have parent fields

 */
public class BTInOrderConstantSpace {
    //cant use explicit or implicit stacks as both are O(h) space

    private List<Integer> getBTInOrderConstantSpace(Node root) {
        Node prev = null;
        Node curr = root;

        List<Integer> res = new ArrayList<>();

        while (curr != null) {
            Node next;
            if (curr.parent == prev) { //we came down to curr from prev
                if (curr.left != null) //keep going left until you find leaf
                    next = curr.left;
                else { //leaf found
                    res.add(curr.data); //done with left
                    if (curr.right != null) //if right is not empty, go right
                        next = curr.right;
                    else //right is empty, so go up to parent
                        next = curr.parent;
                }
            } else if (curr.left == prev) { //is parent
                res.add(curr.data); //done with parent
                if (curr.right != null) //if right is not empty, go right
                    next = curr.right;
                else //right is empty, so go up to parent
                    next = curr.parent;
            } else { //done with both children
                next = curr.parent;
            }

            prev = curr;
            curr = next;
        }
        return res;
    }
}
