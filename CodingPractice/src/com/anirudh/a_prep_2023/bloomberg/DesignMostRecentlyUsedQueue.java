package com.anirudh.a_prep_2023.bloomberg;

import java.util.Arrays;

/*
1756. Design Most Recently Used Queue
Medium
231
16
company
Bloomberg
company
Google
Design a queue-like data structure that moves the most recently used element to the end of the queue.

Implement the MRUQueue class:

MRUQueue(int n) constructs the MRUQueue with n elements: [1,2,3,...,n].
int fetch(int k) moves the kth element (1-indexed) to the end of the queue and returns it.


Example 1:

Input:
["MRUQueue", "fetch", "fetch", "fetch", "fetch"]
[[8], [3], [5], [2], [8]]
Output:
[null, 3, 6, 2, 2]

Explanation:
MRUQueue mRUQueue = new MRUQueue(8); // Initializes the queue to [1,2,3,4,5,6,7,8].
mRUQueue.fetch(3); // Moves the 3rd element (3) to the end of the queue to become [1,2,4,5,6,7,8,3] and returns it.
mRUQueue.fetch(5); // Moves the 5th element (6) to the end of the queue to become [1,2,4,5,7,8,3,6] and returns it.
mRUQueue.fetch(2); // Moves the 2nd element (2) to the end of the queue to become [1,4,5,7,8,3,6,2] and returns it.
mRUQueue.fetch(8); // The 8th element (2) is already at the end of the queue so just return it.


Constraints:

1 <= n <= 2000
1 <= k <= n
At most 2000 calls will be made to fetch.


Follow up: Finding an O(n) algorithm per fetch is a bit easy. Can you find an algorithm with a better complexity for each fetch call?
 */
/*
https://leetcode.com/problems/design-most-recently-used-queue/solutions/1065076/java-sqrt-decomposition-short-doubly-linked-list/
Partition numbers into bucket = sqrt(n) long doubly linked lists (dll).

Access directly the target/bucket-th bucket and seek
to the anwser: target%bucket

When removing the target from the dll, rebalance by appending
each subsequent head to the end of the previous bucket.

Append target to the last bucket dll.

Use sentinel nodes to ease coding.
*/
class MRUQueue {

    //TODO
    Node[] nodes;
    int bucket;

    public MRUQueue(int n) {
        bucket = (int) Math.sqrt(n);
        nodes = new Node[(n + bucket - 1) / bucket];
        Arrays.asList(nodes).replaceAll(i -> new Node(-1));
        for (int i = 1; i <= n; ++i) {
            nodes[(i - 1) / bucket].pre.append(new Node(i));
        }
    }

    public int fetch(int k) {
        Node ans = nodes[--k / bucket].nxt;
        for (int i = k % bucket; i > 0; --i) {//seek to target inside bucket
            ans = ans.nxt;
        }
        ans.remove();
        for (int i = 1 + k / bucket; i < nodes.length; ++i) {//rebalance
            nodes[i - 1].pre.append(nodes[i].nxt.remove());
        }
        nodes[nodes.length - 1].pre.append(ans);
        return ans.val;
    }

    static class Node {
        Node pre = this, nxt = this;
        int val;

        Node(int v) {
            val = v;
        }

        void append(Node node) {
            Node tmp = nxt;
            nxt = node;
            node.pre = this;
            node.nxt = tmp;
            tmp.pre = node;
        }

        Node remove() {
            pre.nxt = nxt;
            nxt.pre = pre;
            return nxt = pre = this;
        }
    }
}
/* O(n) soln
    List<Integer> l;
    public MRUQueue(int n) {
        l = new ArrayList<>();
        for(int i = 1; i <=n; ++i) {
            l.add(i);
        }
    }

    public int fetch(int k) { //O(n)
        int res = l.get(k - 1);
        l.remove(k-1);
        l.add(res);
        return res;
    }
 */
