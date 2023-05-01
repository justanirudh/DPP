package com.anirudh.a_prep_2023.bloomberg;

import java.util.*;

/*
1823. Find the Winner of the Circular Game
Medium
2.4K
46
company
Bloomberg
company
Goldman Sachs
company
Google
There are n friends that are playing a game. The friends are sitting in a circle and are numbered from 1 to n in clockwise order. More formally, moving clockwise from the ith friend brings you to the (i+1)th friend for 1 <= i < n, and moving clockwise from the nth friend brings you to the 1st friend.

The rules of the game are as follows:

Start at the 1st friend.
Count the next k friends in the clockwise direction including the friend you started at. The counting wraps around the circle and may count some friends more than once.
The last friend you counted leaves the circle and loses the game.
If there is still more than one friend in the circle, go back to step 2 starting from the friend immediately clockwise of the friend who just lost and repeat.
Else, the last friend in the circle wins the game.
Given the number of friends, n, and an integer k, return the winner of the game.
 */

/*
Create a circular doubly linked list,
traverse k times, remove it, do pointer manipulation,
traverse k more times, so on.
Do this for n-1 times to get a single node at the end
 */
public class FindtheWinneroftheCircularGame {

    //Sx: O(n)
    //Tx: O(kn)
    static class Node {
        int val;
        Node next;
        Node prev;

        Node(int val) {
            this.val = val;
        }
    }

    public int findTheWinner(int n, int k) {
        Node head = null;
        Node prev = null;
        for (int i = 1; i <= n; ++i) {
            if (head == null) {
                head = new Node(i);
                prev = head;
            } else {
                Node curr = new Node(i);
                curr.prev = prev;
                prev.next = curr;
                prev = curr;
            }
        }
        assert prev != null;
        prev.next = head;
        head.prev = prev;

        Node curr = head;
        for (int i = 0; i < n - 1; ++i) { //as need 1 node at the end
            for (int j = 0; j < k - 1; ++j) { // as need to count current elem
                curr = curr.next;
            }
            //remove curr
            Node pv = curr.prev;
            Node ne = curr.next;
            pv.next = ne;
            ne.prev = pv;
            curr = ne;
        }
        return curr.val;
    }

    //Sx = O(n)
    //Tx = O(n^2) but beats above
    public int findTheWinnerSlow(int n, int k) {
        List<Integer> f = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            f.add(i);
        }
        int firsti = 0;
        while (f.size() != 1) { //O(n)
            int remi = (firsti + k - 1) % f.size(); //O(1)
            boolean isLast = remi == f.size() - 1;
            f.remove(remi); //O(n)
            if (!isLast)
                firsti = remi;
            else
                firsti = 0;
        }
        return f.get(0);

    }
}
