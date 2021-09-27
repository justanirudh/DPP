package com.anirudh.interview_prep_2021.two_sigma;

/*


Find the winner of the Game to Win by erasing any two consecutive similar alphabets
Difficulty Level : Medium
Last Updated : 02 Jun, 2021
Given a string consisting of lower case alphabets.
Rules of the Game:


A player can choose a pair of similar consecutive characters and erase them.
There are two players playing the game, the player who makes the last move wins.
The task is to find the winner if A goes first and both play optimally.
Examples:


Input: str = "kaak"
Output: B
Explanation:
    Initial String: "kaak"
    A's turn:
        removes: "aa"
        Remaining String: "kk"
    B's turn:
        removes: "kk"
        Remaining String: ""
    Since B was the last one to play
    B is the winner.

Input: str = "kk"
Output: A
 */

/*
Use a stack. whenever peek = next, pop and increment count
return count % 2;
 */
public class GameConsecutiveChars {
    //TODO
}
