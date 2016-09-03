package com.anirudh.MSCodingProblem

import scala.io.Source

/**
  * Created by anirudh on 2/9/16.
  */
object ShuttleMSProblem extends App{

  type Weight = Int
  type Proximity = Int

  val HugeNumber = 100000

  def createGraph(pathsStrings:Seq[String]) = {
    //creating adjacency list
    val paths = pathsStrings.map(s => s.split(" ").toSeq match {
      case Seq(o, d, w) => Seq(o, d, w)
    }) //paths: (src, dest, weight)
    val nodes = paths.flatMap(edge => Seq(edge.head, edge(1))).distinct
    val nodesWithIndex = nodes.zipWithIndex
    val graphInit = nodes.map(s => Seq((s, 0))) //dist from itself is 0

    val graph = paths.foldLeft(graphInit){case (agg, path) => {
      val node1 = path.head
      val node2 = path(1)
      val weight = path(2).toInt
      //adding node1's edge to graph: find node1 in graphint and add edge
      val index = nodesWithIndex.find(_._1 == node1).get._2
      val aggUp = agg.updated(index, agg(index) :+ (node2, weight))
      //adding node2's edge to graph: find node2 in graphint and add edge
      val index2 = nodesWithIndex.find(_._1 == node2).get._2
      val aggUp2 = aggUp.updated(index2, aggUp(index2) :+ (node1,weight))
      aggUp2
    }
    }

    (graph, nodes)
  }

  def applyDijkstra(graph:Seq[Seq[(String, Weight)]], unexpQueue:Seq[(String, Proximity)],
                    nwp:Seq[(String, Proximity)]):Seq[(String, Proximity)] = {

    if( unexpQueue.isEmpty  || unexpQueue.size == 1)
      nwp
    else{
      val src = unexpQueue.minBy(_._2)
      val unexpQueueNew = unexpQueue diff Seq(src)
      val srcWithPaths = graph.find(_.head._1 == src._1).get.tail.filter(v => unexpQueueNew.map(_._1).contains(v._1))
      val (nwpNew, unexpQueueNew2) = srcWithPaths.foldLeft((nwp, unexpQueueNew)){
        case((prox, q), (dest, weight)) =>
          val destProximity = prox.find(_._1 == dest).get._2
          if(src._2 + weight < destProximity)
            (prox.updated(prox.indexWhere(_._1 == dest), (dest, src._2 + weight)),
              q.updated(q.indexWhere(_._1 == dest), (dest, src._2 + weight)))
          else
            (prox,q)
      }
      applyDijkstra(graph, unexpQueueNew2, nwpNew)
    }
  }

  def calculateTimeForWalk(src:String, dest:String, paths:Seq[String]):Int = {
    val graphAndNodes:(Seq[Seq[(String, Int)]], Seq[String]) = createGraph(paths)
    val nodesWithDummyProximities = graphAndNodes._2.map(node => (node,if(node == src) 0 else HugeNumber)) //(node, prox, explored)
    val nodesWithRealProximities = applyDijkstra(graphAndNodes._1, nodesWithDummyProximities, nodesWithDummyProximities)
    nodesWithRealProximities.find(_._1 == dest).get._2
  }

  def calculateBestRoutes(testcase:Seq[String], solvedTillNow:Seq[String], caseNo:Int): Seq[String] ={
    if(testcase.isEmpty)
      solvedTillNow
    else{
      val srcDest = testcase.head.split(" ")
      val noPaths = testcase.tail.head.toInt
      val restTestcase = testcase.tail.tail
      val (pathsAndShuttleTime, otherTestcases) = restTestcase.splitAt(noPaths+1)
      val (paths, shuttleTime) = pathsAndShuttleTime.splitAt(noPaths)
      val timeWalk = calculateTimeForWalk(srcDest(0), srcDest(1), paths)
      val timeShuttle = shuttleTime.head.toInt
      val solvedTestcase = if(timeWalk < timeShuttle) "Case #"+caseNo+": "+timeWalk+" Walk"
      else "Case #"+caseNo+": "+timeShuttle+" Shuttle"
      calculateBestRoutes( otherTestcases , solvedTillNow :+ solvedTestcase, caseNo + 1)
    }
  }

  val filename = "/home/anirudh/MSCodingProblem/MSGraphTC3.txt"
  val allLines= Source.fromFile(filename).getLines.toSeq
  val noTestcases = allLines.head.toInt
  val testcases = allLines.tail

  val allRoutes = calculateBestRoutes(testcases, Seq(), 1)
  for(route <- allRoutes){
    println(route + "\n")
  }
}
