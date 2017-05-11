package com.anirudh.general_algos

/**
  * Created by anirudh on 14/11/16.
  */
/*
http://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/

 */
object MeetingRoomOccupancy extends App{

  val arr = Seq(900, 940, 950, 1100, 1500, 1800)
  val dep = Seq(910, 1200, 1120, 1130, 1900, 2000)

  val arrs = arr.map(a => (a, 0)) //starts
  val depd = dep.map(d => (d, 1)) //ends
  val all = (arrs ++ depd).sortBy(_._1)

  var maxOverlap = 0
  var curr = 0
  for (elem <- all){
    if(elem._2 == 0){ //starts
      curr = curr + 1
      if(curr > maxOverlap)
        maxOverlap = curr
    }
    else{ //ends
      curr = curr - 1
    }
  }
  println(maxOverlap)
}
