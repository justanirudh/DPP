package com.anirudh.dynamic_programming_greedy;

import java.util.*;

/**
 * Created by paanir on 9/8/17.
 */
//http://www.geeksforgeeks.org/printing-longest-common-subsequence/
public class LongestCommonSubsequence {

    //Bottom up
    // Returns length of LCS for X[0..m-1], Y[0..n-1]
    static void lcs(String X, String Y, int m, int n) {
        int[][] L = new int[m + 1][n + 1];

        // Following steps build L[m+1][n+1] in bottom up fashion. Note
        // that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (X.charAt(i - 1) == Y.charAt(j - 1))
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
            }
        }

        //STOP here if just finding out the length

        // Following code is used to print LCS
        int index = L[m][n];
        int temp = index;

        // Create a character array to store the lcs string
        char[] lcs = new char[index + 1];
        lcs[index] = '\0'; // Set the terminating character

        // Start from the right-most-bottom-most corner and
        // one by one store characters in lcs[]
        int i = m, j = n;
        while (i > 0 && j > 0) {
            // If current character in X[] and Y are same, then
            // current character is part of LCS
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                // Put current character in result
                lcs[index - 1] = X.charAt(i - 1);

                // reduce values of i, j and index
                i--;
                j--;
                index--;
            }

            // If not same, then find the larger of two and
            // go in the direction of larger value
            else if (L[i - 1][j] > L[i][j - 1])
                i--;
            else
                j--;
        }

        // Print the lcs
        System.out.print("LCS of " + X + " and " + Y + " is ");
        for (int k = 0; k <= temp; k++)
            System.out.print(lcs[k]);

    }

//---------------------------------------------------------------------------------
    //attempt at top-down. //TBD: why memoization does not work, works without memoization
    /*
    5 6
    1 2 3 4 1
    3 4 1 2 1 3
    ans: 1 2 3 1
    exp: 3 4 1
     */
    static String[] seq1;
    static String[] seq2;
    static Map<List<Integer>, List<String>> memoize; //List of indices (2 indices) to result list

    static public List<String> getLCS(int pos1, int pos2, List<String> res) { //res is result till now

        if (pos1 == -1 || pos2 == -1)
            return res;

        List<Integer> indices = new ArrayList<>();
        indices.add(pos1);
        indices.add(pos2);

        if (memoize.containsKey(indices)) {
            List<String> newRes = new ArrayList<>(memoize.get(indices));
            newRes.addAll(res);
            return newRes;
        } else {
            List<String> newRes = null;
            if (seq1[pos1].equals(seq2[pos2])) {
                List<String> copyRes = new ArrayList<>(res); //imp to pass copies as Java, reference is passed by value
                copyRes.add(0, seq1[pos1]);
                newRes = getLCS(pos1 - 1, pos2 - 1, copyRes);
            } else {
                List<String> newRes1 = getLCS(pos1 - 1, pos2, new ArrayList<>(res));
                List<String> newRes2 = getLCS(pos1, pos2 - 1, new ArrayList<>(res));
                newRes = newRes1.size() > newRes2.size() ? newRes1 : newRes2;
            }
            memoize.put(indices, newRes);
            return newRes;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner stdin = new Scanner(System.in);

        String[] lengths = stdin.nextLine().split(" ");
        int len1 = Integer.parseInt(lengths[0]);
        int len2 = Integer.parseInt(lengths[1]);
        seq1 = stdin.nextLine().split(" ");
        seq2 = stdin.nextLine().split(" ");
        memoize = new HashMap<>();

        List<String> lcs = getLCS(len1 - 1, len2 - 1, new ArrayList<>());
        System.out.println(String.join(" ", lcs));

    }
}
