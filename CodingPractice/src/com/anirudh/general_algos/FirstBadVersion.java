package com.anirudh.general_algos;

/**
 * Created by paanir on 1/26/17.
 */
/*
278. First Bad Version   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 81006
Total Submissions: 331757
Difficulty: Easy
Contributors: Admin
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product
fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version.
You should minimize the number of calls to the API.

 */


public class FirstBadVersion {

    public boolean isBadVersion(int a) {
        return true;
    }

    public int badVersionAux(int start, int end) {
        int mid = start + (end - start) / 2;

        if (isBadVersion(mid)) {
            if (mid == 0 || !isBadVersion(mid - 1))
                return mid;
            else
                return badVersionAux(start, mid - 1);
        } else
            return badVersionAux(mid + 1, end);
    }

    public int firstBadVersion(int n) {
        return badVersionAux(1, n);
    }
}
