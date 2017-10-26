package com.anirudh.datastructures.graphs;

import java.util.*;

/**
 * Created by paanir on 10/24/17.
 */
/*
Dependency Gambit
Memory Limit: 1024MB Runtime Limit: 5s Score: 350
In a world of ever-growing software stacks, building the latest-and-greatest libraries is starting to get complicated! To help simplify the build process, you've decided to write a tool to help generate a library link-line from a dependency list. Given a list of libraries & their dependencies, and a target library we want to build, print out the perfect linkline according to the rules listed in the Output Specs.


Input Specifications

The first line of input is the name of the Library we're looking to build. Library names will only contain lowercase letters a-z and hyphens -. This second line will be an integer 0 < N < 25, followed by N lines of libraries & their dependencies. Each library-dependency line will be a series of space-delimited tokens: lib-name Q dep-1 dep-2 ... dep-Q. The first token is the name of the library; the second token 0 ≤ Q ≤ 25 represents the number of dependencies to follow, which all must be built in order for that library to compile.

Note that the input will never contain cycles.


Output Specifications

The output should be the linkline for the target library. Each library required to build the target (including the target itself) should appear exactly once, according to the following criteria:

The linker will run in rounds. In each round, it will build all libraries that have no remaining unbuilt dependencies at the start of that round. You may assume that the linker can infinitely parallelize, and that the round completes only once all libraries in that round have built.
Whenever the linker does a round, print out all the libraries from that round in lexicographical order (space-delimited), and move on to the next round.
The very last round will always be a single library: the target

Sample Input/Output

INPUT
lib-x
4
lib-x 2 lib-b lib-c
lib-a 0
lib-b 1 lib-a
lib-c 1 lib-a
OUTPUT
lib-a lib-b lib-c lib-x
EXPLANATION
lib-x is dependent on lib-b & lib-c, both of which are dependent on lib-a. Therefore, lib-a is built by itself in the first round (as it is the only one with no unbuilt dependencies); lib-b & lib-c are both built in the second round (and are sorted alphabetically when printed); and finally lib-x is printed in the 3rd round.
INPUT
lib-y
4
lib-y 0
lib-a 0
lib-b 1 lib-a
lib-c 1 lib-a
OUTPUT
lib-y
EXPLANATION
There are many other libraries with dependencies, but our target is lib-y, which has none. Therefore the only round is lib-y.
INPUT
lib-z
7
lib-a 0
lib-b 0
lib-c 0
lib-d 0
lib-e 1 lib-d
lib-f 1 lib-e
lib-z 4 lib-a lib-b lib-c lib-f
OUTPUT
lib-a lib-b lib-c lib-d lib-e lib-f lib-z
EXPLANATION
INPUT
bloom-lib
23
lib-a 0
lib-b 1 lib-a
lib-c 1 lib-b
lib-d 1 lib-c
lib-e 0
lib-f 1 lib-e
lib-g 1 lib-e
lib-h 1 lib-e
lib-i 0
lib-j 1 lib-i
bloom-lib 7 lib-b lib-j lib-r lib-s lib-t lib-u lib-v
lib-k 0
lib-l 0
lib-m 0
lib-n 0
lib-o 0
lib-p 0
lib-q 0
lib-r 7 lib-k lib-l lib-m lib-n lib-o lib-p lib-q
lib-s 7 lib-k lib-l lib-m lib-n lib-o lib-p lib-q
lib-t 7 lib-k lib-l lib-m lib-n lib-o lib-p lib-q
lib-u 7 lib-k lib-l lib-m lib-n lib-o lib-p lib-q
lib-v 7 lib-k lib-l lib-m lib-n lib-o lib-p lib-q
OUTPUT
lib-a lib-i lib-k lib-l lib-m lib-n lib-o lib-p
 */
public class Bloomberg_DependencyGraph {
    static class Node {
        String name;
        int count;
        Set<String> libs;
        String color;

        public Node(String name, int count, String[] neighs) {
            this.name = name;
            this.count = count;
            libs = new HashSet<>(Arrays.asList(neighs));
            color = "white";
        }

    }

    static List<String> doBFS(Map<String, Node> map, String target) {
        List<String> deps = new ArrayList<>();
        Node first = map.get(target);
        first.color = "grey";
        Queue<Node> queue = new LinkedList<>();
        queue.add(first);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            deps.add(curr.name);
            for (String neigh : curr.libs) {
                if (map.get(neigh).color.equals("white")) {
                    queue.add(map.get(neigh));
                }
            }
            curr.color = "black";
        }
        return deps;
    }


    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String target = stdin.nextLine();
        int numLibs = Integer.parseInt(stdin.nextLine());
        Map<String, Node> map = new HashMap<>();

        //map created
        for (int i = 0; i < numLibs; ++i) {
            String[] info = stdin.nextLine().split(" ");
            String lib = info[0];
            int numDeps = Integer.parseInt(info[1]);
            String[] deps = new String[info.length - 2];
            System.arraycopy(info, 2, deps, 0, info.length - 2);
            map.put(lib, new Node(lib, numDeps, deps));
        }

        List<String> depList = new ArrayList<>();

        //get to count 0 for target
        while (map.get(target).count != 0) {
            Queue<String> builts = new PriorityQueue<>(); //to keep them sorted
            Set<String> builtSet = new HashSet<>();
            //first go through all to get 0s. then go though all to remove all in set

            for (String key : map.keySet()) {
                if (map.get(key).count == 0) {
                    builts.add(key);
                    builtSet.add(key);
                }
            }

            for (String key : map.keySet()) {
                Node curr = map.get(key);
                Set<String> neighs = curr.libs;
                Set<String> copyNeighs = new HashSet<>(neighs);
                copyNeighs.removeAll(builtSet);
                curr.count = copyNeighs.size();
            }

            while (!builts.isEmpty()) {
                depList.add(builts.poll());
            }
        }
        depList.add(target);

        //now removing all that arent in target's dependency list
        List<String> copyDepsList = new ArrayList<>(depList);
        List<String> justDeps = doBFS(map, target);

        copyDepsList.removeAll(justDeps);
        depList.removeAll(copyDepsList);

        String res = String.join(" ", depList);
        System.out.print(res);


    }


}
