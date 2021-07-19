package stack

interface Stack2<Element> {

    fun push(element: Element)

    fun pop():Element?

    fun peek():Element?
    val count: Int
        get
    val isEmpty:Boolean
        get() = count == 0
}

class Stack02<T: Any>(): Stack2<T> {

    private val storage = arrayListOf<T>()

    override fun toString() = buildString {
        println("----TOP----")
        storage.asReversed().forEach(){println("$it")}
        println("-----------")
    }

    override fun push(element: T) {
        storage.add(element)
    }

    override fun pop(): T? {
        if (isEmpty) return null
        return storage.removeAt(count-1)
    }

    override fun peek(): T? {
        return storage.lastOrNull()
    }

    override val count: Int
        get() = storage.size

    companion object{
        fun <Element: Any> create(items: Iterable<Element>): Stack2<Element> {
            val stack = Stack02<Element>()
            for(item in items){
                stack.push(item)
            }
            return stack
        }
    }

}

/**fun <Element> stackOf(vararg elements: Element): Stack2<Element> {
    return Stack02.create(elements.asList())
}*/