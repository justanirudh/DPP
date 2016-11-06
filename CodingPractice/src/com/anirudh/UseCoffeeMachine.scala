package com.anirudh

/**
  * Created by anirudh on 2/7/16.
  */


object UseCoffeeMachine extends App {

  class Coffee(val brand:String, var quantity:Int, var crushed:Boolean)
  class Milk(val brand:String, var quantity:Int, val fatPercent:Double)

  class CoffeeMachine(val coffee:Coffee, val milk:Milk) {

    class Froth(val quantity:Int)
    class Cappuchino(val coffee:Coffee, val milk:Milk, val froth:Froth){
      assert(coffee.crushed, "Coffee is not crushed")
      assert(coffee.quantity/milk.quantity == 50/30, "Coffee to milk proportions are wrong")
      assert(coffee.quantity/froth.quantity == 50/20, "Coffee to froth proportions are wrong")
    }

    private def crushBeans(coffee:Coffee, weight:Int):Coffee = {
      coffee.quantity = coffee.quantity - weight
      new Coffee(coffee.brand, weight, true)
    }

    private def createFroth(milk:Milk, weight:Int):Froth = {
      milk.quantity = milk.quantity - weight
      new Froth(weight)
    }

    def makeCappuchino: Cappuchino = {
      val crushedBeans = crushBeans(coffee, 50)
      val froth = createFroth(milk,20)
      new Cappuchino(crushedBeans, new Milk(milk.brand, 30, milk.fatPercent), froth)
    }

  }

  val cm = new CoffeeMachine(new Coffee("Nescafe", 1000, false), new Milk("Nandini", 1000, 0.2))
  val cc = cm.makeCappuchino
  println(cc.coffee.quantity, cc.milk.quantity, cc.froth.quantity)
  println(cm.coffee.quantity, cm.milk.quantity)
  val cc2 = cm.makeCappuchino
  println(cc2.coffee.quantity, cc2.milk.quantity, cc2.froth.quantity)
  println(cm.coffee.quantity, cm.milk.quantity)
}