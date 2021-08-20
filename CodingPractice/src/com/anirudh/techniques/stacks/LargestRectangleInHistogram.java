package com.anirudh.techniques.stacks;

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
Given heights = [2,1,5,6,2,3],
return 10.
     */
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxarea = 0;

        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]){
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        return maxarea;
    }

//    private int[] heights;
//    private Stack<Integer> stack;
//
//    private int getMaxArea(int index, int currMaxArea) {
//        int top = heights[stack.pop()];
//        int width = stack.isEmpty() ? index : index - stack.peek() - 1;  //full width from 0 to i if stack empty
//        return Math.max(top * width, currMaxArea);  //right (i) and left (stack.peek) elements are smaller than top
//    }
//
//    public int largestRectangleArea(int[] heights) {
//        if (heights == null || heights.length == 0)
//            return 0;
//
//        this.heights = heights;
//        stack = new Stack<>(); //stack has decreasing heights top to bottom
//        int i = 0;
//        int maxArea = 0;
//
//        while (i < heights.length) {
//            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {  //push if curr_elem > stack.peek
//                stack.push(i);
//                i++;
//            } else //calculate area considering the top element as smallest element
//                maxArea = getMaxArea(i, maxArea);
//        }
//
//        //empty the rest of the stack
//        while (!stack.isEmpty()) {
//            maxArea = getMaxArea(i, maxArea);  //i will remain constant for rest of elems
//        }
//
//        return maxArea;
//    }

    public static void main(String[] args) {
        System.out.println((new LargestRectangleInHistogram()).largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
