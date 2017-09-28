package com.anirudh.general_algos.basics

object MergeSort extends App{

def merge(left:Seq[Int], right:Seq[Int], inversions:Int):(Seq[Int], Int) = {
	(left, right) match {
		case (Nil, Nil) => (Nil, inversions)
		case (x, Nil) => (x,inversions)
		case (Nil, y) => (y, inversions)
		case (x::xx, y::yy) => {
			if(x <= y){
				val (seq, inv) = merge(xx, right, inversions)
				(x +: seq, inv)
			}
			else{
				val (seq, inv) = merge(left, yy, inversions + left.size)
				(y +: seq, inv)
			}
		}
	}	
}



def mergeSort(array:Seq[Int], inversions:Int):(Seq[Int], Int) = {

	if(array.isEmpty || array.length == 1)
		(array, inversions)
	else{
		val half = array.length/2
		val (left, right) = array.splitAt(half)
		val (sortedLeft, inv1) = mergeSort(left, inversions)
		val (sortedRight, inv2) = mergeSort(right, inv1)
		merge(sortedLeft, sortedRight, inv2)
	}
	
}

val array = Seq(10,9,8,7,6,5,4,3,2,1)

val (sortedArray, inversions) = mergeSort(array, 0)

sortedArray foreach println

println("Inversions: " + inversions)

}
