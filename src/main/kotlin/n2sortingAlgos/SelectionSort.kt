package n2sortingAlgos
/**
 1. first setting the first[0] element(current) as lowest no matter either it is or not.
 2. then starting from after current and compare if every number after current is smaller
     than lowest if yes, then this number will be new lowest.
 3. then swap new lowest with the current
 4. and loop continues.*/

fun <T: Comparable<T>> MutableList<T>.selectionSort() {
    if (this.size < 2) return
    for (current in 0 until this.lastIndex) {
        var lowest = current
        for (other in (current + 1) until this.size) {
            if (this[other] < this[lowest])
                lowest = other
        }
        if (lowest!=current)
            this.swapAt(lowest,current)
    }
    println(this)
}

fun main(){
    val array = arrayListOf(8,7,0,4,6)
    println(array.selectionSort())
}

