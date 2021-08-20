package com.anirudh

/**
  * Created by anirudh on 18/9/16.
  */
object Sandbox2 extends App{


  val s = " * This file contains portable arithmetic entropy encoding routines for JPEG\n * " +
    "(implementing the ISO/IEC IS 10918-1 and CCITT Recommendation ITU-T T.81).\n *\n * Both sequential and progressive modes are supported in this single module."
  println(s.replace("\n", " ").replace("*", "").replaceAll(" +", " "))
}
