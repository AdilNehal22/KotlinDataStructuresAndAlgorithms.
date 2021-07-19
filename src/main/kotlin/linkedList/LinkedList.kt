package linkedList

/*Kotlin Collection Interfaces->ITERABLE provides sequential access to the elements of the list
 via iterator(for loop)*/
/*COLLECTION->tells if a list contain element or collection of element, Collection is also
the iterable so you can also remove the iterator*/
/*MUTABLEITERATOR->allows items to be removed from list. But if you add this this interface to the
linkedlist then there will be error that iterator never return mutable iterator so implement this
in linkedlistiterator*/
public class LinkedList<T>: Iterable<T>, Collection<T>, MutableIterable<T>, MutableCollection<T>{

    data class Node<T>(var nodeValue: T, var  nextNode: Node<T>? = null ){
        override fun toString(): String {
            return "$nodeValue -> ${nextNode.toString()?:"$nodeValue"}"
        }
    }

    private var head: Node <T>? = null
    private var tail: Node<T>? = null
    /*to implement the iterable first make the size property available outside the class
     size will be public but its setter will be private*/

    /*we already made the field size and isEmpty fun so we just write override to implement
    collection*/
    override var size: Int = 0
        private set

    override fun isEmpty():Boolean{
        return size==0
    }

    override fun toString(): String {
        if (isEmpty()) {
            return "Is Empty"
        }else{return head.toString()}
    }

    /*for pushing node means adding the element at the front of LL aka
    head node pushing the first will be the head*/
    fun push(nodeValue: T):LinkedList<T>{
        head = Node(nodeValue, nextNode = head)
        if (tail == null) tail = head
        size++
        return this
    }

    /*for appending node means adding the node at last of linked list and that will
    also be the tail of LL aka tail end pushing*/
    private fun append(nodeValue: T){
        //same implementation if list is empty so better to use push()
        /*tail = Node(nodeValue, nextNode = tail)
        if (head == null) tail = head*/
        if (isEmpty()){
            push(nodeValue)
            return
        }
        /*It's just linking the current tail to a newly created node */
        tail?.nextNode = Node(nodeValue)
       // and then setting that same newly created node as the new current tail.
        tail = tail?.nextNode
    }

    /*for node insertion first to find the node where you want to insert new node */
    fun nodeAt(index: Int):Node<T>?{
        /*just make the head node as reference for counting the no of node, from the head*/
        var currentNode: Node<T>? = head
        var currentIndex = 0
        while (currentNode!=null && currentIndex<index){
            /*simply making the next node the current node until reaches the index*/
            currentNode = currentNode.nextNode
            currentIndex++
        }
        return currentNode
    }
/*this function simply insert a new node after the given node*/
    fun insert(value: T, afterNode: Node<T>):Node<T>{
    /*first checking if tail is afterNode if yes! the simply appending the value
    and making the new node as the new tail, the function append doing it*/
        if (tail == afterNode){
            append(value)
            return tail!!
        }
    /*if not the tail then making the new node and making this new node 'next the after Node'*/
        val newNode = Node(value, afterNode.nextNode)
    /*making what is next afterNode(newly created node) as the new node*/
        afterNode.nextNode = newNode
        size++
        return newNode
    }
    /*the pop operation meaning removing from the front of the list return the popped value
    ,can be optional because list can be empty too*/
    fun pop():T{
        //if list is not empty then dec size
        if(!isEmpty()) size--
            /*simply storing the value to be pop in a result and making the next node as head*/
        val result = head?.nodeValue
        head = head?.nextNode
        /*if list become empty then we will make tail null*/
        if (isEmpty()){
            tail = null
        }
        return result!!
    }

    /*operation for removing last node of the list but for this you have to find the node before the
    node to be removed*/
    fun removeLast():T?{
        //if the head is null, nothing to remove so you return null
        val head = head?: return null
        //if there's only one node remove last functionality would be equal to pop function
        //and it will also update head and tail references
        if (head.nextNode == null) return pop()
        size--
        //searching for the next node until current.next is null, tells current is the last node
        var previous = head
        var current = head
        var next = current.nextNode
        while (next!=null){
            previous = current
            current = next
            next = current.nextNode
        }
        /*since current is the last node you disconnect it using the previous.next = null
        reference you also update tail reference*/
        previous.nextNode = null
        tail = previous
        return  current.nodeValue
    }

    fun removeAfter(beforeNode: Node<T>):T?{
        /*storing the value of node after given node in a result*/
        val result = beforeNode.nextNode?.nodeValue
        /*if given node's after node is tail then make given node = tail*/
        if (beforeNode.nextNode == tail){
            tail = beforeNode
        }
        /*if given node's next node is not null then dec size of list */
        if (beforeNode.nextNode != null){
            size--
        }
        /*making the given node's next node's next node, given node's next node*/
        beforeNode.nextNode = beforeNode.nextNode?.nextNode
        return  result
    }
/*since we are implementing the interface iterable so we need to also define the methods
by overriding it */
    override fun iterator(): MutableIterator<T>{
        return LinkedListIterator(this)
    }
/*now compiler complains what is LinkedListIterator so now in new file make the class
LinkedListIterator and their implement the interface iterator and override its function*/

    /*we still need to implement two more Collection methods we will make one to complete another*/
//returns true if element is in the list
    override fun contains(element: T): Boolean {
        for (item in this){
            if (item==element) return true
        }
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (i in elements){
            if (!contains(i)) return false
        }
        return true
    }
    /*implementing mutablecollection functions */

    override fun add(element: T): Boolean {
        append(element)
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (element in elements){
            append(element)
        }
        return true
    }

    override fun clear() {
        head = null
        tail = null
        size = 0
    }
/*Since the LinkedList doesn’t have a fixed size, add() and addAll() are always
successful and need to return true. For the removal of elements, you’ll use a
different approach for iterating through your MutableIterable linked list. This way,
you can benefit from your MutableIterator*/
    override fun remove(element: T): Boolean {
    //getting an iterator to iterate through the list
        val iterator = iterator()
    //Create a loop that checks if there are any elements left, and gets the next one.
        while (iterator.hasNext()){
            val item = iterator.next()
            //Check if the current element is the one you’re looking for, and if it is, remove it.
            if (item==element){
                iterator.remove()
                return true
            }
        }
    //Return a boolean that signals if an element has been removed.
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
        val result = false
        val iterator = iterator()
        while (iterator.hasNext()){
            val item = iterator.next()
            if (!elements.contains(item)){
                iterator.remove()
                return true
            }
        }
        return false
    }

}
fun main(){

    val list:MutableCollection<Int> = LinkedList<Int>()

    list.add(1)
    list.add(2)
    list.add(3)
    list.add(4)
    list.removeAll(list)

}