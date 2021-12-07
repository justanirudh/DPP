package com.anirudh.companies_21_22.spotify;

import java.util.*;

/**
 * Created by paanir on 9/7/21.
 */
/*
1152. Analyze User Website Visit Pattern
Medium

222

2012

Add to List

Share
You are given two string arrays username and website and an integer array timestamp. All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]] indicates that the user username[i] visited the website website[i] at time timestamp[i].

A pattern is a list of three websites (not necessarily distinct).

For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and ["luffy", "luffy", "luffy"] are all patterns.
The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.

For example, if the pattern is ["home", "away", "love"], the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
Similarly, if the pattern is ["leetcode", "love", "leetcode"], the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
Also, if the pattern is ["luffy", "luffy", "luffy"], the score is the number of users x such that x visited "luffy" three different times at different timestamps.
Return the pattern with the largest score. If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.



Example 1:

Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
Output: ["home","about","career"]
Explanation: The tuples in this example are:
["joe","home",1],["joe","about",2],["joe","career",3],["james","home",4],["james","cart",5],["james","maps",6],["james","home",7],["mary","home",8],["mary","about",9], and ["mary","career",10].
The pattern ("home", "about", "career") has score 2 (joe and mary).
The pattern ("home", "cart", "maps") has score 1 (james).
The pattern ("home", "cart", "home") has score 1 (james).
The pattern ("home", "maps", "home") has score 1 (james).
The pattern ("cart", "maps", "home") has score 1 (james).
The pattern ("home", "home", "home") has score 0 (no user visited home 3 times).
Example 2:

Input: username = ["ua","ua","ua","ub","ub","ub"], timestamp = [1,2,3,4,5,6], website = ["a","b","a","a","b","c"]
Output: ["a","b","a"]
 */
/*
Comparator:compare is passed to an EMPTY list/queue to define how things will be sorted once inserted
Comparable: compareTo is part of the class. Put the object in Collections.sort to sort it the right way

Use brute force. For each user find all combinations by doing 3 loops O(n^3)
Then find the max frequented pattern
 */
public class AnalyzeUserWebsiteVisitPattern {

    class Visit {
        int ts;
        String web;

        Visit(int ts, String web) {
            this.ts = ts;
            this.web = web;
        }

    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Visit>> userHistory = new HashMap<>();

        for (int i = 0; i < username.length; ++i) { //add elems
            if (!userHistory.containsKey(username[i]))
                userHistory.put(username[i], new ArrayList<>());
            userHistory.get(username[i]).add(new Visit(timestamp[i], website[i]));
        }

        Map<String, Integer> freq = new HashMap<>();

        String res = "";
        for (List<Visit> visits : userHistory.values()) {
            Set<String> visited = new HashSet<>(); // same 3 strings should not be visited by 1 user
            if (visits.size() < 3)
                continue;
            visits.sort(Comparator.comparingInt(o -> o.ts));
            for (int i = 0; i < visits.size(); ++i) { //all combos
                for (int j = i + 1; j < visits.size(); ++j) {
                    for (int k = j + 1; k < visits.size(); ++k) {
                        StringJoiner sj = new StringJoiner(",");
                        sj.add(visits.get(i).web);
                        sj.add(visits.get(j).web);
                        sj.add(visits.get(k).web);
                        String pattern = sj.toString();
                        if (!visited.contains(pattern)) {
                            freq.put(pattern, freq.getOrDefault(pattern, 0) + 1);
                            visited.add(pattern);
                        }
                        if (res.equals("") || //first time
                                freq.get(pattern) > freq.get(res) || //if we get a pattern with more frequency
                                (freq.get(pattern) == freq.get(res) && pattern.compareTo(res) < 0)) { //we get a pattern with same freq but lexi smaller
                            res = pattern;
                        }
                    }
                }
            }
        }
        return Arrays.asList(res.split(","));
    }
}
