package com.anirudh.general_algos;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by paanir on 5/9/17.
 */
/*
341. Flatten Nested List Iterator
Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 */
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

        Stack<Iterator<NestedInteger>> stack;
        NestedInteger currInt;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            stack.push(nestedList.iterator());
        }

        private Iterator<NestedInteger> getTop() {
            if (stack.isEmpty())
                return null;
            else
                return stack.peek();
        }

        @Override
        public Integer next() { //will always be integer
            return currInt.getInteger();
        }

        @Override
        public boolean hasNext() { //removes all fluff till an int is found
            Iterator<NestedInteger> curr = getTop();
            if (curr == null) //stack empty, return false
                return false;
            if (curr.hasNext()) {
                NestedInteger elem = curr.next();
                if (elem.isInteger()) { //integer
                    currInt = elem;
                    return true;
                } else { //list
                    List<NestedInteger> l = elem.getList();
                    stack.push(l.iterator());
                    return hasNext();
                }
            } else { //go to list below it in stack
                stack.pop();
                return hasNext();
            }
        }
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
