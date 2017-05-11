package com.anirudh.datastructures.OOD

import scala.collection.mutable

/**
  * Created by anirudh on 24/7/16.
  */

object UseParkingLot extends App {

  class Vehicle(val number: String)

  //extends it for BikeSlot and CarSlot; Also, add isRegistered

  class Money(val value: Int)

  type Change = Money
  type CanExit = Boolean

  class Receipt(val slotNumber: String, val vehicleNumber: String, var fee: Int)

  class User

  //Fields: Name, userid, vehicle, receipt, money
  //Actions: Ask for parking space, Drive to slot, wait for some time till his work finishes, give receipt when exit, give fee when
  //displayed on DisplayFee, Exit

  object ParkingLot {

    case class NoSpaceException(msg: String) extends Exception(msg)

    case class LessMoneyException(msg: String) extends Exception(msg)

    class DisplayFee(val fee: Int)

    class Slot(val number: String, var available: Boolean)

    //extends it for BikeSlot and CarSlot

    /*private*/ val slots: Seq[Slot] = Seq(
      new Slot("A", true),
      new Slot("B", true),
      new Slot("C", true),
      new Slot("D", true),
      new Slot("E", true)
    )
    /*private*/ var slotToVehicle: scala.collection.mutable.Map[String, (String, Long)]
    = mutable.Map[String, (String, Long)]() //slot to vehicle, parking time

    var earnings: Int = 0
    var currentExitReceipt: Receipt = _

    private def emptySlots: Seq[Slot] = slots.filter(s => s.available)

    private def filledSlots = slots.filter(s => !s.available)

    private def canOccupy: Boolean = emptySlots.nonEmpty

    private def getFreeSlot: (Slot, Int) = slots.zipWithIndex.find(s => s._1.available).get

    def provideReceiptToIncomingVehicle(vehicle: Vehicle): Receipt =
      if (!canOccupy)
        throw NoSpaceException("No space available in the parking lot")
      else {
        val freeSlotAndIndex = getFreeSlot
        //block the slot
        slots(freeSlotAndIndex._2).available = false
        //Update the chart
        slotToVehicle(freeSlotAndIndex._1.number) = (vehicle.number, System.currentTimeMillis())
        new Receipt(freeSlotAndIndex._1.number, vehicle.number, 0)
      }

    def calculateFeeForExitingVehicle(receipt: Receipt): DisplayFee = {
      val parkingTimeMillis = System.currentTimeMillis() - slotToVehicle(receipt.slotNumber)._2
      val fee = parkingTimeMillis * 1 //1 dollar per millisecond
      slotToVehicle(receipt.slotNumber) = (receipt.vehicleNumber, parkingTimeMillis) //parking time can also be put in receipt, also put start time and end time
      receipt.fee = fee.toInt
      currentExitReceipt = receipt
      new DisplayFee(fee.toInt)
    }

    def letVehicleExit(moneyFromUser: Money): (String, Option[Change], Option[DisplayFee], CanExit) = {
      //free up the slot
      slots.find(s => s.number == currentExitReceipt.slotNumber).get.available = true
      //update chart
      slotToVehicle -= currentExitReceipt.slotNumber
      val fee = currentExitReceipt.fee
      if (moneyFromUser.value < fee)
        ("You are short of cash sir.", None, Some(new DisplayFee(fee - moneyFromUser.value)), false)
      else if (moneyFromUser.value == fee) {
        earnings += fee
        ("Thank you.", None, None, true)
      }
      else {
        earnings += fee
        ("Thank you. Here's your change", Some(new Change(moneyFromUser.value - fee)), None, true)
      }
    }
  }

  //Initial state
  println("Inital state")
  for (s <- ParkingLot.slots) {
    println(s.number, s.available)
  }
  for (stv <- ParkingLot.slotToVehicle) {
    println(stv._1 + " has vehicle number " + stv._2._1)
  }
  println("earnings: " + ParkingLot.earnings)
//  println("Current exit receipt: " + ParkingLot.currentExitReceipt.slotNumber,
//    ParkingLot.currentExitReceipt.vehicleNumber, ParkingLot.currentExitReceipt.fee)

  //cars come in
  println("2 cars come in")
  val ticket1 = ParkingLot.provideReceiptToIncomingVehicle(new Vehicle("KA1234"))
  val ticket2 = ParkingLot.provideReceiptToIncomingVehicle(new Vehicle("KA1235"))
  for (s <- ParkingLot.slots) {
    println(s.number, s.available)
  }
  for (stv <- ParkingLot.slotToVehicle) {
    println(stv._1 + " slot has vehicle number " + stv._2._1)
  }
  println("ticket 1: ", ticket1.slotNumber, ticket1.vehicleNumber, ticket1.fee)
  println("waiting for 100ms")
  Thread.sleep(100) //should ideally be in User class
  val fee = ParkingLot.calculateFeeForExitingVehicle(ticket1)
  println("displayed fee: ", fee.fee)
  println("Current exit receipt: " + ParkingLot.currentExitReceipt.slotNumber,
    ParkingLot.currentExitReceipt.vehicleNumber, ParkingLot.currentExitReceipt.fee)
  val exitStatus = ParkingLot.letVehicleExit(new Money(500))
  println(exitStatus._1, exitStatus._2, exitStatus._3, exitStatus._4)
  //Final status after leaving
  println("Final status after 1 vehicle left")
  for (s <- ParkingLot.slots) {
    println(s.number, s.available)
  }
  for (stv <- ParkingLot.slotToVehicle) {
    println(stv._1 + " has vehicle number " + stv._2._1)
  }
  println("earnings: " + ParkingLot.earnings)
  println("Current exit receipt: " + ParkingLot.currentExitReceipt.slotNumber,
    ParkingLot.currentExitReceipt.vehicleNumber, ParkingLot.currentExitReceipt.fee)
}
