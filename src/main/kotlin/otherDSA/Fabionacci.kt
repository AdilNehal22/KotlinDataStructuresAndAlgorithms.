package main.kotlin
import java.util.*

fun giveFibonacci(numb: Int): Long {
    val array = LongArray(numb)
    var sum: Long? = null

    array[0] = 0
    array[1] = 1

    for (i in 2 until numb){
        array[i] = array[i-1]+array[i-2]
    }
    return array[array.size-1]

}

fun main(){
    val scanner = Scanner(System.`in`)
    val number = scanner.nextInt()
    println(giveFibonacci(number))

}