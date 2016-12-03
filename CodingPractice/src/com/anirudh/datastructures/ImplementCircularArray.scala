package com.anirudh.datastructures

/**
  * Created by anirudh on 19/7/16.
  */
//CTCI Problem no.14.6
object ImplementCircularArray {

  //Can be more efficiently made by using a circular linked list and just moving the head
  //Extension from Iterator anf Iterable reqd for the syntax of 'for(elem <- CircularArray)' to work
  class CircularArray[T](val container:T*) extends Iterable[T] {
    def add[T](elem:T) = new CircularArray(container :+ elem:_*)

    def printArray() = println(container.mkString(","))
    //1 is right, 0 is left
    def rotate(isRight:Boolean, times:Int) = {
      val size = container.length
      val realTimes = times % size
      val (first, second) = if(isRight) container.splitAt(size - realTimes)
      else container.splitAt(realTimes)
      new CircularArray(second ++ first:_*)
    }

    class CircularArrayIterator[K](array:CircularArray[K]) extends Iterator[K] {
      var current = -1
      val container = array.container
      val containerLength = container.length

      def hasNext() = current < containerLength - 1
      def next() = {
        current = current + 1
        val realCurrent = current % containerLength
        container(realCurrent)
      }
      def remove() = throw new Exception()
    }

    def iterator() = {
      new CircularArrayIterator[T](this)
    }
  }

  def main(args:Array[String]) = {
    val a = new CircularArray()
    val b = new CircularArray(1,2,3)
    val a2 = a.add("foo").add("lem")
    val b2 = b.add(5).add(6)
    a2.printArray()
    b2.printArray()
    val b2RotLeft = b2.rotate(false, 2)
    b2RotLeft.printArray()
    val b2RotRight = b2.rotate(true, 12)
    b2RotRight.printArray()
    for(elem <- b2){
      println(elem)
    }
  }
}
