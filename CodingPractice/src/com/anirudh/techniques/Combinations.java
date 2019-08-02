package com.anirudh.techniques;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by paanir on 11/5/17.
 */
// Java program to print all combination of size r in an array of size n
/*
77. Combinations
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */

//https://leetcode.com/problems/combinations/solution/
class Combinations {

    private List<List<Integer>> output;
    private int n;
    private int k;

    private void backtrack(int first, List<Integer> curr) {
        if (curr.size() == k) // if the combination is done
            output.add(new ArrayList<>(curr));

        for (int i = first; i <= n; ++i) {
            curr.add(i); // add i into the current combination
            backtrack(i + 1, curr); // use next integers to complete the combination
            curr.remove(curr.size() - 1); // backtrack; remove last
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        output = new ArrayList<>();
        backtrack(1, new ArrayList<>());
        return output;
    }
}