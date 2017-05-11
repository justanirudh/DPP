package com.anirudh.general_algos.basics

object BucketSortRadixSort extends App{
	
	def bucketSort[T](input:Seq[(Int, T)]):Seq[(Int, T)] = {
		val bucketsSize = input.maxBy(_._1)._1
		val buckets = (0 to bucketsSize).foldLeft(Seq[Seq[(Int, T)]]())((agg, e) => agg :+ Seq())
		val bucketsFilled = input.foldLeft(buckets){
			case(agg, (k,v)) => agg.updated(k, agg(k) :+ (k,v))
		}
		bucketsFilled.flatten
	}

	//Bucket sort: sorting keys without using any comparisons
	val pairs = Seq((3, "C"), (4, "D"), (4, "DD"), (1, "A"), (2, "B"), (2, "BC"), (5, "E"))
	val sorted = bucketSort(pairs)
	sorted.map(_._2) foreach println

	def radixSort(input:Seq[(Int, Int)]) = {
		//number of passes = number of keys; in this case 2
		val firstPass = bucketSort(input.map(p => (p._2,p._1))).map(p => (p._2,p._1))
		bucketSort(firstPass)
	}

	//Radix sort: Like Bucket Sort, but for the cases when keys are tuples
	val pairs2 = Seq((3, 4), (4, 1), (3, 12), (1, 3), (4, 0), (2, 5), (2, 3))
	val sortedRadix = radixSort(pairs2)
	sortedRadix foreach println

}
