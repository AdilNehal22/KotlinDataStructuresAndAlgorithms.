package binarySearch

    fun <T : Comparable<T>> ArrayList<T>.binarySearch(myValue: Int, array: ArrayList<Int>): Int {
        var low = 0
        var high = array.size-1
        while (low<=high){
            val middle = (low+high)/2
            when {
                myValue == array[middle] -> return array[middle]
                myValue<array[middle] -> high = middle-1
                else -> low = middle+1
            }
        }
        return -1
    }


fun main() {

    val array = arrayListOf<Int>(11,12,13,14,15,16,17,18,19,20)
    println(array.binarySearch(19))




}