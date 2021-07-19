package queue

import linkedList.DoublyLinkedList

class LinkedListQueue<T>: Queue<T> {
    private val list = DoublyLinkedList<T>()

    private var size = 0

    override val count: Int
        get() = size

    override fun dequeue(): T? {
        return list.deleteAtFirst()
    }

    override fun enqueue(element: T):Boolean {
        return list.addLastNode(element)
    }

    override fun peek(): T? = list.head?.nodeValue

    override fun toString(): String = list.toString()
}

fun main(){
    val queue = LinkedListQueue<String>().apply {
        enqueue("Adil")
        enqueue("Ahmed")
        enqueue("Hasan")
        enqueue("Samrah")
    }
    println(queue)
}

/**The main weakness with LinkedListQueue is not apparent from the table. Despite
O(1) performance, it suffers from high overhead. Each element has to have extra
storage for the forward and back reference. Moreover, every time you create a new
element, it requires a relatively expensive dynamic allocation. By contrast,
ArrayListQueue does bulk allocation, which is faster.*/
