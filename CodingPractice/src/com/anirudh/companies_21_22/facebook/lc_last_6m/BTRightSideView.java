package com.anirudh.companies_21_22.facebook.lc_last_6m;

/**
 * Created by paanir on 1/30/17.
 */
/*
199. Binary Tree Right Side View
Medium

1038

45

Favorite

Share
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
 */

/*
do level order traversal with first node on right
Insert root into Queue
while(q! empty) {
    //find size of queue

    for(int i to size) {
    poll from queue
    push children to stack
    tracking the last elem
}
    put the last elem in result array
}
 */

public class BTRightSideView {

    //Just use level order traversal.
    //Use size of level to find the last element
    // See 107. Binary Tree Level Order Traversal II
    //TODO: Implement

}
