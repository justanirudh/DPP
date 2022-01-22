package com.anirudh.companies_21_22.facebook.lc_last_6m;

import java.util.*;

/*
498. Diagonal Traverse
Medium

1857

496

Add to List

Share
Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.



Example 1:


Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
Example 2:

Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]
 */
/*
Insight 1: sum of coords in a diagonal is the same
Insight 2: if sum is even, we need to go l to r; if odd, r to l
Make a TreeMap{i + j -> List<Coordinate(x,y)>}. treemap sorts on the basis of order of zig-zags
For each List<Coordinate>,
    if i+j = even; increase by 'y'
    if i+j == odd; decrease by 'y'

    Sx: m * n
    Tx: {mnlog(mn)} + {(m+n)*mlogm} ~ mnlog(mn)
 */
public class DiagonalTraverse {

    static class CompareCoords implements Comparator<int[]> {
        boolean isEven;

        CompareCoords(boolean isEven) {
            this.isEven = isEven;
        }

        public int compare(int[] a, int[] b) {
            if (isEven)
                return a[1] - b[1];//increase by y
            else
                return b[1] - a[1]; //decrease by y
        }
    }

    public int[] findDiagonalOrder(int[][] mat) {
        Map<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < mat.length; ++i) { //create treemap with increasing order of sum
            for (int j = 0; j < mat[0].length; ++j) {
                int[] coord = new int[2];
                coord[0] = i;
                coord[1] = j;
                int key = i + j;
                if (!map.containsKey(key))
                    map.put(key, new ArrayList<>());
                map.get(key).add(coord);
            }
        }
        int[] res = new int[mat.length * mat[0].length];
        int i = 0;
        for (Map.Entry<Integer, List<int[]>> entry : map.entrySet()) { //in increasing order of sum
            List<int[]> coords = entry.getValue();
            if (entry.getKey() % 2 == 0) { //sort based on direction
                coords.sort(new CompareCoords(true));
            } else {
                coords.sort(new CompareCoords(false));
            }
            int j = 0;
            while (j < coords.size()) {
                int[] coord = coords.get(j);
                res[i] = mat[coord[0]][coord[1]];
                i++;
                j++;
            }
        }

        return res;

    }
}
