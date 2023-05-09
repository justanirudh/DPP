package com.anirudh.a_prep_2023.tiktok;
import java.util.*;

/*
490. The Maze
Medium
1.6K
169
company
Square
company
TikTok
company
Amazon
There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.

You may assume that the borders of the maze are all walls (see examples).



Example 1:


Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
Example 2:


Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
Output: false
Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.
Example 3:

Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
Output: false
 */

/*
Do BFS
for each dx, dy, need to keep going in that direction until find 1 or hit a wall,
that will be the neighbour instead of immiedeiate cell
 */
public class TheMaze {
    boolean isValid(int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length;
    }

    int[][] maze;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    //BFS
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        this.maze = maze;
        Queue<List<Integer>> q = new ArrayDeque<>();
        Set<List<Integer>> visited = new HashSet<>();
        q.offer(Arrays.asList(start[0], start[1]));
        visited.add(Arrays.asList(start[0], start[1]));
        while (!q.isEmpty()) {
            List<Integer> curr = q.poll();
            int x = curr.get(0);
            int y = curr.get(1);
            List<Integer> dest = Arrays.asList(destination[0], destination[1]);
            for (int i = 0; i < 4; ++i) {
                int xx = x + dx[i];
                int yy = y + dy[i];
                while (isValid(xx, yy) && maze[xx][yy] != 1) {
                    xx += dx[i];
                    yy += dy[i];
                }
                List<Integer> end = Arrays.asList(xx - dx[i], yy - dy[i]);
                if (end.equals(dest))
                    return true;
                if (!visited.contains(end)) {
                    q.offer(end);
                    visited.add(end);
                }
            }
        }
        return false;
    }
}
