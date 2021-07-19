package n2sortingAlgos

/**1. There’s no need to sort the collection when it has less than two elements. One
element is sorted by itself; zero elements don’t require an order.
2. A single-pass will bubble the largest value to the end of the collection. Every pass
needs to compare one less value than in the previous pass, so you shorten the
array by one with each pass.
3. This loop performs a single pass starting from the first element and going up
until the last element not already sorted. It compares every element with the
adjacent value.
4. Next, the algorithm swaps the values if needed and marks this in swapped. This is
important later because it’ll allow you to exit the sort as early as you can detect
the list is sorted.
5. If no values were swapped this pass, the collection is assumed sorted, and you
can exit early.*/



    fun <T: Comparable<T>> MutableList<T>.bubbleSort(){
        //1
        if (this.size<2) return
        //2
        for (end in this.lastIndex downTo 1){
            var swapped = false
            //3
            for (current in 0 until end){
                //4
                if (this[current]>this[current+1]){
                    this.swapAt(current, current+1)
                    swapped = true
                }
            }
            //6
            if (!swapped) return
        }
    }


fun main(){

    val list = arrayListOf(9, 4, 10, 3)
    list.bubbleSort()
    println(list)

}