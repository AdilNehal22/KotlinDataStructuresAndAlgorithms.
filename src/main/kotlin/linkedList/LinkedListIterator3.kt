package main.kotlin.LinkedList

import linkedList.LinkedList3

class LinkedListIterator3<T>(private val list1: LinkedList3<T>):Iterator<T>, MutableIterator<T>{
    private var index = 0
    private var lastNode: LinkedList3.Node<T>? = null

    override fun hasNext(): Boolean {
        return index<list1.size
    }

    override fun next(): T {
        if (index>list1.size) throw IndexOutOfBoundsException()
        lastNode = if (index==0){
            list1.nodeAt(0)
        }else{
            lastNode?.nextNode
        }
        index++
        return lastNode!!.nodeValue
    }

    override fun remove() {
        if (index == 1){
            list1.pop()
        }
        val previousNode = list1.nodeAt(index-2)
        list1.removeAfter(previousNode)
        lastNode = previousNode
        index--
    }
}