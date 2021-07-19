package heaps

interface Heap<T: Any>: Collection<T> {
    fun peek(): T?
}