package com.anirudh.a_prep_2023.bloomberg;
import java.util.*;

/*
1244. Design A Leaderboard
Medium
683
88
company
Bloomberg
company
Google
company
Amazon
Design a Leaderboard class, which has 3 functions:

addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
top(K): Return the score sum of the top K players.
reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.
Initially, the leaderboard is empty.



Example 1:

Input:
["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
[[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
Output:
[null,null,null,null,null,null,73,null,null,null,141]

Explanation:
Leaderboard leaderboard = new Leaderboard ();
leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
leaderboard.top(1);           // returns 73;
leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
leaderboard.top(3);           // returns 141 = 51 + 51 + 39;


Constraints:

1 <= playerId, K <= 10000
It's guaranteed that K is less than or equal to the current number of players.
1 <= score <= 100
There will be at most 1000 function calls.
 */

/*
Maintain 2 maps:
map 1: Hashmap: ids -> score
map 2: Reverse sorted TreeMap: score -> {ids}

addScore: add or update mapping in 1 and 2
top: iterate through map#2 and add top K, make sure to count multiple ids with same scores
reset: remove from 1 and 2
 */
class DesignALeaderboard {

    Map<Integer, Integer> idToScore;
    Map<Integer, Set<Integer>> scoreToIds;

    public DesignALeaderboard() {
        idToScore = new HashMap<>();
        scoreToIds = new TreeMap<>(Comparator.reverseOrder());

    }

    public void addScore(int playerId, int score) { //O(logn)
        int newScore = score;
        if(idToScore.containsKey(playerId)) { //remove previous, add new
            int oldScore = idToScore.get(playerId);
            newScore = oldScore + score;
            scoreToIds.get(oldScore).remove(playerId); //remove old in map#2
        }
        idToScore.put(playerId, newScore); //remove old put new in map#1
        scoreToIds.putIfAbsent(newScore, new HashSet<>());
        scoreToIds.get(newScore).add(playerId); //put new in map #2

    }

    public int top(int K) { //O(K)
        int count = K;
        int sum = 0;
        for(int score : scoreToIds.keySet()) {
            int numIds = scoreToIds.get(score).size();
            for(int i = 0; i < numIds; ++i) {
                sum +=score;
                count--;
                if(count == 0)
                    break;
            }
            if (count == 0)
                break;
        }
        return sum;
    }

    public void reset(int playerId) { //O(logn)
        int score = idToScore.get(playerId);
        scoreToIds.get(score).remove(playerId);
        if(scoreToIds.get(score).isEmpty())
            scoreToIds.remove(score);
        idToScore.remove(playerId);
    }
}
