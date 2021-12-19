package com.anirudh.companies_21_22.facebook.lc_last_6m;

import java.util.*;

/**
 * Created by paanir on 8/31/21.
 */
/*
636. Exclusive Time of Functions
Medium

1227

1969

Add to List

Share
On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.

Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack, and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is the current function being executed. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.

You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}". For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple times, possibly recursively.

A function's exclusive time is the sum of execution times for all function calls in the program. For example, if a function is called twice, one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.

Return the exclusive time of each function in an array, where the value at the ith index represents the exclusive time for the function with ID i.



Example 1:


Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3,4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
Example 2:

Input: n = 1, logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
Output: [8]
Explanation:
Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
Function 0 (initial call) resumes execution then immediately calls itself again.
Function 0 (2nd recursive call) starts at the beginning of time 6 and executes for 1 unit of time.
Function 0 (initial call) resumes execution at the beginning of time 7 and executes for 1 unit of time.
So function 0 spends 2 + 4 + 1 + 1 = 8 units of total time executing.
Example 3:

Input: n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
Output: [7,1]
Explanation:
Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
Function 0 (initial call) resumes execution then immediately calls function 1.
Function 1 starts at the beginning of time 6, executes 1 units of time, and ends at the end of time 6.
Function 0 resumes execution at the beginning of time 6 and executes for 2 units of time.
So function 0 spends 2 + 4 + 1 = 7 units of total time executing, and function 1 spends 1 unit of total time executing.
Example 4:

Input: n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"]
Output: [8,1]
Example 5:

Input: n = 1, logs = ["0:start:0","0:end:0"]
Output: [1]
 */

/**
 * 1. Use a stack.
 * 2. If top of element is a start, and the new element is an end, it has to be that of the top element. Same logic as parens
 * 3. Catch here is this: To not maintain 8 different states between 2 adjacent elems in the stack (2 for same or different
 * pid * 4 of se, es, ss, ee), we do this:
 * a. If x is the time duration of the most recent pid_2 popped && there is already a pid_1 in the stack, subtract x from
 * the time duration of pid_1 proactively!
 * b. At the end when pid_1 gets closed, it will be a longer duration since it included all the pid durations between but the
 * proactive subtractions will take care of this!
 */
public class ExclusiveTimeofFunctions {

    class TimeStamp {
        int pid;
        boolean isStart;
        int timestamp;

        TimeStamp(String log) {
            String[] strs = log.split(":");
            this.pid = Integer.parseInt(strs[0]);
            this.isStart = strs[1].equals("start");
            this.timestamp = Integer.parseInt(strs[2]);
        }
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];

        //stack will ONLY have starts, same way parenthesis question has opening parens in stack
        Deque<TimeStamp> stack = new ArrayDeque<>();

        for (String log : logs) {
            TimeStamp ts = new TimeStamp(log);
            if (ts.isStart) { //if start, just push
                stack.push(ts);
            } else { // if end, top would be the start for sure of the same pid
                TimeStamp tsPrev = stack.pop();
                int duration = ts.timestamp - tsPrev.timestamp + 1;
                res[ts.pid] += duration;

                if (!stack.isEmpty()) { //subtract this from peek elem
                    res[stack.peek().pid] -= duration;
                }

            }
        }
        return res;
    }

}
