package com.anirudh.companies_21_22.google.lc_last_6m.anki;
/*
990. Satisfiability of Equality Equations
Medium

1310

14

Add to List

Share
You are given an array of strings equations that represent relationships between variables where each string equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.

Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.



Example 1:

Input: equations = ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
There is no way to assign the variables to satisfy both equations.
Example 2:

Input: equations = ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.


Constraints:

1 <= equations.length <= 500
equations[i].length == 4
equations[i][0] is a lowercase letter.
equations[i][1] is either '=' or '!'.
equations[i][2] is '='.
equations[i][3] is a lowercase letter.
 */

import java.util.*;

/*
1. Go thru == conditions and create graph
2. Do DFS to find connected comps and color them in Map{variable : color}
3. Go thru != conditions and make sure they are of diff colors

Edge cases:
1. for a==a, ignore
2. got x!=y s.t there are no equality conditions for either one of them, return as true
3. if x!=x, return false
 */
public class SatisfiabilityofEqualityEquations {

    void doDFS(String var, int color, Map<String, List<String>> graph, Map<String, Integer> varToColor, Set<String> visited) {
        visited.add(var);
        varToColor.put(var, color);
        for (String n : graph.get(var)) {
            if (!visited.contains(n))
                doDFS(n, color, graph, varToColor, visited);
        }
    }

    public boolean equationsPossible(String[] equations) {
        Map<String, List<String>> graph = new HashMap<>();
        for (String eq : equations) { //create graph
            if (eq.contains("==")) {
                String[] nodes = eq.split("==");
                if (nodes[0].equals(nodes[1]))
                    continue;
                graph.putIfAbsent(nodes[0], new ArrayList<>());
                graph.get(nodes[0]).add(nodes[1]);
                graph.putIfAbsent(nodes[1], new ArrayList<>());
                graph.get(nodes[1]).add(nodes[0]);
            } else {
                String[] nodes = eq.split("!=");
                if (nodes[0].equals(nodes[1])) // a != a
                    return false;
            }
        }

        Map<String, Integer> varToColor = new HashMap<>(); //do DFS
        Set<String> visited = new HashSet<>();
        int color = 0;
        for (String var : graph.keySet()) {
            if (!visited.contains(var)) {
                doDFS(var, color, graph, varToColor, visited);
                color++;
            }
        }

        for (String eq : equations) { //check same color or not for != equations
            if (eq.contains("!=")) {
                String[] vars = eq.split("!=");
                int color0 = varToColor.getOrDefault(vars[0], -1);
                int color1 = varToColor.getOrDefault(vars[1], -1);
                if (color0 == -1 || color1 == -1) //if either of them were not in == graph, then it is a valid equation
                    continue;
                if (color0 == color1) //should not be equal
                    return false;
            }
        }
        return true;

    }
}
