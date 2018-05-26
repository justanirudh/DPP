package com.anirudh.general_algos.basics

import scala.util.Random

/**
  * Created by anirudh on 1/10/16.
  */
//average - n, worst - n^2
//median of median approach - worst n
object RandQuickSortWithFindRank extends App {

  def exchangeElems(arr: Seq[Int], index1: Int, index2: Int) = {
    val temp = arr(index1)
    arr.updated(index1, arr(index2)).updated(index2, temp)
  }

  def getPartition(seq: Seq[Int], start: Int, end: Int): (Int, Seq[Int]) = {
    var partSeq = seq
    val random = new Random(System.currentTimeMillis())
    val random_index = start + random.nextInt(end - start + 1)
    val pivot = partSeq(random_index)
    partSeq = exchangeElems(partSeq, random_index, start)
    //moved pivot to start
    var small = start
    for (large <- start + 1 to end) {
      //small = start, large= start + 1
      if (partSeq(large) < pivot) {
        small = small + 1
        partSeq = exchangeElems(partSeq, small, large)
      }
    }
    (small, exchangeElems(partSeq, start, small))
  }

  def quickSort(seq: Seq[Int], start: Int, end: Int): Seq[Int] = {
    if (start < end) {
      val (partitionIndex, partitionedArray) = getPartition(seq, start, end)
      val leftSorted = quickSort(partitionedArray, start, partitionIndex - 1)
      quickSort(leftSorted, partitionIndex + 1, end)
    }
    else
      seq

  }

  def findElemWithRank(seq: Seq[Int], rank: Int, start: Int, end: Int): Int = {
    //given a rank, give me the corresponding num: O(n) average case
    if (start == end)
      seq(start)
    else {
      val (partitionIndex, partitionedArray) = getPartition(seq, start, end)
      val rankPartition = partitionIndex - start + 1
      //binary search
      if (rank == rankPartition)
        partitionedArray(partitionIndex)
      else if (rank < rankPartition)
        findElemWithRank(partitionedArray, rank, start, partitionIndex - 1)
      else
        findElemWithRank(partitionedArray, rankPartition - rank, partitionIndex + 1, end)
    }
  }

  val seq = Seq(6, 10, 13, 5, 8, 3, 2, 11, 49, 23, 50, 1, 0)
  val sortedSeq = quickSort(seq, 0, seq.size - 1)
  sortedSeq foreach println

  val seq2 = Seq(6, 10, 13, 5, 8, 3, 2, 11)
  val getNum = findElemWithRank(seq2, 7, 0, seq2.size - 1)
  println(getNum)
}
