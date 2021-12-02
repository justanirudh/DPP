package com.anirudh.interview_prep_2021_2022.spotify;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by paanir on 9/6/21.
 */

/*
286. Walls and Gates
Medium

1818

27

Add to List

Share
You are given an m x n grid rooms initialized with these three possible values.

-1 A wall or an obstacle.
0 A gate.
INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.



Example 1:


Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
Example 2:

Input: rooms = [[-1]]
Output: [[-1]]
Example 3:

Input: rooms = [[2147483647]]
Output: [[2147483647]]
Example 4:

Input: rooms = [[0]]
Output: [[0]]
 */

/*
Option 1: For each node do BFS. So basically BFS, in a DFS loop.
Because we are doing a BFS queue in a DFS sort of going to all nodes: O(m^2 * n^2)

Option 2: All gates are "sources". Do BFS for all of them together!
Put all of them in a queue and start BFS
Thing to note: we are finding shortest distance from each gate: rooms[x][y] = rooms[loc.x][loc.y] + 1;
 */

public class WallsandGates {

    class Location {
        int x;
        int y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        //equals
        //hashcode
        //toString
    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {-1, 1, 0, 0};
    int[][] rooms;

    boolean isValid(int x, int y) {
        return x >= 0 && x < rooms.length && y >= 0 && y < rooms[0].length;
    }

    public void wallsAndGates(int[][] rooms) {

        this.rooms = rooms;
        Deque<Location> queue = new ArrayDeque<>();

        for (int i = 0; i < rooms.length; ++i) { //put gates in queue
            for (int j = 0; j < rooms[0].length; ++j) {
                if (rooms[i][j] == 0) { //offer gates into queue
                    queue.offer(new Location(i, j));
                }
            }
        }

        while (!queue.isEmpty()) { //BFS in a matrix to find shortest distance
            Location loc = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int x = loc.x + dx[i];
                int y = loc.y + dy[i];
                if (isValid(x, y) && rooms[x][y] == Integer.MAX_VALUE) { //using INF value as "visited"
                    rooms[x][y] = rooms[loc.x][loc.y] + 1; //distance at gates is conviniently assigned 0 already
                    queue.offer(new Location(x,y));
                }
            }
        }

    }
}
