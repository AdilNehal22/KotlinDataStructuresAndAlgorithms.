package stack

import linkedList.LinkedList3


/*The stack data structure is identical, in concept, to a physical stack of objects. When
you add an item to a stack, you place it on top of the stack. When you remove an
item from a stack, you always remove the top-most item.*/

interface Stack<T> {

    // Adding an element to the top of the stack.
    fun push(element: T)

    //Removing the top element of the stack.
    fun pop(): T?

    /*you can only add or remove elements from one side of the data
    structure.In computer science, a stack is known as the LIFO (last-in first-out) data
    structure. Elements that are pushed in last are the first ones to be popped out.*/

    /*Android uses the fragment stack to push and pop fragments into and out of an
    Activity*/

    /*peek is an operation that’s often attributed to the stack interface. The idea of peek is
    to look at the top element of the stack without mutating its contents. The count
    property returns the number of element into the Stack and it's used to implement
    the isEmpty property*/

    fun peek(): T?
    val count: Int


    val isEmpty: Boolean
    /*custom getter, it will be called every time
    you access the property (this way you can implement a computed property)*/
    get() = count == 0

}

    class Stack1<T : Any>(): Stack<T> {


        /*You can implement your Stack interface in different ways and choosing the right
storage type is important. The ArrayList is an obvious choice since it offers
constant time insertions and deletions at one end via add and removeAt with the last
index as a parameter. Usage of these two operations will facilitate the LIFO nature of
stacks.*/
        private val storage = arrayListOf<T>()

        //String builder can be used to efficiently perform multiple string manipulation operations.
        override fun toString() = buildString {
            println("---top---")
            storage.asReversed().forEach {
                println("$it")
            }
            println("---------")
        }

        override fun pop(): T? {
            if (isEmpty) return null
            return storage.removeAt(count - 1)
        }

        override fun push(element: T) {
            storage.add(element)
        }

        override fun peek(): T? {
            return storage.lastOrNull()
        }

        override val count: Int
            get() = storage.size

/*You might want to take an existing list and convert it to a stack so that the access
order is guaranteed. Of course, it would be possible to loop through the array
elements and push each element. However, you can write a static factory method
that directly adds these elements to the Stack implementation.*/

        companion object {
            fun <T : Any> create(items: Iterable<T>): Stack1<T> {
                val stack = Stack1<T>()
                for (item in items) {
                    stack.push(item)
                }
                return stack
            }
        }

        /**function that takes LinkedList as a parameter
        and convert that parameter into a Stack! */
        fun <T : Any> linkListToStack(list3: LinkedList3<T>): Stack1<Any> {
            val stack = Stack1<Any>()
            for (node in list3.indices) {
                if (list3.isEmpty()) return stack
                val item = list3.pop()
                stack.push(item)
            }
            return stack
        }


        fun <T : Any> parenthesesCheck(string: String):Boolean {
            val stack = Stack1<Any>()
            var count1 = 0
            var count2 = 0

            for (item in string) {
                if (item == '(' || item == ')') {
                    stack.push(item)
                    val peek = stack.peek()
                    if(peek == '(')
                        count1++
                    else if(peek == ')')
                        count2++
                }
            }
            return count1 == count2
        }
    }

    /* make your stack initializable by listing its elements,
    similar to listOf() and other standard library collection factory functions.
    Add this to Stack.kt, outside the Stack class definition*/

fun <T : Any> stackOf(vararg elements: T): Stack<T> {
    return Stack1.create(elements.asList())
}

fun main(){
   val stack = Stack1<Int>()
    stack.push(1)
    stack.push(2)
    stack.push(3)

    val string = "((A)dil))"
    println(stack.parenthesesCheck<String>(string))

}
