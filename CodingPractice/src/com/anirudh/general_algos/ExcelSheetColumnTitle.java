package com.anirudh.general_algos;

/**
 * Created by paanir on 2/25/17.
 */
/*
168. Excel Sheet Column Title Add to List
Description  Submission  Solutions
Total Accepted: 91655
Total Submissions: 369086
Difficulty: Easy
Contributors: Admin
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
 */
// B A C = ('B' - 64) * 26^2 + ('A' - 64) * 26^1 + ('C' - 64) * 26
public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) { //base 26
        if (n == 0)
            return "";
        else {
            n = n - 1;
            int offset = n % 26;
            int rest = n / 26;
            return convertToTitle(rest) + (char) ('A' + offset);
        }
    }
}
