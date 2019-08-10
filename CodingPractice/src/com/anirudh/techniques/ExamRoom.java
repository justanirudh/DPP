package com.anirudh.techniques;

import java.util.*;

/**
 * Created by paanir on 8/3/19.
 */
/*
855. Exam Room
Medium

In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)

Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.



Example 1:

Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
Output: [null,0,9,4,2,null,5]
Explanation:
ExamRoom(10) -> null
seat() -> 0, no one is in the room, then the student sits at seat number 0.
seat() -> 9, the student sits at the last seat number 9.
seat() -> 4, the student sits at the last seat number 4.
seat() -> 2, the student sits at the last seat number 2.
leave(4) -> null
seat() -> 5, the student sits at the last seat number 5.

​​​​​​​

Note:

    1 <= N <= 10^9
    ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
    Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.

Accepted
16,665
Submissions
42,831
 */

/*
S: O(n)
seat: T = O(logn)
leave: T = O(logn)
 */
class ExamRoom {
    private int N;
    private TreeSet<Interval> intervals;
    private Map<Integer, Interval> startMap; //use these maps to remove intervals from intervals_set when leave() is called in O(logn) time, rather than O(n) (O(n) when doing a linear search)
    private Map<Integer, Interval> endMap;

    private class Interval {
        int x, y, dist;

        Interval(int x, int y) {
            this.x = x;
            this.y = y;
            if (x == -1) { //dist used for reverse sorting
                this.dist = y;
            } else if (y == N) {
                this.dist = N - 1 - x;
            } else {
                this.dist = Math.abs(x - y) / 2;
            }
        }
    }

    private void add(Interval interval) {
        intervals.add(interval);
        startMap.put(interval.x, interval);
        endMap.put(interval.y, interval);
    }

    private void remove(Interval interval) {
        intervals.remove(interval);
        startMap.remove(interval.x);
        endMap.remove(interval.y);
    }

    public ExamRoom(int N) {
        this.N = N;
        this.startMap = new HashMap<>();
        this.endMap = new HashMap<>();
        intervals = new TreeSet<>((a, b) -> {
            int distA = a.dist;
            int distB = b.dist;
            if (distA != distB) {
                return distB - distA; //reverse sorted
            } else {
                return a.x - b.x; //take lowest index
            }
        });
        Interval init = new Interval(-1, N);
        add(init);
    }

    //
    public int seat() {
        Interval interval = intervals.pollFirst(); //get the largest interval
        int pos;
        if (interval.x == -1) { //student will take first seat
            pos = 0;
        } else if (interval.y == N) { //student will take last seat
            pos = N - 1;
        } else {
            pos = interval.x + interval.dist;
        }

        Interval left = new Interval(interval.x, pos);
        Interval right = new Interval(pos, interval.y);
        add(left);
        add(right);

        return pos;
    }

    //remove 2 intervals and add a new merged interval
    public void leave(int p) {
        Interval left = endMap.get(p);
        Interval right = startMap.get(p);
        Interval merged = new Interval(left.x, right.y);
        remove(left);
        remove(right);
        add(merged);
    }

}

