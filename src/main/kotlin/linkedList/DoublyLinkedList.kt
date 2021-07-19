package linkedList

class DoublyLinkedList<T> {
    data class Node<T>(var nodeValue: T, var previousNode:Node<T>? = null, var nextNode:Node<T>? = null){
        override fun toString():String{
            return "$nodeValue -> ${nextNode.toString()?:"$nodeValue"}"
        }
    }

    var head: Node<T>? = null
    get

    private var tail:Node<T>? = null

    private var size = 0

    private fun isEmpty(): Boolean {
        return size == 0
    }

    override fun toString(): String {
        return head.toString()
    }

/**took an index from the user then from starting from head traverse list til
 the last element*/
    fun nodeAt(index: Int):Node<T>{
        var current = head
        var currentIndex = 0
        while(current!=null && currentIndex<index){
            current = current.nextNode
            currentIndex++
        }
        return  current!!
    }
/*makes a new node first then check if list is empty then tail is new node else the previous
of recent head will be new node so the new node's next would become head, means double
referencing head will reference new node as previous node and new node will also reference
head as its next node and last making the new node the head*/
    fun push(nodeValue: T):DoublyLinkedList<T>{
        val newNode = Node(nodeValue)
        if(isEmpty()) tail = newNode
        head?.previousNode = newNode
        newNode.nextNode = head
        head = newNode
        size++
        return this
    }

/**1_first made new node, 2_before inserting a new node after given previous node first make
 previous node's next as the new node's next means what was previous node's next now become
 the new node's next and in between there will be new node. 3_now make previous node's next
 node as the new node. 4_make new node's previous as the given previous node. 5_finally if
 new node's next is not null then new node next node's previous node will be the new node*/
    fun addAfter(nodeValue: T, previousNode: Node<T>){
        val newNode = Node(nodeValue)
        newNode.nextNode = previousNode.nextNode
        previousNode.nextNode = newNode
        newNode.previousNode = previousNode
        if(newNode.nextNode!=null) {
            newNode.nextNode?.previousNode = newNode
        }
        size++
    }

    /**making the new node then checking if list is empty when empty then both head and tail
 will point to the null so new node will be head and if not empty then obviously tail's next
 node will reference the new node and then new node previous node will reference the tail
 and finally we make new node as tail*/
    fun addLastNode(nodeValue: T):Boolean{
        val newNode = Node(nodeValue)
        if (isEmpty()) {
            head = newNode
            tail = head
            size++
            return true
        }else {
            tail?.nextNode = newNode
            newNode.previousNode = tail
        }
            tail = newNode
            size++
            return true
        }

/**first checking that if the list is null so print a message then if the list
 contains only one item then that one item will be both tail and head so making
 tail null means removing it else if there are many item in list then making the recent head's
 next as the new head and making new head previous as null means removing it*/
    fun deleteAtFirst():T{
        if (isEmpty()) { println("List is empty") }
        if (head==tail){ tail = null }
        else {
            head = head?.nextNode
            head?.previousNode = null
        }
        size--
        return head!!.nodeValue
    }
/**the function takes tne node which we want to delete then check if list is empty if not
 then check if head and tail are equal if yes this means that there is only one element
 and if not then it makes node given which simply given node's prev node's next node as
 given node next means what is after given node will be after given node's previous node
 and then simply given node previous node pointing at the given node's after node*/
    fun deleteAfter(node: Node<T>){
        if (isEmpty()){println("list is empty")}
        if (head==tail){
            println("There is only one value in the list")
        }else {
            node.previousNode?.nextNode = node.nextNode
            node.previousNode = node.previousNode?.nextNode
        }
        size--
    }
 /**the function simply check if list is empty if not then checking if head is not null
  means empty list and head's next is null means there's only one element in the list then
  simply making that head null and if not then making tail's previous as new tail and making
  old tail null*/
    fun deleteLast(){
        if(isEmpty()){println("list is empty")}
        if (head?.nextNode == null){
            head = null
        }else {
            tail = tail?.previousNode
            tail?.nextNode = null
        }
        size--
    }

}
fun main(){
    val list = DoublyLinkedList<Int>()
    list.addLastNode(40)
    list.addLastNode(20)
    list.addLastNode(30)
    list.addLastNode(10)
    println(list)
}