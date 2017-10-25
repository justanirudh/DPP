package competitions;

import java.util.*;

/**
 * Created by paanir on 10/24/17.
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

        while (map.get(target).count != 0) {
            Queue<String> builts = new PriorityQueue<>();
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

        List<String> copyDepsList = new ArrayList<>(depList);
        List<String> justDeps = doBFS(map, target);

        copyDepsList.removeAll(justDeps);
        depList.removeAll(copyDepsList);

        String res = String.join(" ", depList);
        System.out.print(res);


    }


}
