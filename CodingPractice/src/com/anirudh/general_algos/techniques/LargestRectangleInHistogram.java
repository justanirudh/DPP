package com.anirudh.general_algos.techniques;

import java.util.Stack;

/**
 * Created by paanir on 1/16/17.
 */

//http://www.geeksforgeeks.org/largest-rectangle-under-histogram

/*
84. Largest Rectangle in Histogram
Given n non-negative integers representing the histogram's bar heights where the width of each bar is 1,
find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given heights = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given heightss = [2,1,5,6,2,3],
return 10.
     */
public class LargestRectangleInHistogram {

    public static int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0)
            return 0;

        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int maxArea = 0;
        while (i < heights.length) {
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()])
                stack.push(i++); //if more than previous element in stack, push
            else { //calculate area considering the top element as smallest element
                //calculate max value when the current height is less than the previous one
                int top = heights[stack.pop()];
                int width;
                if (stack.isEmpty())
                    width = i; //full width from 0 to i
                else
                    width = i - stack.peek() - 1;
                int area = top * width; //right (i) and left (stack.peek) elements are smaller than top
                if (area > maxArea)
                    maxArea = area;
            }
        }
        //empty the rest of the stack
        while (!stack.isEmpty()) {
            int top = heights[stack.pop()]; //i will remain constant for rest of elems
            int width;
            if (stack.isEmpty())
                width = i; //full width from 0 to i
            else
                width = i - stack.peek() - 1;
            int area = top * width; //right (i) and left (stack.peek) elements are smaller than top
            if (area > maxArea)
                maxArea = area;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
