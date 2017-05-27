package com.anirudh.datastructures.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by paanir on 5/28/17.
 */
//Leetcode #332. Under-construction
public class ReconstructItinerary {

    public class Solution {

        List<String> addSortedly(List<String> list, String elem) {
            //TODO: naive impl
            list.add(elem);
            Collections.sort(list);
            return list;
        }


        public List<String> findItinerary(String[][] tickets) {
            HashMap<String, List<String>> ticketMap = new HashMap<>();

            //created an adjacency list
            for (String[] ticket : tickets) {
                String from = ticket[0];
                List<String> list;
                if (!ticketMap.containsKey(from)) {
                    list = new ArrayList<>();
                    list.add(ticket[1]);
                } else
                    list = addSortedly(ticketMap.get(from), ticket[1]);
                ticketMap.put(from, list);
            }


            String from = "JFK";
            List<String> itin = new ArrayList<>();
            itin.add(from);
            while (!ticketMap.isEmpty()) {

                for (String key : ticketMap.keySet()) {
                    System.out.print(key + " -> ");
                    for (String to : ticketMap.get(key)) {
                        System.out.print(to + ", ");
                    }
                    System.out.println();
                }
                System.out.println("-------------");

                List<String> tos = ticketMap.get(from);
                String newFrom = "";
                for (String to : tos) {
                    if (ticketMap.containsKey(to)) {
                        newFrom = to; //it will be the new from
                        tos.remove(to); //rmeove it from the current from's tos list
                        if (tos.isEmpty()) //if tos is empty, remove from too
                            ticketMap.remove(from);
                        break;
                    }
                }
                if (newFrom == "") {
                    // ticketMap.remove(from);
                    from = tos.get(0);
                    itin.add(from);
                    break;
                } else {
                    from = newFrom;
                    itin.add(from);
                }
                System.out.println(from);
            }
            return itin;
        }
    }
}
