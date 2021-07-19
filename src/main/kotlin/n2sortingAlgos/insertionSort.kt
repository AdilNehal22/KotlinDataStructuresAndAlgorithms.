package n2sortingAlgos
/**
 1.the first loop is starting from the [1] of array i.e after our sorted part
 2. the second loop is starting from where the current is starting but traverse in reverse order.
 3. checking if array[i] is lesser than array[i-1] if yes than swap than see if there are further
 elements to compare [i] with and then swap
 4. when no element is left the loop will break.*/

fun <T: Comparable<T>> MutableList<T>.insertionSort(){
    if(this.size < 2) return
    for(current in 1 until this.size){
        for(shifting in (1..current).reversed()){
            if (this[shifting]<this[shifting-1]){
                this.swapAt(shifting, shifting-1)
            }else{
                break
            }
        }
        println(this)
    }
}

fun main(){
    val array = arrayListOf(9,4,3,8)
    println(array.insertionSort())
}