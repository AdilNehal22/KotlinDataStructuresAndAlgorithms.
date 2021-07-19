package queue

import java.util.*

/**Circular Queue is a linear data structure in which the operations are performed based
on FIFO (First In First Out) principle and the last position is connected back to the
first position to make a circle.*/
class RingBufferQueue<T>(val size: Int): Queue<T> {

/**declare an array with the size*/
    private val array: Array<Any?> = Array(size){null}

/**setting the initial values*/
    private var front = -1
    private var rear = -1
    private var capacity = 0

/**the function simply insert an element from back, checking if list is full if not
 then circularly incrementing the tail means the insertion will be from back but if
 the front places are empty then the rear will connect to front making a circle, last cond
 for checking for only the first time that if tail went forward but head remain same so
 inc head*/
    override fun enqueue(element: T): Boolean {
        if(capacity == size ) println("queue is full")
        rear = (rear+1)%size
        array[rear] = element
        capacity++
        if(front==-1) front++
        return true
    }
/**checking if empty if not then first taking the value to dequeue in  a variable then
 making that place null and then circularly inc the head so when rear connected to head
 so it would got at that place and dequeue*/
    override fun dequeue(): Any?{
        if (capacity==0) println("the queue is empty")
        val dequeuedElement = array[front]
        array[front] = null
        front = (front+1)%size
        capacity--
        return dequeuedElement
    }

    override fun peek(): Any = array[front]!!


    override val count: Int
        get() = capacity

    override fun toString(): String {
        return "CircularQueue " + array.contentToString() + ""
    }

}

fun main(){
    val queue = RingBufferQueue<Int>(4)
     queue.enqueue(1)
     queue.enqueue(2)
     queue.enqueue(3)
     queue.enqueue(4)
     println(queue.peek())
    println(queue)

}