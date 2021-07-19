package tree


import queue.ArrayListQueue

import java.util.*

class TreeNode<T>(val value: T) {
/**Each node is responsible for a value and holds references to all of its children using
a mutable list. Each node is also a list*/
    private val children: MutableList<TreeNode<T>> = mutableListOf()
//This method adds a child node to a node/node list
    fun addChild(child: TreeNode<T>) = children.add(child)
/**Depth-first traversal starts at the root node and explores the tree as far as possible
along each branch before reaching a leaf and then backtracking
 This function simply/ recursively traversing the tree what its doing is, TreeNode i.e our visitor
 type visit take value in its parameter means the node and then recursively getting all the nodes
 of the tree and making a tree*/

/**what happens in depth first is when a node is visited then its children are also visited right
 after it and then other other node is visited and then right after it's children are visited*/
/**took value see its origin then print it*/
    fun forEachDepthFirst(visit: Visitor<T>){
        visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }
/**what is happening here is visit() is getting a TreeNode<T> and then pushing that node's
children in a queue and then from that queue we dequeue enqueued children
one by one like dequeuing a node and also at the same time enqueuing dequeued node's children in a queue
then dequeuing the other same level node from the queue
and passing its children into the queue and then dequeue it now from queue
we take each children node mean dequeue and passing its sub children into the queue and then dequeue other
 node of same level and enqueuing its children it will happen until the nodes from queue arent finished*/

/**in level order the level vise nodes are visited*/
fun forEachLevelOrderTraversal(visit: Visitor<T>){
        visit(this)
        val queue =  ArrayListQueue<TreeNode<T>>()
        children.forEach { queue.enqueue(it) }
        while (!queue.isEmpty){
            val node = queue.dequeue()
            visit(node!!)
            node.children.forEach { queue.enqueue(it) }
        }
    }

    fun search(value: T):TreeNode<T>?{
        var result: TreeNode<T>? = null
        forEachLevelOrderTraversal {
            if (it.value == value){
                result = it
            }
        }
        return result!!
    }

    fun treePrint(visit: Visitor<T>){
        visit(this)
        val queue = ArrayListQueue<TreeNode<T>>()
        children.forEach {
            queue.enqueue(it)
            print("${it.value} ")
        }
        println()
        while (!queue.isEmpty){
            val node = queue.dequeue()
            node?.children?.forEach {
                queue.enqueue(it)
                print("${it.value} ")
            }
        }
    }
}


typealias Visitor<T> = (TreeNode<T>) -> Unit

fun main(){
    /**val hot = TreeNode("Hot")
    val cold = TreeNode("Cold")

    val beverages = TreeNode("Beverages").run {
        addChild(hot)
        addChild(cold)
    }*/

    fun makeBeverageTree():TreeNode<Int>{
        val root = TreeNode(15)

        val one = TreeNode(1)
        val seventeen = TreeNode(17)
        val twenty = TreeNode(20)

        val one1 = TreeNode(1)
        val five = TreeNode(5)
        val zero = TreeNode(0)

        val two = TreeNode(2)

        val five1 = TreeNode(5)
        val seven = TreeNode(7)

        root.addChild(one)
        root.addChild(seventeen)
        root.addChild(twenty)

        one.addChild(one1)
        one.addChild(five)
        one.addChild(zero)

        seventeen.addChild(two)

        twenty.addChild(five1)
        twenty.addChild(seven)

        return  root
    }

    val tree = makeBeverageTree()


    tree.treePrint { println(it.value) }




}