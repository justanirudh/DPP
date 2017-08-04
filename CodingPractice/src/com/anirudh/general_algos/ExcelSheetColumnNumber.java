package com.anirudh.general_algos;

/**
 * Created by paanir on 2/24/17.
 */
/*
171. Excel Sheet Column Number
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
 */
// B A C = ('B' - 64) * 26^2 + ('A' - 64) * 26^1 + ('C' - 64) * 26
public class ExcelSheetColumnNumber {
    public int titleToNumAux(String s, int num) {
        if (s.length() == 0)
            return num;
        num = (s.charAt(0) - 64) + num * 26; //base 26, 'A' is 65 in ASCII
        return titleToNumAux(s.substring(1), num);
    }

    public int titleToNumber(String s) {
        return titleToNumAux(s, 0);
    }
}
