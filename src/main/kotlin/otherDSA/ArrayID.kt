package main.kotlin
//1D array implementation
fun addElement(array: IntArray, position: Int, value: Int):IntArray{

    val newArray = IntArray(array.size+1)


    for (i in 0 until position){
        newArray[i] = array[i]
    }

    newArray[position] = value

    for(i in position+1 until newArray.size){
        newArray[i] = array[i-1]
    }

    return newArray

}

fun search(arr: IntArray, value: Int):Int?{
    var location: Int = 0
    for (i in 0..arr.size){
        if (value == arr[i]){
            location = i
            break
        }
    }
    return location
}

fun delete(array: IntArray, value: Int):IntArray{
    val result = IntArray(array.size-1)
    for (i in array.indices){
        if (value==array[i]){
            for (j in 0 until array[i-1]){
                result[j] = array[j]
            }
            for (j in array[i-1] until result.size){
                result[j] = array[j+1]
            }
        }
    }
    return result
}


fun main(){
    //add()
    /**val array: IntArray = addElement(intArrayOf(1,2,3,4,5),4,10)

    for(i in array.indices) println(array[i])
    println()
    //search()
    println(search(array,10))
    //delete()
    val array: IntArray = delete(intArrayOf(1,2,3,4,10,5),10)
    for (i in array.indices) println(array[i])*/




}

