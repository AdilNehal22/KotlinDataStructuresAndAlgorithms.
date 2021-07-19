package queue

import stack.Stack1

class StackQueue<T : Any>:Queue<T> {

/*The idea behind using two stacks is simple. Whenever you enqueue an element, it
goes in the right stack.When you need to dequeue an element,
you reverse the right stack and place it in the left stack so that you
can retrieve the elements using FIFO order.*/

    private val leftStack = Stack1<T>()
    private val rightStack = Stack1<T>()

/**To check if the queue is empty, simply check that both the left and right stack are
empty. This means that there are no elements left to dequeue, and no new elements
have been enqueued to right*/
    override val isEmpty: Boolean
        get() = rightStack.isEmpty && leftStack.isEmpty
/**As you already know, there will be a time when you need to transfer the elements
from the right stack into the left stack. That needs to happen whenever the left stack
is empty.With this code, you pop elements from the right stack and push them into the left
stack. You already know from the previous chapter that stacks work in a LIFO way
(last in, first out). You’ll get them in reversed order without any additional work.*/
    private fun transferElement(){
        var nextElement = rightStack.pop()
        while (nextElement!=null){
            leftStack.push(nextElement)
            nextElement = rightStack.pop()
        }
    }

    override val count: Int
        get() = TODO("Not yet implemented")

/**You know that peeking looks at the top element. If the left stack is not empty, the
element on top of this stack is at the front of the queue.
If the left stack is empty, you use transferElements(). That way,
leftStack.peek() will always return the correct element or null.*/
    override fun peek(): Any? {
        if (leftStack.isEmpty) transferElement()
        return leftStack.peek()
    }

    override fun enqueue(element: T): Boolean {
        rightStack.push(element)
        return true
    }

    override fun dequeue(): Any? {
        if (leftStack.isEmpty) transferElement()
        return leftStack.pop()
    }

}