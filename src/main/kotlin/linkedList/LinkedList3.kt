package linkedList

import main.kotlin.LinkedList.LinkedListIterator3

public class LinkedList3<T>:Iterable<T>, Collection<T>, MutableIterable<T>, MutableCollection<T>{

    data class Node<T>(var nodeValue: T, var nextNode: Node<T>? = null){

        override fun toString(): String {
            return if (nextNode!=null){
                "$nodeValue -> ${nextNode.toString()}"
            }else{
                "$nodeValue"
            }
        }




    }

     var head: Node<T>? = null
     get
    private var tail: Node<T>? = null

    override var size = 0
        private set

    override fun isEmpty():Boolean{
        return size == 0
    }

    override fun toString(): String {
        if (isEmpty()){
            return ("List is empty")
        }else{
            return head.toString()
        }
    }
    operator fun get(index: Int): T? {
        var count = 0
        var currNode = head
        while (currNode!=null){
            if (count==index)
                return currNode.nodeValue
            count++
            currNode=currNode.nextNode
        }
        return currNode?.nodeValue
    }

    //for adding

    private fun push(nodeValue:T):LinkedList3<T>{
        head = Node(nodeValue,nextNode = head)
        /*if (tail==null) {
            head = tail
        }*/

        size++
        return this
    }

    fun append(nodeValue: T){
        if (isEmpty()) {
            push(nodeValue)
            //size++
            return
        }
        else if(tail!=null){
            tail?.nextNode = Node(nodeValue)
            tail = tail?.nextNode
            size++
            return
        }
        else {
            head?.nextNode = Node(nodeValue)
            tail = head?.nextNode
            size++
            return
        }
    }

    fun nodeAt(index:Int):Node<T>{
        var currentNode = head
        var currentIndex = 0
        while (currentIndex<index){
            currentNode = currentNode?.nextNode
            currentIndex++
        }
        return currentNode!!
    }

    fun insert(nodeValue: T, afterNode:Node<T>):Node<T>{
        if (tail==afterNode){
            append(nodeValue)
            return tail!!
        }
        afterNode.nextNode = Node(nodeValue)
        size++
        return afterNode.nextNode!!
    }

    //remove operations

    fun pop():T{
        if (!isEmpty()) size--
        val result = head?.nodeValue
        head = head?.nextNode
        if(isEmpty()){tail = null}
        return result!!
    }

    fun removeLast():T?{
        val head = head?: return null
        if (head.nextNode == null){pop()}
        var previous = head
        var current = head
        var next = current.nextNode
        while (next!=null){
            previous = current
            current = next
            next = current.nextNode
        }
        previous.nextNode = null
        tail = previous
        return current.nodeValue
    }

    fun removeAfter(beforeNode: Node<T>):T{
        val result = beforeNode.nodeValue
        if (beforeNode.nextNode == tail){
            tail = beforeNode
        }
        if (beforeNode.nextNode!=null){
            size--
        }
        beforeNode.nextNode = beforeNode.nextNode?.nextNode
        return  result
    }

    override fun iterator(): MutableIterator<T> {
        return LinkedListIterator3(this)
    }

    override fun contains(element: T): Boolean {
        for (item in this){
            if (item==element){
                return true
            }
        }
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (item in elements){
            if (!elements.contains(item)){
                return false
            }
        }
        return true
    }

    override fun add(element: T): Boolean {

        append(element)
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (item in elements){
            append(item)
            return true
        }
        return false
    }

    override fun clear() {
        head = null
        tail = null
        size = 0
    }

    override fun remove(element: T): Boolean {
        val iterator = iterator()
        while(iterator.hasNext()){
            val item = iterator.next()
            if (item == element){
                iterator.remove()
                return true
            }
        }
        return false
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var result = false
        for (item in elements){
            result = remove(item) || result
        }
        return result
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false
        val iterator = this.iterator()
        while (iterator.hasNext()){
            val item = iterator.next()
            if (!elements.contains(item)){
                iterator.remove()
                return true
            }
        }
        return false
    }

        fun reverseList(list1: LinkedList3<T>):LinkedList3<T>{
            var previous: Node<T>? = null
            var next: Node<T>? = head
            var current = head
            while(next!=null){
                next = current?.nextNode
                current?.nextNode = previous
                previous = current
                current = next
            }
            return this
        }

     fun middleNode(list1: LinkedList3<T>): Node<T> {
         val middleValue = (list1.size)/2
         var currentNode = head
         var count = 0
         while(count<middleValue){
             currentNode = currentNode?.nextNode
             count++
         }
         currentNode?.nextNode = null
         return currentNode!!
     }

     fun mergeTwoLists(list1: LinkedList3<T>, list2: LinkedList3<T>):LinkedList3<T>{
         if (list1.isEmpty() || list2.isEmpty()){
             println("your either list is empty")
         }else {
             for (i in list1.indices) {
                 for (j in list2.indices)
                     list1.addAll(list2)
             }
         }
         return this
     }

 }

fun main(){

    val list1 = LinkedList3<Int>()

    list1.add(1)
    list1.add(2)
    list1.add(3)
    list1.append(4)
    list1.append(5)

    val list2 = LinkedList3<Int>()

    list2.add(1)
    list2.add(2)
    list2.add(3)
    list2.add(4)
    list2.add(5)
    list2.append(6)


    list1.mergeTwoLists(list1,list2)



}




