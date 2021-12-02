package com.anirudh.interview_prep_2021_2022.two_sigma;

import java.util.*;

/**
 * Created by paanir on 9/5/21.
 */
/*
710. Random Pick with Blacklist
Hard

472

82

Add to List

Share
You are given an integer n and an array of unique integers blacklist. Design an algorithm to pick a random integer in the range [0, n - 1] that is not in blacklist. Any integer that is in the mentioned range and not in blacklist should be equally likely returned.

Optimize your algorithm such that it minimizes the call to the built-in random function of your language.

Implement the Solution class:

Solution(int n, int[] blacklist) Initializes the object with the integer n and the blacklisted integers blacklist.
int pick() Returns a random integer in the range [0, n - 1] and not in blacklist. All the possible integers should be equally likely returned.


Example 1:

Input
["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
[[7, [2, 3, 5]], [], [], [], [], [], [], []]
Output
[null, 6, 4, 1, 6, 1, 6, 4]

Explanation
Solution solution = new Solution(7, [2, 3, 5]);
solution.pick(); // return 6, any integer from [1,4,6] should be ok. Note that for every call of pick, 1, 4, and 6 must be equally likely to be returned (i.e., with probability 1/3).
solution.pick(); // return 4
solution.pick(); // return 1
solution.pick(); // return 6
solution.pick(); // return 1
solution.pick(); // return 6
solution.pick(); // return 4
 */

/*
Option 1: keep RANDing in range until the number is not in blacklist. This gives TLE (as it says to optimize calls to rand)
Option 2: create whitelist and randomly select from it : TLE
Option 3:
3.1 Break the [0,n-1] range into [0, n-bLen-1] and [n-bLen, n-1] elements. bLen = length of blacklist
3.2 Create Mapping the blacklisted elems in [0, n-bLen-1] to "whitelisted" elems in [n-bLen, n-1]
3.3 Then just pick a random number from [0, n-bLen-1]. If it in blacklisted, return the mapped whitelisted
 */
public class RandomPickBlacklist {

    Random r = new Random();
    Map<Integer, Integer> mapping = new HashMap<>();
    int n;
    int bLen;

    public RandomPickBlacklist(int n, int[] blacklist) {

        bLen = blacklist.length;
        this.n = n;

        Set<Integer> blacklistSet = new HashSet<>();

        List<Integer> bLeft = new ArrayList<>(); //blacklisted elems in [0, n-bLen)
        for (int b : blacklist) {
            if (b < n - bLen) { //range is from 0 to n-bLen, so just check < n-bLen
                bLeft.add(b);
            }
            blacklistSet.add(b); //also create a blacklist set to be used when getting white side
        }

        List<Integer> wRight = new ArrayList<>(); //whitelisted elems in [n-bLen, n)
        for (int i = n - bLen; i < n; ++i) {
            if (!blacklistSet.contains(i)) {  //i not in blacklist
                wRight.add(i);
            }
        }

        for (int i = 0; i < bLeft.size(); ++i) { //create mapping; num blacks in left = num whites in right
            mapping.put(bLeft.get(i), wRight.get(i));
        }
    }

    public int pick() {
        int rand = r.nextInt(n - bLen);
        return mapping.getOrDefault(rand, rand); //if exists in mapping, return, else return rand itself

    }
}
