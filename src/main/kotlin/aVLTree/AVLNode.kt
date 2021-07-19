package aVLTree

import tree.BinaryTreeNode
import kotlin.math.max

typealias Visitor<T> = (T) -> Unit

class AVLNode<T: Comparable<T>>(var value: T) {

    private var root: BinaryTreeNode<T>? = null

    var leftChild: AVLNode<T>? = null
    var rightChild: AVLNode<T>? = null

    val min: AVLNode<T>
    get() = leftChild?.min ?: this

    override fun toString() = diagram(this)

    private fun diagram(node: AVLNode<T>?,
                        top: String = "",
                        root: String = "",
                        bottom: String = ""): String {
        return node?.let {
            if (node.leftChild == null && node.rightChild == null) {
                "$root${node.value}\n"
            } else {
                diagram(node.rightChild, "$top ", "$top┌──", "$top│ ") +
                        root + "${node.value}\n" + diagram(node.leftChild, "$bottom│ ", "$bottom└──", "$bottom ")
            }
        } ?: "${root}null\n"
    }
    /**You’ll use the relative heights of a node’s children to determine whether a particular
    node is balanced.*/
     var height = 0
    /**the balanced factor of each node is (Hl - Hr) = 1 or 0 or -1
    The balanceFactor computes the height difference of the left and right child. If a
    particular child is null, its height is considered to be -1.*/

    val leftHeight: Int
        get() = leftChild?.height ?: -1

    val rightHeight: Int
        get() = rightChild?.height ?: -1

    val balancedFactor: Int
        get() = leftHeight - rightHeight

    fun inOrderTraversal(visit: Visitor<T>){
        leftChild?.inOrderTraversal(visit)
        visit(value)
        rightChild?.inOrderTraversal(visit)
    }

    private fun postOrderTraversal(visit: Visitor<T>){
        leftChild?.postOrderTraversal(visit)
        rightChild?.postOrderTraversal(visit)
        visit(value)
    }

    fun preOrderTraversal(visit: Visitor<T>){
        visit(value)
        leftChild?.postOrderTraversal(visit)
        rightChild?.postOrderTraversal(visit)
    }

}
