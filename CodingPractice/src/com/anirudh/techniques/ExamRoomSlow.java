package com.anirudh.techniques;

import java.util.TreeSet;

/**
 * Created by paanir on 8/4/19.
 */

/*
S: O(n)
seat: T = O(n)
leave: T = O(logn) remove from treeset is O(logn): RB tree
 */
public class ExamRoomSlow {
    //S: O(n)

    TreeSet<Integer> positions;
    int numSeats;

    public ExamRoomSlow(int N) {
        positions = new TreeSet<>();
        numSeats = N;
    }

    /*
    T = O(n)
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
        3. If last position is free, check distance from nearest
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

    // T = O(logn) remove from treeset is O(logn): RB tree
    public void leave(int p) {
        positions.remove(p);
    }
}
