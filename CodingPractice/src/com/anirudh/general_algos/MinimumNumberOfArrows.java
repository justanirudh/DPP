package com.anirudh.general_algos;

import java.util.Arrays;

/**
 * Created by paanir on 4/2/17.
 */
//partially implemented
public class MinimumNumberOfArrows {

    public int findMinArrowShots(int[][] points) {
        if (points.length == 0)
            return 0;
        //sorted points by starting x
        Arrays.sort(points, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int ret = Integer.compare(a[0], b[0]);
                if (ret != 0)
                    return ret;
                else
                    return Integer.compare(a[1], b[1]);
            }
        });
        for (int[] ar : points) {
            System.out.println(ar[0] + "," + ar[1]);
        }
        //Greedy approach
        int startBalloonIndex = 0;
        int numArrows = 0;
        int balloonNum = 0;
        while (balloonNum < points.length) {
            int endingIndex = points[startBalloonIndex][1];
            while (balloonNum < points.length && points[balloonNum][0] <= endingIndex) {
                balloonNum++;
            }
            numArrows++;
            if (balloonNum == points.length)
                break;
            startBalloonIndex = balloonNum;
        }
        return numArrows;
    }
}
