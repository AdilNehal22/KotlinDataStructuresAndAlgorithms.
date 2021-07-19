package linkedList

import java.lang.IndexOutOfBoundsException
/*first give the reference of your list in the primary constructor */
/**/
class LinkedListIterator<T>(private val list: LinkedList<T>): Iterator<T>, MutableIterator<T> {

    private var index = 0
    private var lastNode: LinkedList.Node<T>? = null
/*tells that if your iterator has values left to read you need to keep track of the position
that iterator has in collection so create an index field^*/
    override fun hasNext(): Boolean {
    /*now you can easily check if the position you're at is less than the total no. of nodes */
        return index<list.size
    }
/*for next() the one that reads the values of the nodes you can use another property
to help you out. you'll want to keep the track of last node to get the value of next node, so
make the field lastnode ^ */
    override fun next(): T {
    /*first check if there are still the elements to return  if aren't so throw exception*/
        if (index>=list.size) throw IndexOutOfBoundsException()
/*if yes then you iterate but on first iteration there will be no lastnode set, so you
 take the first node. After first iteration now you got the next property of lastnode to find next one*/
        lastNode = if (index == 0){
            list.nodeAt(0)
        }else
        {lastNode?.nextNode}
/*as lastnode getting update so also inc index*/
        index++
        return lastNode!!.nodeValue
    }

    override fun remove() {
        if (index == 1) {
            list.pop()
        }else{
            val previousNode = list.nodeAt(index-2) ?: return
            list.removeAfter(previousNode)
            lastNode = previousNode
        }
        index--
    }

}