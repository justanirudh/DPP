package com.anirudh.general_algos;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by paanir on 5/9/17.
 */
//#341 under-construction
public class FlattenNestedListIterator {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {

        List<NestedInteger> nestedList;
        Stack<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.nestedList = nestedList;
            stack = new Stack<>();
//            stack.push();
        }

        @Override
        public Integer next() {
            // NestedInteger curr = super.next();

            // if(curr.isInteger()){
            //     return curr.getInteger();
            // }
            // else{ //is list
            //     List<NestedInteger> l = curr.getList();
            // }

        }

        @Override
        public boolean hasNext() {
            return stack.isEmpty();
            //boolean  super.hasNext();
//            if (stack.isEmpty()) {
//
//            } else
//                return true;
        }
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
