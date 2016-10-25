package com.anirudh.datastructures.facebookInterviewProblem

import scala.collection.mutable.{HashMap => MutableMap}

/**
  * Created by anirudh on 24/10/16.
  */

/*
Given a sequence of sequences of strings, merge them such that the relative order of strings is maintained.
Eg. merging this{{a,d,e}, {b,d,f}} should give: {a, b, d, e, f} or {b, a, d, e, f} or e and f in different orders.
*/
case class CycleInInputSequencesException(msg:String) extends Exception(msg)
object FindCorrectSequence extends App{

  class GraphNode(val str:String){
    var outNeighbours:Seq[String] = Seq()
    var inNeighboursCount = 0
    def addOutNeighbour(str:String) = outNeighbours = str +: outNeighbours
    def incInNeighbourCount() = inNeighboursCount = inNeighboursCount + 1
  }

  def createDAG(sos:Seq[Seq[String]]):MutableMap[String, GraphNode] = {

    val graph = new MutableMap[String, GraphNode]()

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
        graph(to).incInNeighbourCount()
      }
    }
    graph
  }

  def getOrderedSeq(sos:Seq[Seq[String]]):Seq[String] = {
    val graph = createDAG(sos)

    //print the graph
    /*for((key, value) <- graph){
      print(key + "(in = " + value.inNeighboursCount + "): ")
      for(n <- value.outNeighbours)
        print(n + ", ")
      println()
    }*/

    //Two ways of getting the correct order:
    //1. iterate through all nodes and find the one with inCount = 0. Add that to our sequence, decrement counter of all
    // the outNeighbours of the node and delete the current node (or mark it as -1). This is O(V^2 + E) = O(V^2)
    //2. Use topological sort. O(V + E) ~ O(V^2)

    //Doing 1 here
    //Making temporary mapping of vertices to their in-counts
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

    getFinalSeq(vertexToInCount, Seq())
  }

  val seqOfSeqs = Seq(Seq("a", "b", "c", "g"), Seq("k","a","d"), Seq("d", "c", "i"), Seq("c", "l", "g"))

  //c -> g -> c
  val seqOfSeqsthrows = Seq(Seq("a", "b", "c", "g", "c"), Seq("k","a","d"), Seq("d", "c", "i"), Seq("c", "l", "g"))

  val orderedSeq = getOrderedSeq(seqOfSeqs)

  for(str <- orderedSeq){
    print(str + " ")
  }
}
