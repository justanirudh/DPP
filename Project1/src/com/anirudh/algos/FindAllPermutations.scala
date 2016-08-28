package com.anirudh.algos

/**
  * Created by anirudh on 25/6/16.
  */
//CTCI Chap9 Q5: only implementing Recursive, DP would be just adding hashmap for result of each substring
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

  val string = "eat"

  val allPermutations = getAllPermutations(string)

  allPermutations foreach println

  assert(allPermutations.length == factorial(string.length))
}
