package com.anirudh.interview_prep_2021.spotify;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by paanir on 8/27/21.
 */

/**
 * 973. K Closest Points to Origin
 Medium

 3657

 172

 Add to List

 Share
 Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

 The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

 You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).



 Example 1:


 Input: points = [[1,3],[-2,2]], k = 1
 Output: [[-2,2]]
 Explanation:
 The distance between (1, 3) and the origin is sqrt(10).
 The distance between (-2, 2) and the origin is sqrt(8).
 Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 Example 2:

 Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 Output: [[3,3],[-2,4]]
 Explanation: The answer [[-2,4],[3,3]] would also be accepted.


 Constraints:

 1 <= k <= points.length <= 104
 -104 < xi, yi < 104
 */

/**
 * Similar to 347. Top K Frequent Elements
 * create a max heap. kick out max item everytime heap hits size k
 * For closest distance, we are using max heap. So that we can kick off largest element in O(logk) time
 */

public class KClosestPointstoOrigin {
    class Point {
        int[] point;
        double dist;

        Point(int[] point, double dist) {
            this.point = point;
            this.dist = dist;
        }
    }

    class PointComparator implements Comparator<Point> {
        public int compare(Point a, Point b) {
            return (int) (b.dist - a.dist); //since max heap
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        Queue<Point> maxHeap = new PriorityQueue<>(new PointComparator());

        for (int[] point : points) {
            double dist = Math.pow(point[0], 2) + Math.pow(point[1], 2);
            maxHeap.offer(new Point(point, dist));
            if (maxHeap.size() > k) {
                maxHeap.poll(); //kick out the max element, hence using max heap
            }
        }
        int[][] res = new int[k][2];
        for (int i = 0; i < k; ++i) {
            res[i] = maxHeap.poll().point;
        }
        return res;
    }
}
