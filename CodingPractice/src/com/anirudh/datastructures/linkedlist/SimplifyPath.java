package com.anirudh.datastructures.linkedlist;

import com.anirudh.general_algos.basics.StringStack;

import java.util.ArrayList;

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

    public static String removeRedundantAndLastSlashes(String path) {
        //removing redundant slashes
        StringBuilder pathNoredslash = new StringBuilder();
        int i = 0;
        while (i < path.length()) {
            char curr = path.charAt(i);
            pathNoredslash.append(curr);
            if (curr == '/') {
                while (curr == '/') {
                    i = i + 1;
                    if (i < path.length())
                        curr = path.charAt(i);
                    else
                        break;
                }
            } else
                i = i + 1;
        }

        //remove trailing slashes
        if (pathNoredslash.length() == 1) // is /
            return "/";
        else if (pathNoredslash.charAt(pathNoredslash.length() - 1) == '/')
            pathNoredslash.deleteCharAt(pathNoredslash.length() - 1); //remove last /
        return pathNoredslash.deleteCharAt(0).toString(); //remove first /
    }

    public static String simplifyPath(String path) {
        if (path.equals("/"))
            return "/";

        String pathNoRedslash = removeRedundantAndLastSlashes(path);

        if (pathNoRedslash.equals("/"))
            return "/";

        String[] dirs = pathNoRedslash.split("/"); //stack it up
        StringStack stack = new StringStack();
        for (String s : dirs) {
            if (s.equals("."))
                continue;
            else if (s.equals("..")) {
                if (stack.isEmpty())
                    continue;
                else
                    stack.pop();
            } else
                stack.push(s);
        }

        ArrayList<String> finalPath = new ArrayList<>(); //just convert stack into a valid path
        while (true) {
            String s = stack.pop();
            if (s == null)
                break;
            else
                finalPath.add(0, s);
        }

        return "/" + String.join("/", finalPath);
    }

    public static void main(String[] args) {

        String path = "/../";
        System.out.println(simplifyPath(path));
    }
}
