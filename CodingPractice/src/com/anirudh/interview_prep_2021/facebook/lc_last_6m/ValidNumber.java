package com.anirudh.interview_prep_2021.facebook.lc_last_6m;

/**
 * Created by paanir on 8/30/21.
 */
/*
65. Valid Number
Hard

227

470

Add to List

Share
A valid number can be split up into these components (in order):

A decimal number or an integer.
(Optional) An 'e' or 'E', followed by an integer.
A decimal number can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One of the following formats:
One or more digits, followed by a dot '.'.
One or more digits, followed by a dot '.', followed by one or more digits.
A dot '.', followed by one or more digits.
An integer can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One or more digits.
For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].

Given a string number, return true if number is a valid number.

Example 1:

Input: number = "0"
Output: true
Example 2:

Input: number = "e"
Output: false
Example 3:

Input: number = "."
Output: false
Example 4:

Input: number = ".1"
Output: true
 */

import java.util.*;

/**
 * 1. Create a state machine (DFA) on paper
 * 2. Represent the state machine as this: List<Map<String, Integer>> stateMachine
 * 2.a. Each state is an index of the arraylist
 * 2.b. At each state, there is a map from { transition -> next state }
 * E.g. {at A, we see x, we transition to B } will be at [index A, {x -> b}]
 * 3. Final states are in a hashset
 * 4. Start iterating through String until you hit end
 * 5. check if end state is one of the final states
 */
public class ValidNumber {

    public boolean isNumber(String s) {

        //INITIATE STATE MACHINE

        List<Map<String, Integer>> stateMachine = new ArrayList<>();

        Map<String, Integer> transitions0 = new HashMap<>();
        transitions0.put("digit", 1);
        transitions0.put("sign", 2);
        transitions0.put("dot", 3);
        stateMachine.add(transitions0);

        Map<String, Integer> transitions1 = new HashMap<>();
        transitions1.put("digit", 1);
        transitions1.put("dot", 4);
        transitions1.put("expo", 5);
        stateMachine.add(transitions1);

        Map<String, Integer> transitions2 = new HashMap<>();
        transitions2.put("digit", 1);
        transitions2.put("dot", 3);
        stateMachine.add(transitions2);

        Map<String, Integer> transitions3 = new HashMap<>();
        transitions3.put("digit", 4);
        stateMachine.add(transitions3);

        Map<String, Integer> transitions4 = new HashMap<>();
        transitions4.put("digit", 4);
        transitions4.put("expo", 5);
        stateMachine.add(transitions4);

        Map<String, Integer> transitions5 = new HashMap<>();
        transitions5.put("digit", 7);
        transitions5.put("sign", 6);
        stateMachine.add(transitions5);

        Map<String, Integer> transitions6 = new HashMap<>();
        transitions6.put("digit", 7);
        stateMachine.add(transitions6);

        Map<String, Integer> transitions7 = new HashMap<>();
        transitions7.put("digit", 7);
        stateMachine.add(transitions7);

        Set<Integer> terminalStates = new HashSet<>();
        terminalStates.add(1);
        terminalStates.add(4);
        terminalStates.add(7);

        //START STRING PARSING, iterative

        int currState = 0; //this tells us which index in the list we should look at
        for(char c : s.toCharArray()) {

            String type; //this gives us which key-value to look at in a state
            if(Character.isDigit(c))
                type = "digit";
            else if (c == '+' || c == '-')
                type = "sign";
            else if (c == '.')
                type = "dot";
            else if (c == 'e' || c == 'E')
                type = "expo";
            else
                return false;

            if(!stateMachine.get(currState).containsKey(type)) { //if there is no transition to next state
                return false;
            }

            currState = stateMachine.get(currState).get(type); //new state

        }

        return terminalStates.contains(currState); //if the last state is one of the terminal states

    }
}
