object HeapSort extends App{
	
	def heapify(seq:Seq[Int], root:Int):Seq[Int] = {
		val n = seq.length
		val leftI = 2*root + 1
		val rightI = 2*root + 2
		val largestILeft = if(leftI < n && seq(leftI) > seq(root)) leftI else root
		val largestI = if(rightI < n && seq(rightI) > seq(largestILeft)) rightI else largestILeft

 
		/*if(leftI >= n && rightI >= n)
			root
		else if (leftI < n && rightI >= n)
			Seq((root, seq(root)),(leftI, seq(leftI))).maxBy(_._2)._1
		else if (leftI >= n && rightI < n)
			Seq((root, seq(root)),(rightI, seq(rightI))).maxBy(_._2)._1
		else
			Seq((root, seq(root)),(leftI, seq(leftI)),(rightI, seq(rightI))).maxBy(_._2)._1*/

		if(largestI == root)
			seq
		else{
			val newSeq = seq.updated(root, seq(largestI))
			val newSeq2 = newSeq.updated(largestI, seq(root))
			heapify(newSeq2, largestI) 
		}
	}

	def heapSort(input: Seq[Int]) = {
		val n = input.length
		val heap = ((n/2 -1) to 0 by -1).foldLeft(input)((agg, index) => heapify(input, index))		
		val sorted = (n-1 to 0 by -1).foldLeft(heap)((agg, lastI) => {
			val newSeq = agg.updated(0, agg(lastI))
			heapify(newSeq.dropRight(1), 0) :+ agg(0)
		})
		sorted
	}

	val shortInput = Seq(3,2,4,1, 10)
	val input = Seq(5,3,7,2,10,7,9,8,5,1,6,2)
	val sortedInput = heapSort(shortInput)
	//val heap = heapify(shortInput, 0)
	sortedInput foreach println
	//heap foreach println
}
