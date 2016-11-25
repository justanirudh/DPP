package com.anirudh.datastructures.facebookInterviewProblem

/**
  * Created by anirudh on 24/10/16.
  */

/*
Given a sequence of sequences of strings, merge them such that the relative order of strings is maintained.
Eg. merging this{{a,d,e}, {b,d,f}} should give: {a, b, d, e, f} or {b, a, d, e, f} or e and f in different orders.
*/
case class CycleInInputSequencesException(msg:String) extends Exception(msg)

//All commented out stuff for Method 1
object FindCorrectSequence extends App{

  class GraphNode(val str:String){
    //for purposes of Method 2
    var color = "white"
    var outNeighbours:Seq[String] = Seq()
    def addOutNeighbour(str:String) = outNeighbours = str +: outNeighbours
    //for purposes of Method 1
    /*var inNeighboursCount = 0
    def incInNeighbourCount() = inNeighboursCount = inNeighboursCount + 1*/

  }

  def createDAG(sos:Seq[Seq[String]]):Map[String, GraphNode] = {

    var graph = Map[String, GraphNode]()

    //populating graph for this use-case
    for(seq <- sos){
      val indicesLastDropped = seq.indices.dropRight(1)
      for(i <- indicesLastDropped){ //add i->i+1 edge and/or node
        val from = seq(i)
        val to = seq(i + 1)
        if(!graph.contains(from)) //graph doesn't contain from
          graph += (from -> new GraphNode(from))
        graph(from).addOutNeighbour(to)
        if(!graph.contains(to)) //graph doesn't contain to
          graph += (to -> new GraphNode(to))
//        graph(to).incInNeighbourCount()
      }
    }
    graph
  }

  def doDFSAndPopulateSorted(graph:Map[String, GraphNode], start:String, sorted:Seq[String]):Seq[String] = {
    var sortedTemp = sorted
    //node has been discovered
    graph(start).color = "grey"
    for(neighbour <- graph(start).outNeighbours){
      if(graph(neighbour).color == "white"){ //dfs on the node only if it has not be discovered
        sortedTemp = doDFSAndPopulateSorted(graph,neighbour, sortedTemp)
      }
    }
    //node has been finished
    graph(start).color = "black"
    //add it to the front of the queue. The earlier a node gets finished, the deeper it is in the graph, the later it
    //comes in the topological sort
    start +: sortedTemp
  }

  def doTopologicalSort(graph:Map[String, GraphNode]):Seq[String] ={
    var sorted:Seq[String] = Seq()

    //DFS initiator
    //CLRS definition. Correct definition
    for(node <- graph.valuesIterator){
      if(node.color == "white"){
        sorted = doDFSAndPopulateSorted(graph, node.str, sorted)
      }
    }
    sorted
  }

  def getOrderedSeq(sos:Seq[Seq[String]]):Seq[String] = {
    val graph = createDAG(sos)

    //print the graph
    for((key, value) <- graph){
      print(key + /*"(in = " + value.inNeighboursCount + ")*/": ")
      for(n <- value.outNeighbours)
        print(n + ", ")
      println()
    }

    //Two ways of getting the correct order:
    //1. iterate through all nodes and find the one with inCount = 0. Add that to our sequence, decrement counter of all
    // the outNeighbours of the node and delete the current node (or mark it as -1). This is O(V^2 + E) = O(V^2)
    //2. Use topological sort. O(V + E)

    //Method 1
    /*//Making temporary mapping of vertices to their in-counts
    val vertexToInCount = new MutableMap[String, Int]()
    for(g <- graph){
      vertexToInCount += (g._1 -> g._2.inNeighboursCount)
    }

    def getFinalSeq(vcMap:MutableMap[String, Int], seqTillNow:Seq[String]):Seq[String] = {
      vcMap.find(_._2 == 0) match {
        case Some((str, _)) =>{
          val outNeighbours = graph(str).outNeighbours
          for(n <- outNeighbours){ //decreasing inCounts of all outNeighbours
            vcMap(n) = vcMap(n) - 1
          }
          getFinalSeq(vcMap - str, str +: seqTillNow) //deleting that node from the map and prepending str to final seq as it is cheaper
        }
        case None => //if map is empty, we found all strings so safely return; if map is not empty, there is a cycle and throw
          if(vcMap.isEmpty)
            seqTillNow.reverse
          else
            throw CycleInInputSequencesException("")
      }
    }

    getFinalSeq(vertexToInCount, Seq())*/

    //Method 2
    doTopologicalSort(graph)
  }

  //k a b d c i l g
  val seqOfSeqs = Seq(Seq("a", "b", "c", "g"), Seq("k","a","d"), Seq("d", "c", "i"), Seq("c", "l", "g"))

  //c -> g -> c
  val seqOfSeqsthrows = Seq(Seq("a", "b", "c", "g", "c"), Seq("k","a","d"), Seq("d", "c", "i"), Seq("c", "l", "g"))

  val orderedSeq = getOrderedSeq(seqOfSeqs)

  for(str <- orderedSeq){
    print(str + " ")
  }
}
