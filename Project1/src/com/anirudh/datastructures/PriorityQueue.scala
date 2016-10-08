package com.anirudh.datastructures

/**
  * Created by anirudh on 17/9/16.
  */

case class NewKeyIsLessThanCurrentKeyException(msg:String) extends Exception(msg)
object PriorityQueue extends App{

  var pq = Seq(30,27,25,22,19,20,18,17,15,16)
  val d  = 3

  def getParent(index:Int) = (index - 1)/d
  def getChild(index:Int, mth:Int) = (d*index) + mth

  def maxHeapify(pq:Seq[Int], parentIndex:Int):Seq[Int] = {
    val (maxIndex,reachedEnd) = (getChild(parentIndex, 1) to getChild(parentIndex, d)).foldLeft((parentIndex, false))
    {case(agg, childIndex) => if(childIndex > (pq.size - 1)) (agg._1, true) else
      (if(pq(agg._1) > pq(childIndex)) agg._1 else childIndex, false)}
    if(maxIndex == parentIndex)
      pq
    else{
      val temp = pq(parentIndex)
      val newPQ = pq.updated(parentIndex, pq(maxIndex)).updated(maxIndex, temp)
      if (reachedEnd) newPQ else maxHeapify(newPQ, maxIndex)
    }
  }

  def extractMax: Int ={
    val max = pq.head
    val newPQ = pq.last +: pq.tail //replaced max with last element
    pq = maxHeapify(newPQ.dropRight(1),0) //dropped last element
    max
  }

  def insert(pq:Seq[Int],indexElem:Int, elem:Int):Seq[Int] = {
    if( indexElem == 0)
      pq
    else{
      val indexParent = getParent(indexElem)
      val par = pq(indexParent)
      if(par >= elem)
        pq
      else{
        insert(pq.updated(indexParent, elem).updated(indexElem, par), indexParent, par)
      }
    }
  }

  def insertElem(pq:Seq[Int],elem:Int) = {
    val newPQ = pq :+ elem
    val indexElem = newPQ.indexOf(elem)
    insert(newPQ, indexElem, elem)
  }

  def increaseKey(pq:Seq[Int], i:Int, k:Int):Seq[Int] = {
    if(k < pq(i))
      throw NewKeyIsLessThanCurrentKeyException(k + " is less than " + pq(i))
    else{
      val newPQ = pq.updated(i, k)
      insert(newPQ, i, k)
    }
  }


//  println("Max: " + extractMax)
//  pq foreach println

//  val newPQ = insertElem(pq, 24)
  val newPQ = increaseKey(pq, 2, 33)
  newPQ foreach println
}
