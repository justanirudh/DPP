package com.anirudh.general_algos.fluff

/**
  * Created by anirudh on 6/9/16.
  */

case class TargetPayrollMoreThanLastYearException(msg:String) extends Exception(msg)
//EPI (//Elements of programming interviews): Q14.11
object FindSalaryCap extends App{

  def findCap(salaries:Seq[Double], target:Double):Double = {
    val avg:Double = target/salaries.size
    val excess = salaries.foldLeft(0.0)((ex, sal) => if(avg > sal) ex + avg - sal else ex)
    val empsHigherSals = salaries.count(_ > avg)
    if(empsHigherSals  == 0)
      throw TargetPayrollMoreThanLastYearException("Average salary ($"+ avg+") is more " +
        "than any of the last year's salaries")
    val shareHigherSalEmps = excess/empsHigherSals
    avg + shareHigherSalEmps
  }

  val salaries:Seq[Double] = Seq(90, 30, 100, 40, 20)
  val targetPayroll:Double = 210
  val cap = findCap(salaries, targetPayroll)
  println("This year's salary cap should be: " + cap)
}
