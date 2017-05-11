package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 1/6/17.
 */
/*
228. Summary Ranges   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 67049
Total Submissions: 239568
Difficulty: Medium
Contributors: Admin
Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */
public class SummaryRanges {

    public static List<String> summaryRanges(int[] nums) {
        ArrayList<String> sr = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            StringBuilder sb = new StringBuilder();
            sb.append(nums[i]);
            i++;
            if (i < nums.length && nums[i] == nums[i - 1] + 1) {
                sb.append("->");
                while (i < nums.length && nums[i] == nums[i - 1] + 1)
                    i++;
                sb.append(nums[i - 1]);
            }
            sr.add(sb.toString());
        }
        return sr;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4, 5, 7, 9, 10};
        List<String> ss = summaryRanges(nums);
        for (String s : ss) {
            System.out.println(s);
        }
    }
}
