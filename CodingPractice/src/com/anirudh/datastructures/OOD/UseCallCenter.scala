//package com.anirudh.datastructures.OOD
//
//import scala.concurrent.{Await, Future}
//
///**
//  * Created by anirudh on 3/7/16.
//  */
/////CTCI chap8q2
//object UseCallCenter {
//
//  case class NoFreeEmployeeException(msg: String) extends Exception(msg)
//
//  //respondants can handle diff 0-4
//  //managers can handle diff 5-7
//  //directors can handle diff 8-10
//  class Call(val content: String, val difficulty: Int) {
//    assert(difficulty >= 0 && difficulty <= 10)
//  }
//
//  class Reply(val content: String) {
//    override def toString = "reply is " + content
//  }
//
//  abstract class Employee {
//    var isFree: Boolean
//
//    def canHandle(call: Call): Boolean
//
//    def reply(call: Call): Future[Reply]
//  }
//
//  class Respondant extends Employee {
//    var isFree = true
//
//    def canHandle(call: Call) = call.difficulty >= 0 && call.difficulty <= 4
//
//    def reply(call: Call) = Future {
//      Thread.sleep(1000)
//      isFree = true
//      new Reply(call.content + ". I am a respondant, how can I help?")
//    }
//  }
//
//  class Manager extends Employee {
//    var isFree = true
//
//    def canHandle(call: Call) = call.difficulty >= 5 && call.difficulty <= 7
//
//    def reply(call: Call) = Future {
//      Thread.sleep(500)
//      isFree = true
//      new Reply(call.content + ". I am a manager, how can I help?")
//    }
//  }
//
//  class Director extends Employee {
//    var isFree = true
//
//    def canHandle(call: Call) = call.difficulty >= 8 && call.difficulty <= 10
//
//    def reply(call: Call) = Future {
//      Thread.sleep(100)
//      isFree = true
//      new Reply(call.content + ". I am a director, how can I help?")
//    }
//  }
//
//  class CallCenter {
//    val respondants = Seq(new Respondant, new Respondant, new Respondant, new Respondant, new Respondant)
//    val managers = Seq(new Manager, new Manager, new Manager)
//    val director = Seq(new Director)
//    val employees = respondants ++ managers ++ director
//  }
//
//  //Factory method
//  def dispatchCall(call: Call, callCenter: CallCenter): Future[Reply] = {
//
//    val freeEmployee = callCenter.employees.find(e => e.isFree && e.canHandle(call)).getOrElse(throw NoFreeEmployeeException("No Free Employee"))
//    freeEmployee.isFree = false
//    freeEmployee.reply(call)
//  }
//
//  def main(args: Array[String]) = {
//    val calls = Seq(new Call("How to open a new account?", 3),
//      new Call("How to open a new account?", 5),
//      new Call("How to open a new account?", 9),
//      new Call("How to open a new account?", 1),
//      new Call("How to open a new account?", 0),
//      new Call("How to open a new account?", 6)
//    )
//
//    val callCenter = new CallCenter
//
//    val replies = calls.foldLeft(Seq[Future[Reply]]())((agg, call) => agg :+ dispatchCall(call, callCenter))
//    //      .map(Await.result(_, 5000 millis))
//    //    println(reply)
//    replies foreach println
//  }
//
//}
