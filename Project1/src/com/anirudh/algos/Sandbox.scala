package com.anirudh.algos
import scala.io.Source

/**
  * Created by anirudh on 14/6/16
  */
object Sandbox extends App{
  val filename = "/home/anirudh/Downloads/PracticeInput.txt"
  for (line <- Source.fromFile(filename).getLines()) {
    println(line)
  }

}
