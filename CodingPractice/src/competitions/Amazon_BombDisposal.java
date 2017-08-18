/**
 * Created by panirud on 8/15/17.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Amazon_BombDisposal {

    static class GraphNode {
        String color;
        int dist;
        Set<String> neighbours;
        String explore;
        GraphNode ( String color){
            this.color = color;
            dist = 0;
            neighbours = new HashSet<>();
            explore = "white";
        }
    }

    static HashMap<String, GraphNode> graph;

    static HashMap<String, GraphNode> createGraph(List<String> wireDescriptions){
        HashMap<String, GraphNode> graph = new HashMap<>();
        for(String wire : wireDescriptions){
            String[] ends = wire.split(",");
            if(!graph.containsKey(ends[0]))
                graph.put(ends[0], new GraphNode(ends[0]));
            graph.get(ends[0]).neighbours.add(ends[1]);
            if(!graph.containsKey(ends[1]))
                graph.put(ends[1], new GraphNode(ends[1]));
            graph.get(ends[1]).neighbours.add(ends[0]);
        }
        return graph;
    }

    static int doBFS(HashMap<String, GraphNode> graph){
        if(!graph.containsKey("orange") || !graph.containsKey("pink"))
            return -1;
        graph.get("orange").explore = "gray";
        Queue<String> queue = new LinkedList<>();
        queue.add("orange");
        while(!queue.isEmpty()){
            String head = queue.remove();
            Set<String> neighbours = graph.get(head).neighbours;
            for(String neighbour : neighbours){
                if(graph.get(neighbour).explore.equals("white")){
                    if(neighbour.equals("pink"))
                        return graph.get(head).dist + 1;
                    graph.get(neighbour).dist = graph.get(head).dist + 1;
                    graph.get(neighbour).explore = "gray";
                    queue.add(neighbour);
                }
            }
            graph.get(head).explore = "black";
        }
        return -1;
    }

    public static int run(List<String> wireDescriptions) {
        // Write your code in this method, without changing the signature.
        graph = createGraph(wireDescriptions);

        return doBFS(graph);
    }

    public static void main(String[] args) {
        // This method is provided purely for testing. It will not be called by the
        // grading robot.

        System.out.println(run(Arrays.asList(
                "black,blue",
                "blue,orange",
                "orange,orange",
                "orange,orange",
                "orange,black",
                "orange,pink",
                "blue,pink"
        )));
    }
}
