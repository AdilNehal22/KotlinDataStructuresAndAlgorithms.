package priorityQueue

import heaps.ComparableHeapImpl
import heaps.ComparatorHeapImpl
import heaps.Heap
/**AbstractPriorityQueue implements the Queue interface and is generic in the
type T. It’s an abstract class because you want to manage comparison using either
Comparable<T> objects or an external Comparator<T> implementation.*/

abstract class AbstractPriorityQueue<T: Any>:Queue<T> {

    abstract val heap: Heap<T>

    override fun enqueue(element: T): Boolean {
        heap.insert(element)
        return true
    }

    override fun dequeue(): T? = heap.remove()

    override val count: Int
        get() = heap.count

    override fun peek(): T? = heap.peek()

    class ComparablePriorityQueueImpl<T : Comparable<T>> :
        AbstractPriorityQueue<T>() {
        override val heap = ComparableHeapImpl<T>()
    }

    class ComparatorPriorityQueueImpl<T: Any>(private val comparator: Comparator<T>):
            AbstractPriorityQueue<T>() {
        override val heap = ComparatorHeapImpl(comparator)
    }

}

fun main(){
    val stringLengthComparator = Comparator<String>{ o1, o2 ->
            val length1 = o1?.length ?: -1
            val length2 = o2?.length ?: -1
            length1 - length2
    }
    val priorityQueue = AbstractPriorityQueue.ComparatorPriorityQueueImpl(stringLengthComparator)

    arrayListOf<String>("one", "two", "three", "forty", "five", "six",
        "seven", "eight", "nine").forEach {
        priorityQueue.enqueue(it)
    }

    while (!priorityQueue.isEmpty)
        println(priorityQueue.dequeue())
}