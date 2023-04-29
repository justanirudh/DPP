package com.anirudh.a_prep_2023.bloomberg;

/*
1274. Number of Ships in a Rectangle
Hard
480
53
company
Bloomberg
(This problem is an interactive problem.)

Each ship is located at an integer point on the sea represented by a cartesian plane, and each integer point may contain at most 1 ship.

You have a function Sea.hasShips(topRight, bottomLeft) which takes two points as arguments and returns true If there is at least one ship in the rectangle represented by the two points, including on the boundary.

Given two points: the top right and bottom left corners of a rectangle, return the number of ships present in that rectangle. It is guaranteed that there are at most 10 ships in that rectangle.

Submissions making more than 400 calls to hasShips will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.



Example :


Input:
ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
Output: 3
Explanation: From [0,0] to [4,4] we can count 3 ships within the range.
Example 2:

Input: ans = [[1,1],[2,2],[3,3]], topRight = [1000,1000], bottomLeft = [0,0]
Output: 3


Constraints:

On the input ships is only given to initialize the map internally. You must solve this problem "blindfolded". In other words, you must find the answer using the given hasShips API, without knowing the ships position.
0 <= bottomLeft[0] <= topRight[0] <= 1000
0 <= bottomLeft[1] <= topRight[1] <= 1000
topRight != bottomLeft
 */

/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *     public boolean hasShips(int[] topRight, int[] bottomLeft);
 * }
 */

 /*
 Do Binary Search, divide each rectangle into 4 rectangles and search in them
 How max 400 reqs is enough?
    Height of binary tree = log4(1000*1000) = 10
    In each level, atmost 10 rectangles will be non-empty (since give constraint that max 10 ships can be present). Other rectangles will be dropped
    Each of these 10 rectangles at a level will be divided into 4 sub-rectangles
    meaning max 40 rectangles/40 calls per level
    meaning total 40 * 10levels = 400 calls

 */

class NumberofShipsinaRectangle  {

    abstract static class Sea {
      public abstract boolean hasShips(int[] topRight, int[] bottomLeft);
  }

    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        //see if valid rectangle
        if(bottomLeft[0] > topRight[0] || bottomLeft[1] > topRight[1])
            return 0;

        //see if it has ships
        if(!sea.hasShips(topRight, bottomLeft))
            return 0;

        //Found solution
        if(bottomLeft[0] == topRight[0] && bottomLeft[1] == topRight[1]) {
            return 1;
        }

        int midX = (topRight[0] + bottomLeft[0])/2;
        int midY = (topRight[1] + bottomLeft[1])/2;
        int x = bottomLeft[0];
        int y = bottomLeft[1];
        int i = topRight[0];
        int j = topRight[1];

        //Divide into 4 rectangles

        return countShips(sea, new int[]{midX, midY}, bottomLeft) +
                countShips(sea, topRight, new int[]{midX+1, midY+1}) +
                countShips(sea, new int[]{midX, j}, new int[]{x , midY+1}) +
                countShips(sea,new int[]{i, midY}, new int[]{midX+1, y});

    }
}
