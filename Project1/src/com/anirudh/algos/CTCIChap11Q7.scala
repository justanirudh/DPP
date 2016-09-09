object LongestIncreasingSubsequence extends App{
	def findLIS(seq:Seq[Int]):Seq[Int] = {
		val allSubSequences = seq.foldLeft(Seq[Seq[Int]]())((agg, elem) => {
			if(agg.isEmpty)
				agg :+ Seq(elem)
			else{
				if(elem >= agg.last.last){
					val lastSeq = agg.last
					agg.dropRight(1) :+ (lastSeq :+ elem)
				}
				else
					agg :+ Seq(elem)
			}
		})
	allSubSequences.map(ss => (ss, ss.length)).maxBy(_._2)._1
	}
	
	val seq = Seq(13, 14, 10, 11, 12, 9,8,9,23,40,45,10,9,7)
	val subseq = findLIS(seq)
	subseq foreach println
}
