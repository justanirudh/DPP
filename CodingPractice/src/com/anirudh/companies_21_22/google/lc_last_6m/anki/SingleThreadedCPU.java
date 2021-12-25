package com.anirudh.companies_21_22.google.lc_last_6m.anki;

import java.util.*;

/*
1834. Single-Threaded CPU
Medium

628

86

Add to List

Share
You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and will take processingTimei to finish processing.

You have a single-threaded CPU that can process at most one task at a time and will act in the following way:

If the CPU is idle and there are no available tasks to process, the CPU remains idle.
If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
Once a task is started, the CPU will process the entire task without stopping.
The CPU can finish a task then start a new one instantly.
Return the order in which the CPU will process the tasks.



Example 1:

Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
Output: [0,2,3,1]
Explanation: The events go as follows:
- At time = 1, task 0 is available to process. Available tasks = {0}.
- Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
- At time = 2, task 1 is available to process. Available tasks = {1}.
- At time = 3, task 2 is available to process. Available tasks = {1, 2}.
- Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
- At time = 4, task 3 is available to process. Available tasks = {1, 3}.
- At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
- At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
- At time = 10, the CPU finishes task 1 and becomes idle.
Example 2:

Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
Output: [4,3,2,0,1]
Explanation: The events go as follows:
- At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
- Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
- At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
- At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
- At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
- At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
- At time = 40, the CPU finishes task 1 and becomes idle.
 */
/*
Sort tasks by starting times
Use PQ sorted by proc-time, then index
iterate over times sorted list while adding stuff to PQ until time is met
once sorted times arr is finished, just keep pulling from queue until thats empty too
do this until ans array is full
 */
public class SingleThreadedCPU {

    public int[] getOrder(int[][] tasks) {
        //sort tasks by enqueue times
        int[][] richTasks = new int[tasks.length][3]; //startTime, procTime, index
        for (int i = 0; i < tasks.length; ++i) {
            richTasks[i][0] = tasks[i][0];
            richTasks[i][1] = tasks[i][1];
            richTasks[i][2] = i;
        }

        Arrays.sort(richTasks, Comparator.comparingInt(a -> a[0]));

        //create a queue to compare based on processing-time, then index
        Queue<int[]> order = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[2] - b[2];
            }
        });

        List<Integer> resList = new ArrayList<>();

        int idx = 0;
        int thresholdStartTime = richTasks[idx][0]; //first start time

        while (idx < richTasks.length) {
            while (idx < richTasks.length && richTasks[idx][0] <= thresholdStartTime) { //get all valid tasks
                order.offer(richTasks[idx]);
                idx++;
            }
            if (!order.isEmpty()) {
                int[] nextTask = order.poll();
                resList.add(nextTask[2]); //add its index to result
                thresholdStartTime += nextTask[1]; //get next threshold
            } else { //gap between 2 tasks, put next one in queue
                thresholdStartTime = richTasks[idx][0];
            }

        }

        while (!order.isEmpty()) { //at this point, richTasks are all either in order PQ or already processed
            resList.add(order.poll()[2]);
        }

        int[] res = new int[tasks.length]; //convert list ot arr
        for (int i = 0; i < tasks.length; ++i) {
            res[i] = resList.get(i);
        }
        return res;
    }
}
