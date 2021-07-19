package queue

/*Queues use FIFO or first in, first out ordering, meaning the first element that was
added will always be the first one removed. Queues are handy when you need to
maintain the order of your elements to process later.*/

interface Queue<T> {

    /*dequeue: Removes the element at the front of the queue and returns it.*/
    fun dequeue(): Any?

    /*enqueue: Inserts an element at the back of the queue and returns true if the
    operation is successful.*/
    fun enqueue(element: T):Boolean

    /*peek: Returns the element at the front of the queue without removing it.*/
    fun peek(): Any?

    val count: Int
    get

    /*isEmpty: Checks if the queue is empty using the count property*/
    val isEmpty: Boolean
    get() = count == 0

    /**Notice that the queue only cares about removal from the front and insertion at the
    back. You don’t need to know what the contents are in between. If you did, you’d
    presumably use an array instead of a Queue.*/

}