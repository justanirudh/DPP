package com.anirudh.math;

/**
 * Created by paanir on 1/5/17.
 */
/*
223. Rectangle Area
Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 */
public class ComputeArea {

    class Rectangle {
        int left;
        int right;
        int top;
        int bottom;
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {

        int area1 = Math.abs(C - A) * Math.abs(D - B);
        int area2 = Math.abs(G - E) * Math.abs(H - F);

        //2 rectangles: r1 and r2. Each having 4 lines as boundaries: left, right, top, bottom
        Rectangle r1 = new Rectangle();
        r1.left = A;
        r1.right = C;
        r1.top = D;
        r1.bottom = B;

        Rectangle r2 = new Rectangle();
        r2.left = E;
        r2.right = G;
        r2.top = H;
        r2.bottom = F;

        Rectangle common = new Rectangle();
        //taking max as rest of the rectangle will be on the right of 'left'. Hence the bigger one will potentially be a side of common
        common.left = Math.max(r1.left, r2.left);
        common.right = Math.min(r1.right, r2.right); //taking min as rest rectangle on the left of 'right'
        common.top = Math.min(r1.top, r2.top);
        common.bottom = Math.max(r1.bottom, r2.bottom);

        if (common.left < common.right && common.bottom < common.top) {  //common is a valid rectangle
            int commonArea = Math.abs(common.right - common.left) * Math.abs(common.top - common.bottom);
            return area1 + area2 - commonArea;
        } else
            return area1 + area2;
    }

    public static void main(String[] args) {

    }
}
