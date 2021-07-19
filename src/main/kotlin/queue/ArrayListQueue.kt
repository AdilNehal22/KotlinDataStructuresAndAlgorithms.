package queue

class ArrayListQueue<T>: Queue<T> {
    private  val list = arrayListOf<T>()

    /*peek: Returns the element at the front of the queue without removing it.*/
    override fun peek(): Any? {
        return if(list.isEmpty()) null else list.getOrNull(0)
    }

    /*enqueue: Inserts an element at the back of the queue and returns true if the
    operation is successful.*/
    override fun enqueue(element: T): Boolean {
        return list.add(element)
    }

    /*dequeue: Removes the element at the front of the queue and returns it.*/
    override fun dequeue(): T? {
        return list.removeAt(0)
    }
    //Get the size of the queue using the same property of the list.
    override val count: Int
        get() = list.size

    override fun toString(): String = list.toString()


}

fun main(){
    val queue = ArrayListQueue<String>()
    queue.enqueue("Adil")
    queue.enqueue("Ibrahim")
    queue.enqueue("ford")
    println(queue)
}

/*You’ve seen how easy it is to implement a list-based queue by leveraging a Kotlin
ArrayList. Enqueue is very fast, thanks to an O(1) append operation.
There are some shortcomings to the implementation. Removing an item from the
front of the queue can be inefficient, as removal causes all elements to shift up by
one. This makes a difference for very large queues. Once the list gets full, it has to
resize and may have unused space. This could increase your memory footprint over
time. Is it possible to address these shortcomings? Let’s look at a linked list-based
implementation and compare it to an ArrayListQueue.*/
