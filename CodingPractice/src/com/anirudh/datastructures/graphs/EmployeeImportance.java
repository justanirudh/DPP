package com.anirudh.datastructures.graphs;

/*
690. Employee Importance
Medium

1376

1170

Add to List

Share
You have a data structure of employee information, including the employee's unique ID, importance value, and direct subordinates' IDs.

You are given an array of employees employees where:

employees[i].id is the ID of the ith employee.
employees[i].importance is the importance value of the ith employee.
employees[i].subordinates is a list of the IDs of the direct subordinates of the ith employee.
Given an integer id that represents an employee's ID, return the total importance value of this employee and all their direct and indirect subordinates.



Example 1:


Input: employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
Output: 11
Explanation: Employee 1 has an importance value of 5 and has two direct subordinates: employee 2 and employee 3.
They both have an importance value of 3.
Thus, the total importance value of employee 1 is 5 + 3 + 3 = 11.
Example 2:


Input: employees = [[1,2,[5]],[5,-3,[]]], id = 5
Output: -3
Explanation: Employee 5 has an importance value of -3 and has no direct subordinates.
Thus, the total importance value of employee 5 is -3.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Create a directed graph (DAG) of employees
Create map of id -> importance
Do DFS (just because simpler algo)
 */
public class EmployeeImportance {

    Map<Integer, List<Integer>> graph;
    Map<Integer, Integer> impMap;
    int sum;
//    Set<Integer> visited; //visited not required since it is a DAG

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    void doDFS(int id) {
        sum += impMap.get(id);
        for (int subId : graph.get(id)) {
            doDFS(subId);
        }
    }

    public int getImportance(List<Employee> employees, int id) {
        //create graph
        graph = new HashMap<>();
        impMap = new HashMap<>();
        sum = 0;

        for (Employee e : employees) {
            impMap.put(e.id, e.importance);
            graph.put(e.id, new ArrayList<>());
            for (Integer sub : e.subordinates) {
                graph.get(e.id).add(sub);
            }
        }

        //do DFS
        doDFS(id);
        return sum;
    }
}
