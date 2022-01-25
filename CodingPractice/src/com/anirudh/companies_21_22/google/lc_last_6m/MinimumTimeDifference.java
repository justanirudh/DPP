package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
539. Minimum Time Difference
Medium

862

187

Add to List

Share
Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.


Example 1:

Input: timePoints = ["23:59","00:00"]
Output: 1
Example 2:

Input: timePoints = ["00:00","23:59","00:00"]
Output: 0


Constraints:

2 <= timePoints.length <= 2 * 104
timePoints[i] is in the format "HH:MM".
Accepted
72,192
Submissions

 */
/*

Option 1:
1. convert each time to a value by doing {60*hh + mm}
2. sort normally
3. add list(0) + (60*24) to the list for loop-around
4. find min-diff normally
https://leetcode.com/problems/minimum-time-difference/discuss/100636/Java-10-liner-solution.-Simplest-so-far

Option 2: complex
1. sort them by hh then by mm
    create custom comparator
2. see diff of each adjacent value
    add first value + 24 to last for ease of loop-around
    Make 4 cases to compare times:
        2:00, 5:00
        2:00, 5:10
        2:15, 5:00
        2:15, 5:10

Tx: O(nlogn)
 */
public class MinimumTimeDifference {

    //Option 2
    static class CompareElems implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            if(a[0] != b[0]) //hh is not same
                return a[0] - b[0];
            else { //hh is same, based on min
                return a[1] - b[1];
            }
        }
    }
    public int findMinDifference(List<String> timePoints) {
        List <int[]> sortedTimePoints = timePoints.stream()
                .map(x -> x.split(":"))
                .map(x -> {
                    int a = Integer.parseInt(x[0]);
                    int b = Integer.parseInt(x[1]);
                    int[] arr = new int[2];
                    arr[0] = a;
                    arr[1] = b;
                    return arr;
                })
                .sorted(new CompareElems())
                .collect(Collectors.toList());

        //add (first + 24) at the end  to do last comparison
        int[] last = new int[2];
        last[0] = sortedTimePoints.get(0)[0] + 24;
        last[1] = sortedTimePoints.get(0)[1];
        sortedTimePoints.add(last);

        int diff = Integer.MAX_VALUE;
        for(int i = 0; i < sortedTimePoints.size() - 1; ++i) {
            int[] first = sortedTimePoints.get(i);
            int[] second = sortedTimePoints.get(i + 1);
            int hh1 = first[0];
            int mm1 = first[1];
            int hh2 = second[0];
            int mm2 = second[1];
            if(mm1 == 0 && mm2 == 0) { //2:00 and 5:00
                diff = Math.min(diff, (hh2 - hh1) * 60);
            }
            else if(mm1 == 0) { //2:00 and 5:15
                diff = Math.min(diff, (hh2 - hh1) * 60 + mm2);
            }
            else if (mm2 == 0) { // 2: 15 and 5:00
                diff = Math.min(diff, (hh2 - hh1 - 1) * 60 + (60 - mm1));
            }
            else { //2:15 and 5:10
                diff = Math.min(diff, (hh2 - hh1 - 1) * 60 + (60 - mm1) + mm2);
            }
        }
        return diff;
    }
}
