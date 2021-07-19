package heaps

interface Collection<T: Any> {
    val count: Int

    val isEmpty: Boolean
    get() = count == 0

    fun insert(element: T)
    fun remove(index: Int):T?
    fun remove(): T?
}