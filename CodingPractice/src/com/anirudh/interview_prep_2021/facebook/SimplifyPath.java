package com.anirudh.interview_prep_2021.facebook;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringJoiner;

/**
 * Created by paanir on 12/22/16.
 */

/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class SimplifyPath {

    String simplifyPath(String path) {
        String[] dirs = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String dir : dirs) {
            if (!dir.isEmpty()) {
                switch (dir) {
                    case ".":
                        break;
                    case "..":
                        if (!stack.isEmpty())
                            stack.removeLast();
                        break;
                    default:
                        stack.addLast(dir);
                }
            }
        }
        StringJoiner res = new StringJoiner("/");
        while (stack.size() != 0) {
            res.add(stack.removeFirst());
        }
        return "/" + res;
    }

    public static void main(String[] args) {
//        String[] dirs = "/baz/foo/bar/".split("/");
//        System.out.println(dirs.length);
//        for (String dir : dirs) {
//            System.out.println(dir);
//        }
        SimplifyPath sp = new SimplifyPath();
        System.out.println(sp.simplifyPath("/a/./b/../../c/"));
    }
}
