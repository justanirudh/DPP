package com.anirudh.general_algos

/**
  * Created by anirudh on 2/6/16.
  */
object FractionalKnapsack extends App{

  def solveFracKnap(items: Map[Int, (Int, Int)], knapsackSize:Int) = {

    val indexToWeightSortedByVbyW =
      items.toSeq.sortWith((i1, i2) => (i1._2._1/i1._2._2) > (i2._2._1/i2._2._2))
      .map(i => (i._1, i._2._2))

    def solve(knapsack:Seq[(Int, Int)], sizeLeft:Int, posIVBW:Int):Seq[(Int, Int)] = {
      if(sizeLeft == 0)
          knapsack
      else{
        val item = indexToWeightSortedByVbyW(posIVBW)
        val weightAllowed = Math.min(item._2, sizeLeft)
        solve(knapsack :+ (item._1, weightAllowed), sizeLeft - weightAllowed, posIVBW + 1)
      }
    }

    solve(Seq(), knapsackSize, 0).toMap
  }

  //index to (benefit, weight)
  //https://www.youtube.com/watch?v=c2Ush3m_sfc
  val items = Map(1 -> (25, 3), 2 -> (20, 2), 3 -> (15, 1), 4 ->(40, 4), 5 -> (50, 5))
  val knapsackSize = 6

  //index to weight
  val distribution:Map[Int, Int] = solveFracKnap(items, knapsackSize)

  distribution foreach println

  val totalBenefit = distribution.toSeq.foldLeft(0)((agg, item) => agg +
    (item._2 * items(item._1)._1/items(item._1)._2))

  println("Benefit: " + totalBenefit)
}
