package com.anirudh.datastructures;

/*
* The Hungarian Paul Erd¨os (1913–1996, speak as “Ar-dish”) not only was one of the strangest mathematicians
of the 20th century, he was also one of the most famous. He kept on publishing widely
circulated papers up to a very high age and every mathematician having the honor of being a co-author
to Erd¨os is well respected.
Not everybody got the chance to co-author a paper with Erd¨os, so many people were content if they
managed to publish a paper with somebody who had published a scientific paper with Erd¨os. This
gave rise to the so-called Erd¨os numbers. An author who has jointly published with Erd¨os had Erd¨os
number 1. An author who had not published with Erd¨os but with somebody with Erd¨os number 1
obtained Erd¨os number 2, and so on.
Today, nearly everybody wants to know which Erd¨os number he or she has. Your task is to write a
program which computes Erd¨os numbers for a given set of scientists.
Input
The first line of the input contains the number of scenarios.
The input for each scenario consists of a paper database and a list of names. It begins with the line
P N
where P and N are natural numbers. Following this line are P lines containing descriptions of papers
(this is the paper database). A paper appears on a line by itself and is specified in the following way:
Smith, M.N., Martin, G., Erdos, P.: Newtonian forms of prime factors matrices
Note that umlauts like ‘¨o’ are simply written as ‘o’. After the P papers follow N lines with names.
Such a name line has the following format:
Martin, G.
Output
For every scenario you are to print a line containing a string “Scenario i” (where i is the number
of the scenario) and the author names together with their Erd¨os number of all authors in the list of
names. The authors should appear in the same order as they appear in the list of names. The Erd¨os
number is based on the papers in the paper database of this scenario. Authors which do not have any
relation to Erd¨os via the papers in the database have Erd¨os number “infinity”.
Sample Input
1
4 3
Smith, M.N., Martin, G., Erdos, P.: Newtonian forms of prime factor matrices
Erdos, P., Reisig, W.: Stuttering in petri nets
Smith, M.N., Chen, X.: First oder derivates in structured programming
Jablonski, T., Hsueh, Z.: Selfstabilizing data structures
Smith, M.N.
Hsueh, Z.
Chen, X.
Sample Output
Scenario 1
Smith, M.N. 1
Hsueh, Z. infinity
Chen, X. 2
* */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by anirudh on 20/9/16.
 */

//TODO: BROKEN: Needs to be fixed

public class ErdosNumber {

//    public static Scanner s = new Scanner(System.in);


    public static class Node {
        String scientist;
        Boolean explored = false;
        int erdosNum;
        ArrayList<String> neighbours = new ArrayList<>();
        public Node(String scientist){
            this.scientist = scientist;
        }
        Node updateNeighbours(String e) {
            neighbours.add(e);
            return this;
        }
    }

    public static int findErdosNumber(Map<String, Node> graph, String scientist){
        Queue<String> queue = new LinkedList<>();
        queue.add("Erdos, P.");
        graph.get("Erdos, P.").erdosNum = 0;
        while (!queue.isEmpty()){
            Node src = graph.get(queue.poll());
            src.explored = true;
            ArrayList<String> neighbours = src.neighbours;
            for(String neighbour : neighbours){
                if(!graph.get(neighbour).explored){
                    graph.get(neighbour).erdosNum = src.erdosNum + 1;
                    if(neighbour.equals(scientist))
                        return graph.get(neighbour).erdosNum;
                    else
                        queue.add(neighbour);
                }
            }
        }
        return -1;
    }

    public static void addToGraph(ArrayList<String> allScientists){
        int k, j;
        int sizeScientists = allScientists.size();
        for(k =0; k < sizeScientists; k++){
            String e = allScientists.get(k);
            for(j = 0; j < sizeScientists; j++){
                if(k != j){
                    String key = allScientists.get(j);
                    if(graph.containsKey(key)){
                        graph.put(key, graph.get(key).updateNeighbours(e));
                    }
                    else{
                        Node n = new Node(key);
                        graph.put(key, n.updateNeighbours(e));
                    }
                }
            }
        }
    }

    public static Map<String, Node> graph = new HashMap<>();

    public static void readStuff( BufferedReader s) throws IOException{
        Integer scenarios = Integer.parseInt(s.readLine());
        int i, l, m;
        ArrayList<String> allScenarios = new ArrayList<>();
        for (m = 0; m<scenarios; m++) {
            allScenarios.add("Scenario " + (m+1));
            String[] inputs = s.readLine().split(" ");
            int  numPapers =  Integer.parseInt(inputs[0].trim());
            int  numScientists =  Integer.parseInt(inputs[1].trim());
            for(i = 0; i<numPapers; i++){
                //parse scientists per line
                String paper = s.readLine();
                String[] scientistsAllBroken = (paper.split(":")[0]).split(",");
                ArrayList<String> allScientists = new ArrayList<>();
                for(l = 0; l < scientistsAllBroken.length; l++){
                    allScientists.add(scientistsAllBroken[l].trim() + ", " + scientistsAllBroken[l+1].trim());
                    l++;
                }
                //make graph
                addToGraph(allScientists);
            }

            //printing graph
            Map<String, Node> graphCopy0 = new HashMap<>(graph);
            for (Map.Entry<String, Node> entry : graphCopy0.entrySet()) {
                String key = entry.getKey();
                ArrayList<String> values = entry.getValue().neighbours;
                System.out.print(key + "-> ");
                for(String e : values){
                    System.out.print(e + " | ");
                }
                System.out.println();
            }

            //BFS in the graph
            for(i = 0; i< numScientists; i++){
                String scientist = s.readLine();
                Map<String, Node> graphCopy = new HashMap<>(graph);
                int erdosNum = findErdosNumber(graphCopy, scientist);
                if(erdosNum == -1)
                    allScenarios.add(scientist + " infinity");
//                    System.out.println(scientist + " infinity");
                else
                    allScenarios.add(scientist + " " + erdosNum);
//                    System.out.println(scientist + " " + erdosNum);
            }
        }
        for(String str: allScenarios){
            System.out.println(str);
        }
    }

    public static void main(String[] args) throws FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader("/home/anirudh/erdosInputTiny"));
        try{
            readStuff(br);
        }
        catch(Exception e){

        }

    }
}
