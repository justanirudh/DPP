package com.anirudh.datastructures.graphs;

import java.util.*;

/**
 * Created by paanir on 8/30/21.
 */
/*
721. Accounts Merge
Medium

2892

513

Add to List

Share
Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:

Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Example 2:

Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 */

/**
 * Connect all emails attached to a name to each other
 * Create a graph!
 * Do a DFS to find connected components!
 */
public class AccountsMerge {

    Map<String, List<String>> graph = new HashMap<>();
    Map<String, String> emailToName = new HashMap<>();
    Set<String> visited = new HashSet<>();


    List<String> doDFS(String email, List<String> connected) {
        connected.add(email);
        visited.add(email);

        for (String neighbour : graph.get(email)) {
            if (!visited.contains(neighbour)) {
                doDFS(neighbour, connected);
            }
        }

        return connected;
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        //CREATE GRAPH
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); ++i) { //add emails to graph, connect i to i + 1
                if (!graph.containsKey(account.get(i)))
                    graph.put(account.get(i), new ArrayList<>()); //email -> empty list

                emailToName.put(account.get(i), account.get(0)); //required later to find name; email -> name

                if (i + 1 < account.size()) { //if there is a next element, add to each other's vertex list
                    graph.get(account.get(i)).add(account.get(i + 1));
                    if (!graph.containsKey(account.get(i + 1)))
                        graph.put(account.get(i + 1), new ArrayList<>());
                    graph.get(account.get(i + 1)).add(account.get(i));
                }

            }
        }

        List<List<String>> res = new ArrayList<>();

        //DO DFS
        for (String email : graph.keySet()) {
            if (!visited.contains(email)) {
                List<String> connectedEmails = doDFS(email, new ArrayList<>());
                String name = emailToName.get(connectedEmails.get(0));
                Collections.sort(connectedEmails);
                connectedEmails.add(0, name);
                res.add(connectedEmails);
            }
        }
        return res;
    }
}