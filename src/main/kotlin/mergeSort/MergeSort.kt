package mergeSort

fun <T: Comparable<T>> List<T>.mergeSort():List<T>{

    if (this.size < 2) return this
    val middle = this.size/2
    val leftSubList = this.subList(0, middle).mergeSort()
    val rightSubList = this.subList(middle, this.size).mergeSort()

    return merge(leftSubList, rightSubList)

}

private fun<T: Comparable<T>> merge(leftSubList: List<T>, rightSubList: List<T>): List<T>{

    var leftIndex = 0
    var rightIndex = 0
    val result:MutableList<T> = mutableListOf()

    while (leftIndex < leftSubList.size && rightIndex < rightSubList.size){
        val leftElement = leftSubList[leftIndex]
        val rightElement = rightSubList[rightIndex]
        if (leftElement < rightElement){
            result.add(leftElement)
            leftIndex++
        }
        else if(rightElement < leftElement){
            result.add(rightElement)
            rightIndex++
        }
        else{
            result.add(leftElement) ; leftIndex++
            result.add(rightElement) ; rightIndex++
        }
        if (leftIndex < leftSubList.size){
            result.addAll(leftSubList.subList(leftIndex, leftSubList.size))
        }
        if (rightIndex < rightSubList.size){
            result.addAll(rightSubList.subList(rightIndex, rightSubList.size))
        }
    }
    return result
}
