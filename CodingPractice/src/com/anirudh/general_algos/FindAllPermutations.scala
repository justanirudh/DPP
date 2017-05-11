package com.anirudh.general_algos

/**
  * Created by anirudh on 25/6/16.
  */
//CTCI Chap9 Q5: This is O(n!). Cannot do better than this as n! permutations
object FindAllPermutations extends App{

  def getAllPermutations(string:String):Seq[String] = {
    if(string.isEmpty || string.length == 1)
      Seq(string)
    else {
      val permsWithoutLastChar = getAllPermutations(string.init)
      val lastChar = string.last
      permsWithoutLastChar.foldLeft(Seq[String]()){
        case (agg, permutation) => {
          val length = permutation.length
          val strings = (0 to length).foldLeft(Seq[String]()){
            case(agg2, index) => {
              val (part1, part2) = permutation.splitAt(index)
              agg2 :+ (part1 + lastChar + part2)
            }
          }
          agg ++ strings
        }
      }
    }
  }

  def factorial(n: Int): Int = {
    if (n == 0)
      1
    else
      n * factorial(n-1)
  }

  val str = "create"

//  val num = readLine()
//  val numAllSubsets = (num.toSeq.toSet.subsets.map(_.toSeq).toSeq.map(s => s.mkString)
//    .filterNot(s => s == "") :+ num).distinct
//  val singleDigits = numAllSubsets.filter(_.size == 1)
//  val multipleDigits = numAllSubsets.filter(_.size > 1)
//  val allPermutations =  multipleDigits.flatMap(subset => getAllPermutations(subset)).distinct
//  val max = (allPermutations.filter(s => s == s.reverse) ++ singleDigits).map(_.toInt).max
//  println(max)
val allPermutations = getAllPermutations(str)
  allPermutations foreach println

  println("size of allPerms: " + allPermutations.length)
  assert(allPermutations.length == factorial(str.length))
}
