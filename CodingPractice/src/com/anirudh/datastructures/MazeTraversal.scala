package com.anirudh.datastructures

import scala.collection.immutable.ListMap

/**
  * Created by anirudh on 16/11/16.
  */

//http://www.geeksforgeeks.org/shortest-path-in-a-binary-maze/
/*
Shortest path in a Binary Maze
Given a MxN matrix where each element can either be 0 or 1. We need to find the shortest path between a given source
cell to a destination cell. The path can only be created out of a cell if its value is 1.

Expected time complexity is O(MN).

For example –

Input:
mat[ROW][COL]  = {{1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                  {1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                  {1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                  {0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                  {1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
                  {1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
                  {1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                  {1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                  {1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }};
Source = {0, 0};
Destination = {3, 4};

Output:
Shortest Path is 11


 */
object MazeTraversal extends App{

  class GraphNode(val coords:(Int, Int)){
    var color = "white" //undiscovered
    var distFromSrc = -1
    var parent:(Int, Int) = (-1, -1)
    var neighbours:Seq[(Int, Int)] = Seq()
  }

  def getPath(graph:ListMap[(Int, Int), GraphNode], src:(Int, Int), dest:(Int,Int)):(Seq[(Int, Int)], Int) = {
    val destNode = graph(dest)
    if(destNode.distFromSrc == -1){
      (Seq(), -1)
    }
    else {
      var path:Seq[(Int, Int)] = Seq()
      var currCoords = dest
      while(currCoords != src){
        path = currCoords +: path
        currCoords = graph(currCoords).parent
      }
      path = currCoords +: path
      (path, destNode.distFromSrc)
    }
  }

  def performBFS(graph:ListMap[(Int, Int), GraphNode] , src:(Int, Int)):ListMap[(Int, Int), GraphNode] = {
    //Breadth First Search
    val srcNode = graph(src)
    srcNode.color = "grey" //discovered
    srcNode.distFromSrc = 0
    val queue = new QueueUsingLL[(Int, Int)]
    queue.enqueue(src)
    while(!queue.isEmpty){
      val coords:(Int, Int) = queue.dequeue().asInstanceOf[(Int, Int)]
      val graphNode = graph(coords)
      for(neighbour <- graphNode.neighbours){
        val neighbourNode = graph(neighbour)
        if(neighbourNode.color == "white"){
          neighbourNode.color = "grey"
          neighbourNode.parent = coords
          neighbourNode.distFromSrc = graphNode.distFromSrc + 1
          queue.enqueue(neighbour)
        }
      }
      graphNode.color = "black"
    }
    graph
  }

  def createGraph(maze:Array[Array[Int]] ):ListMap[(Int, Int), GraphNode] = {

    def findNeighbours(coords:(Int, Int)):Seq[(Int, Int)] = { //using maze from closure
      def isValid(coords:(Int, Int)) =
        coords._1 >=0 && coords._1 < maze.length && coords._2 >= 0 && coords._2 < maze(0).length
      val right = (coords._1 + 1, coords._2)
      val top = (coords._1, coords._2 + 1)
      val left = (coords._1 - 1, coords._2)
      val bottom = (coords._1, coords._2 - 1)
      val potentialNeighbours = Seq(right, top, left, bottom)
      var neighbours:Seq[(Int, Int)] = Seq()
      for(coords <- potentialNeighbours){
        if(isValid(coords) && maze(coords._1)( coords._2) == 1)
          neighbours = coords +: neighbours
      }
      neighbours
    }

    //using ListMap just so that print of graph is in order of the data entered
    //SortedMap: sorts keys; LinkedHashMap: reverse order of entry
    var graph:ListMap[(Int, Int), GraphNode] = ListMap()

    for(x <- maze.indices){
      for(y <- maze(0).indices){
        val coords = (x,y)
        //1. add neighbours to its node class, 2. add to graph
        if(maze(x)(y) == 1){ //node exists only if its value is 1
          val graphNode = new GraphNode(coords)
          val neighbours = findNeighbours(coords)
          graphNode.neighbours = neighbours
          graph = graph + (coords -> graphNode)
        }
      }
    }
    graph
  }


  val maze:Array[Array[Int]] = Array(
    Array(1, 0, 1, 1, 1, 1, 0, 1, 1, 1 ),
    Array(1, 0, 1, 0, 1, 1, 1, 0, 1, 1 ),
    Array(1, 1, 1, 0, 1, 1, 0, 1, 0, 1 ),
    Array(0, 0, 0, 0, 1, 0, 0, 0, 0, 1 ),
    Array(1, 1, 1, 0, 1, 1, 1, 0, 1, 0 ),
    Array(1, 0, 1, 1, 1, 1, 0, 1, 0, 0 ),
    Array(1, 0, 0, 0, 0, 0, 0, 0, 0, 1 ),
    Array(1, 0, 1, 1, 1, 1, 0, 1, 1, 1 ),
    Array(1, 1, 0, 0, 0, 0, 1, 0, 0, 1 )
  )

  val src = (0,0)
  val dest = (3,4)

  val graph = createGraph(maze) //creates adjacency list of the maze/matrix

  val updatedGraph:ListMap[(Int, Int), GraphNode] = performBFS(graph, src) //updates graph with BFS wrt src

  /*for(vertex <- updatedGraph) {
    println(vertex._1 + "-> ")
    val node = vertex._2
    println("color: " + node.color + " parent: (" + node.coords._1 + "," + node.coords._2 + ")" + " distance from src: " + node.distFromSrc)
  }*/

  val path:(Seq[(Int, Int)], Int) = getPath(updatedGraph, src, dest) //gets path

  if(path._2 == -1){
    println("No Path")
  }
  else{
    print("Path: ")
    for(coords <- path._1){
      print("(" + coords._1 + ", " + coords._2 + ") -> ")
    }
    print("Finished\npath length: " + path._2)
  }

}
