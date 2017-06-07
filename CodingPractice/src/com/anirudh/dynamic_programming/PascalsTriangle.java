package com.anirudh.dynamic_programming;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 12/28/16.
 */

/*
118. Pascal's Triangle   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 111171
Total Submissions: 304282
Difficulty: Easy
Contributors: Admin
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

 */

/*
119. Pascal's Triangle II   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 98604
Total Submissions: 282867
Difficulty: Easy
Contributors: Admin
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalsTriangle {

    public static List<List<Integer>> generate(int numRows) {
        if (numRows == 0)
            return new ArrayList<>();
        else if (numRows == 1) {
            List<List<Integer>> out = new ArrayList<>();
            ArrayList<Integer> in = new ArrayList<>();
            in.add(1);
            out.add(in);
            return out;
        } else {
            List<List<Integer>> out = generate(numRows - 1);
            ArrayList<Integer> newRow = new ArrayList<>();
            newRow.add(1);
            List<Integer> outLastRow = out.get(out.size() - 1); //last row of out
            if (outLastRow.size() != 1) { //for numRows = 2
                for (int i = 0; i < out.size() - 1; ++i) {
                    newRow.add(outLastRow.get(i) + outLastRow.get(i + 1));
                }
            }
            newRow.add(1);
            out.add(newRow);
            return out;
        }
    }

    //Space: O (2k - 1) = O(k)
    public static List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0)
            return new ArrayList<>();
        else if (rowIndex == 0) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(1);
            return row;
        } else {
            List<Integer> lastRow = getRow(rowIndex - 1);
            ArrayList<Integer> newRow = new ArrayList<>();
            newRow.add(1);
            if (lastRow.size() != 1) { //for numRows = 2
                for (int i = 0; i < lastRow.size() - 1; ++i) {
                    newRow.add(lastRow.get(i) + lastRow.get(i + 1));
                }
            }
            newRow.add(1);
            return newRow;
        }
    }

    public static void main(String[] args) {
//        List<List<Integer>> list = generate(5);
//        for (int i = 0; i < list.size(); ++i) {
//            List<Integer> in = list.get(i);
//            for (int j = 0; j < in.size(); ++j)
//                System.out.print(in.get(j) + ",");
//            System.out.println("\n");
//        }

        List<Integer> row = getRow(0);
        for (int j = 0; j < row.size(); ++j)
            System.out.print(row.get(j) + ",");
    }
}
