package com.anirudh.math;

/**
 * Created by paanir on 2/25/17.
 */
/*
168. Excel Sheet Column Title
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
            n = n - 1; //IMP: Instead of 1 -> A, 26 -> Z, we can assume that 0 -> A, 25 -> Z
            int offset = n % 26; //similar to converting decimal to binary: mod, then divide
            return convertToTitle(n / 26) + (char) ('A' + offset); //same as how we would make a binary string from decimal
        }
    }
}
