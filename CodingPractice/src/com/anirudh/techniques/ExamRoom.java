package com.anirudh.techniques;

import java.util.Set;
import java.util.TreeSet;

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

public class ExamRoom {

    TreeSet<Integer> positions;
    int numSeats;

    public ExamRoom(int N) {
        positions = new TreeSet<>();
        numSeats = N;
    }

    /*
    T = O(N)
     */
    public int seat() {
        int res = -1;

        //base cases
        if (positions.size() == 0) { //empty seats, then sit in the front
            res = 0;
            positions.add(res);
            return res;
        }

        //positions size >=1

        /*
        1. check max distance in every interval
        2. if 0th position is free, check distance from nearest
        3. If last position id free, check distance from nearest
        return position with Min (1,2,3)
         */

        int maxD = Integer.MIN_VALUE;

        //Opt-1
        Integer prev = null, curr;
        for (Integer pos : positions) {
            if (prev == null) {
                prev = pos;
                continue;
            }
            curr = pos;
            int currD = (curr - prev) / 2;
            if (currD > maxD) { //strictly greater, not equals. because if we find equals, we will ignore as we need to find one with lowest index
                maxD = currD;
                res = prev + currD;
            }
            prev = curr;
        }

        //Opt-2
        if (!positions.contains(0)) {
            int currD = positions.first(); //no d/2 because we will place the elem at 0, not in between 0 and first pos
            if (currD >= maxD) { //>= because if this is true, we have found a new lowest index that fulfills all constraints
                maxD = currD;
                res = 0;
            }
        }

        //Opt-3
        int lastPos = numSeats - 1;
        if (!positions.contains(lastPos)) {
            int currD = lastPos - positions.last();
            if (currD > maxD) //strictly greater, not equals
                res = lastPos;
        }

        positions.add(res);
        return res;
    }

    // T = O(logn) This implementation provides guaranteed log(n) time cost for the basic operations (add, remove and contains).
    public void leave(int p) {
        positions.remove(p);
    }
}
