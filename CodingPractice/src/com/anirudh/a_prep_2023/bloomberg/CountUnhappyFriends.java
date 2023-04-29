package com.anirudh.a_prep_2023.bloomberg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
1583. Count Unhappy Friends
Medium
245
776
company
Bloomberg
You are given a list of preferences for n friends, where n is always even.

For each person i, preferences[i] contains a list of friends sorted in the order of preference. In other words, a friend earlier in the list is more preferred than a friend later in the list. Friends in each list are denoted by integers from 0 to n-1.

All the friends are divided into pairs. The pairings are given in a list pairs, where pairs[i] = [xi, yi] denotes xi is paired with yi and yi is paired with xi.

However, this pairing may cause some of the friends to be unhappy. A friend x is unhappy if x is paired with y and there exists a friend u who is paired with v but:

x prefers u over y, and
u prefers x over v.
Return the number of unhappy friends.



Example 1:

Input: n = 4, preferences = [[1, 2, 3], [3, 2, 0], [3, 1, 0], [1, 2, 0]], pairs = [[0, 1], [2, 3]]
Output: 2
Explanation:
Friend 1 is unhappy because:
- 1 is paired with 0 but prefers 3 over 0, and
- 3 prefers 1 over 2.
Friend 3 is unhappy because:
- 3 is paired with 2 but prefers 1 over 2, and
- 1 prefers 3 over 0.
Friends 0 and 2 are happy.
Example 2:

Input: n = 2, preferences = [[1], [0]], pairs = [[1, 0]]
Output: 0
Explanation: Both friends 0 and 1 are happy.
Example 3:

Input: n = 4, preferences = [[1, 3, 2], [2, 3, 0], [1, 3, 0], [0, 2, 1]], pairs = [[1, 3], [0, 2]]
Output: 4
 */
public class CountUnhappyFriends {

    private int findIndex(int[] arr, int elem) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == elem)
                return i;
        }
        return -1; //shouldnt happen as we have full pref list for each person
    }

    private boolean isPersonHighPri(int[] prefsFOP, int pairedWith, int person) {
        for (int friend : prefsFOP) {
            if (friend == person)
                return true;
            else if (friend == pairedWith)
                return false;
        }
        return true; //shouldnt happen as we have full pref list for each person
    }

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        Map<Integer, Integer> pM = new HashMap<>();
        Set<Integer> unHappy = new HashSet<>();
        for (int[] pair : pairs) {
            pM.put(pair[0], pair[1]);
            pM.put(pair[1], pair[0]);
        }
        for (int person : pM.keySet()) {
            if (unHappy.contains(person))
                continue;
            int other = pM.get(person);
            int[] prefsPerson = preferences[person];
            int idxOther = findIndex(prefsPerson, other);
            if (idxOther == 0) { //means person's first choice, person is happy
                continue;
            } else {
                //go from 0 to idxOther-1, and for each "other", check if
                // they are paired with someone whos priority is less than friend's
                for (int i = 0; i < idxOther; ++i) {
                    int friendOfPerson = prefsPerson[i];
                    int[] prefsFOP = preferences[friendOfPerson];
                    int pairedWith = pM.get(friendOfPerson);
                    boolean personIsHighPri = isPersonHighPri(prefsFOP, pairedWith, person);
                    if (personIsHighPri) {
                        unHappy.add(person);
                        unHappy.add(friendOfPerson);
                        break;
                    }
                }
            }
        }
        return unHappy.size();
    }
}
