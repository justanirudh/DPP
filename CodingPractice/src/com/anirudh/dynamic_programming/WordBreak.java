package com.anirudh.dynamic_programming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by paanir on 7/14/17.
 */
//LC#139 under-construction
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[][] matrix = new boolean[s.length()][s.length()];

        //Example: iamace
        //length 1 words; base case: i,a,m,a,c,e
        for(int i = 0; i < s.length(); ++i){
            int start = i; //close bracket
            int end = i + 1; //open bracket
            if(dict.contains(s.substring(start, end)))
                matrix[start][end-1] = true;
        }

        //for 2 length combo (ia, am, ma, ac,ce), 3 length combo etc.
        for(int length = 2; length <= s.length(); ++length){
            int start = 0;
            int end = start + length;

        }

    }
}

