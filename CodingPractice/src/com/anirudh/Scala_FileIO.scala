package com.anirudh

/**
  * Created by anirudh on 22/7/16.
  */
import scala.io.Source

object FileIO{
  def main(args: Array[String]) {
//    println("Following is the content read:" )
//
//    Source.fromFile("/home/anirudh/Code/Test.txt" ).foreach {
//      print
//
//    }
  val fileLines = Source.fromFile("/home/anirudh/Code/Test.txt").getLines.toSeq
    val numbers:Seq[Seq[Int]] = fileLines.foldLeft(Seq[Seq[Int]]())((agg, line) =>
      agg :+ line.split(" ").toSeq.map(_.toInt))
    numbers.foreach(seq => println(seq.mkString(",") + "\n"))

  }
}
