package n2sortingAlgos


    fun <T> MutableList<T>.swapAt(first: Int, second: Int){
        val temp = this[first]
        this[first] = this[second]
        this[second] = temp
    }
