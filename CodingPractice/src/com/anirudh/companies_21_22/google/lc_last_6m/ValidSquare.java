package com.anirudh.companies_21_22.google.lc_last_6m;

/*
593. Valid Square
Medium

615

706

Add to List

Share
Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.

The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.

A valid square has four equal sides with positive length and four equal angles (90-degree angles).



Example 1:

Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: true
Example 2:

Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
Output: false
Example 3:

Input: p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
Output: true


Constraints:

p1.length == p2.length == p3.length == p4.length == 2
-104 <= xi, yi <= 104
Accepted
76,780
Submissions
175,807
 */

/*
1. Check if all sides are equal
2. Check if both diagonals are equal

appraoch 2:
sort by x, then by y: https://leetcode.com/problems/valid-square/solution/
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidSquare {

    class Point {
        int[] p;
        double dist;

        Point(int[] p, double dist) {
            this.p = p;
            this.dist = dist;
        }
    }


    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        //check if first two points equal. if all are the same only then rest of the logic will give true
        if(Arrays.equals(p1, p2))
            return false;
        //check all sides are equal
        int[] source = p1;
        Point pp2 = new Point(p2, (Math.pow(p2[0] - source[0], 2) + Math.pow(p2[1] - source[1], 2)));
        Point pp3 = new Point(p3, (Math.pow(p3[0] - source[0], 2) + Math.pow(p3[1] - source[1], 2)));
        Point pp4 = new Point(p4, (Math.pow(p4[0] - source[0], 2) + Math.pow(p4[1] - source[1], 2)));
        //sort them
        List<Point> list = new ArrayList<>();
        list.add(pp2);
        list.add(pp3);
        list.add(pp4);
        list.sort((po1, po2) -> (int) (po1.dist - po2.dist));
        if (list.get(0).dist != list.get(1).dist)
            return false;
        double sideL = list.get(0).dist;
        int[] source2 = list.get(0).p;
        int[] source3 = list.get(1).p;
        int[] dest = list.get(2).p;
        if ((Math.pow(source2[0] - dest[0], 2) + Math.pow(source2[1] - dest[1], 2)) != sideL)
            return false;
        if ((Math.pow(source3[0] - dest[0], 2) + Math.pow(source3[1] - dest[1], 2)) != sideL)
            return false;

        //now check all angles are 90 degs by checking if diags are equal
        double diag1 = Math.pow(dest[0] - source[0], 2) + Math.pow(dest[1] - source[1], 2);
        double diag2 = Math.pow(source2[0] - source3[0], 2) + Math.pow(source2[1] - source3[1], 2);

        return diag1 == diag2;

    }
}
