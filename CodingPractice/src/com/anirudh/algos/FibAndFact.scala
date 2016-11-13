package com.anirudh.algos

/**
  * Created by anirudh on 12/11/16.
  */
object FibAndFact extends App{

  def factorialRec(n:Int):Int = {
    if(n == 0) 1
    else if(n == 1) 1
    else {
      n * factorialRec(n - 1)
    }
  }

  def factorialIter(n:Int):Int = {
    if(n == 0) 1
    else {
      var prod = 1
      for(num <- n to 1 by -1){
        prod = prod * num
      }
      prod
    }
  }

  def fibonacciIterNth(n:Int):Int = {
    if(n <= 0) 0
    else if (n == 1) 1
    else if (n == 2) 1
    else{
      var nMinusTwo = 1
      var nMinusOne = 1
      var next = 0
      for(i <- 1 to n - 2){
        next = nMinusOne + nMinusTwo
        nMinusTwo = nMinusOne
        nMinusOne = next
      }
      next
    }
  }

  def fibonacciRecNth(n:Int):Int = {
    if(n <= 0) 0
    else if (n == 1) 1
    else fibonacciRecNth(n-1) + fibonacciRecNth(n-2)
  }

  def fibonacciIterFirstN(n:Int):Seq[Int] = {
    if(n <= 0) Seq()
    else {
      var seq:Seq[Int] = Seq()
      for(i <- 0 until n ){
        if(i == 0)
          seq = seq :+ 1
        else if (i == 1)
          seq = seq :+ 1
        else
          seq = seq :+ (seq(i - 1) + seq(i - 2))
      }
      seq
    }
  }

  def fibonacciRecFirstN(n:Int):Seq[Int] = {
    if(n <= 0) Seq()
    else if (n == 1) Seq(1)
    else if (n == 2) Seq(1, 1)
    else {
      var seq:Seq[Int] = Seq()
      for(num <- 1 to n){
        seq = seq :+ fibonacciRecNth(num)
      }
      seq
    }
  }

  val n = 5

  val factRec = factorialRec(n)
  println(factRec)

  val factIter = factorialIter(n)
  println(factIter)

  val i = 7 //first 7 numbers of fibonacci series

  val seriesIter = fibonacciIterFirstN(i)
  for(num <- seriesIter){
    print(num + " ")
  }
  println()

  val nthIterFibo = fibonacciIterNth(i)
  println(nthIterFibo)

  val nthRecFibo = fibonacciRecNth(i)
  println(nthRecFibo)

  val seriesRec = fibonacciRecFirstN(i)
  for(num <- seriesRec){
    print(num + " ")
  }
  println()

}
