package com.anirudh.companies_21_22.google.lc_last_6m;

/*
305. Number of Islands II
Hard

1313

39

Add to List

Share
You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. Initially, all the cells of grid are water cells (i.e., all the cells are 0's).

We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.

Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:


Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
Output: [1,1,2,3]
Explanation:
Initially, the 2d grid is filled with water.
- Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
- Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
- Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
- Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
Example 2:

Input: m = 1, n = 1, positions = [[0,0]]
Output: [1]
 */

import java.util.*;

/*
Approach 1:
Have a Map {coordinate -> island_id}
For each new cell that is converted into land,
    check its 4 neighbours in the map
    create a set of island_ids for the neighbours
if the set is empty, new island create. res[i] = ++num_islands; //increment and get
if the set has 1 elem, new coord is part of 1 island. res[i] = map.get(neighbour)
if set has multiple elems,
    coord bridging multiple islands
    select 1 island_id from the set: ID (neighbours.iterator().next())
    For each value in map that is contained in the set, convert it to ID
    res[i] = num_islands - (set#size - 1); //old num of islands - num_islands that were purged

Tx: N^2 as for each cell we might need to go over all the other cells

Approach2: Do DFS after adding each land. T: N*mn
 */
public class NumberofIslandsII {

    boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Map<List<Integer>, Integer> islandIDMap = new HashMap<>(); //coordinate -> islandID
        Set<List<Integer>> positionsSet = new HashSet<>();
        int currIslands = 0;
        for (int[] position : positions) {
            List<Integer> positionL = Arrays.asList(position[0], position[1]);
            if (positionsSet.contains(positionL)) { //if putting land on land, num_islands remain the same
                res.add(res.get(res.size() - 1));
                continue;
            }
            positionsSet.add(positionL);
            Set<Integer> neighbourIslandIds = new HashSet<>(); //SHOULD be a Set so that duplicate islandIds are deduped
            for (int i = 0; i < 4; ++i) { // go thru neighbours and find islandIds
                int x = position[0] + dx[i];
                int y = position[1] + dy[i];
                List<Integer> coords = Arrays.asList(x, y);
                if (isValid(x, y, m, n) && islandIDMap.containsKey(coords)) {
                    neighbourIslandIds.add(islandIDMap.get(coords));
                }
            }
            if (neighbourIslandIds.isEmpty()) { //new cell is new island
                currIslands++;
                islandIDMap.put(positionL, currIslands);
            } else if (neighbourIslandIds.size() == 1) { //new cell is part of an existing island
                int neighbourId = neighbourIslandIds.iterator().next();
                islandIDMap.put(positionL, neighbourId);
            } else { //new cell is part of multiple islands; it is a bridging cell
                int commonId = neighbourIslandIds.iterator().next();
                for (List<Integer> key : islandIDMap.keySet()) {
                    if (neighbourIslandIds.contains(islandIDMap.get(key))) {
                        islandIDMap.put(key, commonId);
                    }
                }
                islandIDMap.put(positionL, commonId);
                currIslands = currIslands - (neighbourIslandIds.size() - 1);
            }
            res.add(currIslands);
        }
        return res;
    }
}
