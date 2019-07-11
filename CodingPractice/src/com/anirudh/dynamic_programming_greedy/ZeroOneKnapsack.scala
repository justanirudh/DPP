package com.anirudh.dynamic_programming_greedy

/**
  * Created by anirudh on 12/6/16.
  */
object ZeroOneKnapsack extends App{

  //algo in DSA book, DP chapter, 01KnapSack problem
  def solveZeroOneKnapsackForMaxBenefit(itemsMap: Map[Int, (Int, Int)], knapsackSize:Int) = {

    val items = itemsMap.toSeq
    val weightsRange = 0 to knapsackSize

    //Can be done with simple for loops.
    val itemToWeightBenefitMatrix:Seq[Seq[Int]] = items.foldLeft(Seq[Seq[Int]]()){
      case(matrixTillNow, (indexItem, (benefitItem, weightItem))) => {
          val benefitsForIndex:Seq[Int] = weightsRange.foldLeft(Seq[Int]()){
            case(benefitsTillNow, w) =>
              benefitsTillNow :+ (
                //first item
                if(indexItem == 0){
                 if(weightItem > w)
                    0
                else
                   benefitItem
              } else {
                  val maxBenefitTillNow = matrixTillNow(indexItem - 1)(w)
                if(weightItem > w)
                  maxBenefitTillNow
                  else
                  math.max(maxBenefitTillNow, matrixTillNow(indexItem -1)(w - weightItem) + benefitItem)
              })
          }
        matrixTillNow :+ benefitsForIndex
      }
    }
    (itemToWeightBenefitMatrix.last.last, itemToWeightBenefitMatrix)
  }

  def findItemsSelected(matrix:Seq[Seq[Int]], knapsacksize: Int, allItems:Seq[(Int, (Int, Int))]):Seq[Int] = {
    def find(row:Int, column:Int, items:Seq[Int]):Seq[Int] = {
      if(row == 0) //first item
        if(column == 0)
            items
        else
          0 +: items
      else{
        if(matrix(row)(column) == matrix(row -1)(column))
          find(row -1, column, items)
        else
          find(row - 1, column - allItems(row)._2._2, row +: items)
      }

    }

    find(allItems.size - 1, knapsacksize, Seq())

  }

  //index to (benefit, weight)
  //https://www.youtube.com/watch?v=8LusJS5-AGo
  val items = Map(0 -> (1, 1), 1 -> (4, 3), 2 -> (5, 4), 3 ->(7, 5))
  val knapsackSize = 7

  val maximumBenefitWithMatrix:(Int, Seq[Seq[Int]]) = solveZeroOneKnapsackForMaxBenefit(items, knapsackSize)

  val maxBenefit = maximumBenefitWithMatrix._1
  println(maxBenefit)
  println("--------------------")
  val itemsSelected:Seq[Int] = findItemsSelected(maximumBenefitWithMatrix._2, knapsackSize, items.toSeq)

  itemsSelected foreach println
}
