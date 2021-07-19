package heaps

import java.util.*
import java.util.stream.Collectors
import kotlin.Comparator
import kotlin.collections.ArrayList

abstract class AbstractHeap<T: Any>(): Heap<T> {

    abstract fun compare(a: T, b: T): Int
    var elements: ArrayList<T> = ArrayList<T>()

    override val count: Int
        get() = elements.size

    override fun peek(): T? = elements.firstOrNull()

    private fun leftHandChild(index: Int) = (2*index) + 1
    private fun rightHandChild(index: Int) = (2*index) + 2
    private fun parent(index: Int) = (index-1)/2

    override fun insert(element: T) {
        elements.add(element)
        siftUp(count-1)
    }
    private fun siftUp(index: Int){
        var child = index
        var parent = parent(child)
        while (child>0 && compare(elements[child], elements[parent])>0){
            Collections.swap(elements, child, parent)
            child = parent
            parent = parent(child)
        }
    }

    override fun remove(): T? {
        if (isEmpty) return null
        Collections.swap(elements,0, count-1)
        val removedItem = elements.removeAt(count-1)
        siftDown(0)
        return removedItem
    }

    private fun siftDown(index: Int){
        var parent = index
        while (true){
            val leftChildIndex = leftHandChild(parent)
            val rightChildIndex = rightHandChild(parent)
            var candidate = parent

            if (leftChildIndex<count && compare(elements[candidate], elements[leftChildIndex])>0)
                candidate = leftChildIndex

            if (rightChildIndex<count && compare(elements[candidate], elements[rightChildIndex])>0)
                candidate = rightChildIndex

            if (candidate == parent)
                return

            Collections.swap(elements,parent, candidate)
            parent = candidate
        }
    }

    override fun remove(index: Int): T? {
        if (index>=count) return null
        return if (index == count-1){
            elements.removeAt(count-1)
        }else{
            Collections.swap(elements,index, count-1)
            val removedItem = elements.removeAt(count-1)
            siftUp(index)
            siftDown(index)
            removedItem
        }
    }

    private fun index(element: T, i: Int): Int?{
        if (i>=count)
            return null
        if (compare(element, elements[i])>0)
            return null
        if (element == elements[i])
            return i
        val leftChildIndex = index(element, leftHandChild(i))
        if (leftChildIndex!=null) return  leftChildIndex
        val rightChildIndex = index(element, rightHandChild(i))
        if (rightChildIndex!=null) return rightChildIndex
        return null
    }
    /**If a non-empty array is provided, you use this as the elements for the heap. To satisfy
    the heap’s property, you loop through the array backward, starting from the first
    non-leaf node, and sift down all parent nodes.
    You loop through only half of the elements because there’s no point in sifting down
    leaf nodes, only parent nodes.
     'number of parents = total no of elements/2' */

    protected fun heapify(values: ArrayList<T>){
        elements = values
        if (!elements.isEmpty()){
            (count/2 downTo 0).forEach {
                siftDown(it)
            }
        }
    }


}

fun main(){

}

class ComparableHeapImpl<T: Comparable<T>>(): AbstractHeap<T>(){
    override fun compare(a: T, b: T): Int = a.compareTo(b)


    companion object{
        fun <T: Comparable<T>> create(elements: ArrayList<T>): Heap<T>{
            val heap = ComparableHeapImpl<T>()
            heap.heapify(elements)
            return heap
        }
    }
}
class ComparatorHeapImpl<T: Any>(
    private val comparator: Comparator<T>) : AbstractHeap<T>() {

    override fun compare(a: T, b: T): Int =
        comparator.compare(a, b)

    companion object{
        fun <T : Any> create(elements: ArrayList<T>, comparator: Comparator<T>):Heap<T>{
            val heap = ComparatorHeapImpl(comparator)
            heap.heapify(elements)
            return heap
        }
    }
}
