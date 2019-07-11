package com.anirudh.dynamic_programming_greedy

import scala.collection.mutable

/**
  * Created by anirudh on 25/6/16.
  */
//CTCI chap 9 Q 4: FInd all subsets of a set
object DPFindAllSets extends App{

  def findAllSubsetsNoDP(set:Set[Int]):Set[Set[Int]] = {

    if(set.isEmpty || set.size == 1)
      Set(set)
    else{
      val subsetsWithoutLastElem = findAllSubsetsNoDP(set.init)
      val lastElement = set.last
      val singleton = Set(lastElement)
      val subsetsWithLastElem = subsetsWithoutLastElem.map(subset => subset + lastElement) + singleton
      subsetsWithoutLastElem ++ subsetsWithLastElem
    }
  }

  val cache = new mutable.HashMap[Set[Int], Set[Set[Int]]]()

  def findAllSubsetsDP(set:Set[Int]):Set[Set[Int]] = {
    if(set.isEmpty || set.size == 1)
      Set(set)
    else{
      if(cache.contains(set))
        cache(set)
      else{
        val subsetsWithoutLastElem = findAllSubsetsDP(set.init)
        val lastElement = set.last
        val singleton = Set(lastElement)
        val subsetsWithLastElem = subsetsWithoutLastElem.map(subset => subset + lastElement) + singleton
        val allSubSets = subsetsWithoutLastElem ++ subsetsWithLastElem
        cache.put(set, allSubSets)
        allSubSets
      }
    }
  }

  val set = Set(1,2,3)

  val start = System.currentTimeMillis()
  val allSubsetsNoDP = findAllSubsetsNoDP(set)
  val end = System.currentTimeMillis()

  val allSubsetsNoDPSeq = allSubsetsNoDP.toSeq.sortBy(_.size)
  allSubsetsNoDPSeq foreach println

  assert(allSubsetsNoDP.size == Math.pow(2, set.size) - 1)
  println("time taken without DP: " + (end - start))

  println("-------------------")

  val start2 = System.currentTimeMillis()
  val allSubsetsDP = findAllSubsetsDP(set)
  val end2 = System.currentTimeMillis()

  val allSubsetsDPSeq = allSubsetsDP.toSeq.sortBy(_.size)
  allSubsetsDPSeq foreach println

  assert(allSubsetsDP.size == Math.pow(2, set.size) - 1)
  println("time taken with DP: " + (end2 - start2))

  assert(allSubsetsNoDP == allSubsetsDP)

  println("printing cache..")

  cache foreach println

}
