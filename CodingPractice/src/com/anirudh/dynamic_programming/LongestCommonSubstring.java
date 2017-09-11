package com.anirudh.dynamic_programming;

/**
 * Created by paanir on 9/7/17.
 */

//http://www.geeksforgeeks.org/longest-common-substring/
import java.util.*;
import java.lang.*;

class LongestCommonSubstring {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);
        int num_problems = Integer.parseInt(stdin.nextLine());
        while (num_problems != 0) {
            String[] lengths = stdin.nextLine().split(" ");
            int m = Integer.parseInt(lengths[0]);
            int n = Integer.parseInt(lengths[1]);
            char[] mSeq = stdin.nextLine().toCharArray();
            char[] nSeq = stdin.nextLine().toCharArray();
            //logic starts here
            int[][] matrix = new int[m + 1][n + 1];
            int maxLength = 0;
            for (int i = 0; i <= m; ++i) {
                for (int j = 0; j <= n; ++j) {
                    if (i == 0 | j == 0)
                        matrix[i][j] = 0;
                    else {
                        if (mSeq[i - 1] == nSeq[j - 1])
                            matrix[i][j] = matrix[i - 1][j - 1] + 1;
                        //else 0, which is prepopulated - This is carried over in LCS. This is the ONLY difference between LCSeq and LCSstring
                        if (maxLength < matrix[i][j])
                            maxLength = matrix[i][j];
                    }
                }
            }

            System.out.println(maxLength);
            num_problems--;
        }
    }
}
