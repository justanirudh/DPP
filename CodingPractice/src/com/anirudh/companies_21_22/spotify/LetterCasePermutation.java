<<<<<<< HEAD:CodingPractice/src/com/anirudh/interview_prep_2021_2022/two_sigma/anki/LetterCasePermutation.java
package com.anirudh.interview_prep_2021.spotify.anki;
=======
package com.anirudh.interview_prep_2021_2022.spotify;
>>>>>>> d9fa94e1feca12088b4a171501f0b47747e93ffe:CodingPractice/src/com/anirudh/interview_prep_2021_2022/spotify/LetterCasePermutation.java

import java.util.List;

/*
784. Letter Case Permutation
Medium

2408

130

Add to List

Share
Given a string s, we can transform every letter individually to be lowercase or uppercase to create another string.

Return a list of all possible strings we could create. You can return the output in any order.



Example 1:

Input: s = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2:

Input: s = "3z4"
Output: ["3z4","3Z4"]
Example 3:

Input: s = "12345"
Output: ["12345"]
Example 4:

Input: s = "0"
Output: ["0"]
 */

/*
1. Start with a results array res: [""];
2. Start iterating over the string
2.1 If we see a character c, duplicate elements in res, append lo(c) to 1st half, up(c) to 2nd half
2.2 If we see a number, just append to all elems in res
return res;
Time Complexity: O(2^{N} , where N is the length of S. This reflects the cost of writing the answer.
2 loops: 1 goes over string, other goes over results array duplicating it each time

Space Complexity: O(2^N * N).
 */
public class LetterCasePermutation {

    public List<String> letterCasePermutation(String s) {
        //TODO
        return null;
    }
}
