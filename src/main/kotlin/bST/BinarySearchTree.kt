package bST

import stack.Stack1
import tree.BinaryTreeNode

//By definition, binary search trees can only hold values that are Comparable.
//typealias Visitor3<T> = (BinarySearchTree<T>) -> Unit
class BinarySearchTree<T: Comparable<T>> {

    private var root: BinaryTreeNode<T>? = null

    override fun toString() = root?.toString() ?: "Empty Tree"

    /**Following the rules of the BST, nodes of the left child must contain values less than
    the current node, whereas nodes of the right child must contain values greater than
    or equal to the current node. You’ll implement insert while respecting these rules.*/
    fun insert(value: T) {
        root = insert(root, value)
    }

    /**The first insert is exposed to users, while the second will be used as a private helper
    method*/
    private fun insert(node: BinaryTreeNode<T>?, value: T): BinaryTreeNode<T> {
        /**This is a recursive method, so it requires a base case for terminating recursion. If
        the current node is null, you’ve found the insertion point and return the new
        BinaryNode.*/
        node ?: return BinaryTreeNode(value)

        /**This if statement controls which way the next insert call should traverse. If the
        new value is less than the current value, you call insert on the left child. If the
        new value is greater than or equal to the current value, you call insert on the
        right child.*/
        if (value < node.value) {
            node.leftChildNode = insert(node.leftChildNode, value)
        } else {
            node.rightChildNode = insert(node.rightChildNode, value)
        }
        /**Return the current node. This makes assignments of the form node =
        insert(node, value) possible as insert will either create node (if it was null)
        or return node (if it was not null).*/
        return node
    }
/**Here’s how it works:
1. Start by setting current to the root node.
2. While current is not null, check the current node’s value.
3. If the value is equal to what you’re trying to find, return true.
4. Otherwise, decide whether you’re going to check the left or right child*/
    fun contains(value: T): Boolean {
        var current = root

        while (current != null) {
            if (current.value == value)
                return true
            current = if (value < current.value) {
                current.leftChildNode
            } else {
                current.rightChildNode
            }
        }
        return false
    }

    fun remove(value: T){
        root = remove(root,value)
    }

    private fun remove(node: BinaryTreeNode<T>?, value: T):BinaryTreeNode<T>?{
        node?: return null

        when {
            value == node.value -> {
                /**1. In the case in which the node is a leaf node, you simply return null, thereby
                removing the current node.*/
                if(node.leftChildNode==null && node.rightChildNode==null){
                    return null
                }
                /**2.If the node has no left child, you return node.rightChild to reconnect the right
                subtree.*/
                if(node.leftChildNode == null){
                    return node.rightChildNode
                }
                /**3. If the node has no right child, you return node.leftChild to reconnect the left
                subtree.*/
                if(node.rightChildNode == null){
                    return node.leftChildNode
                }
                /**4. This is the case in which the node to be removed has both a left and right child.
                You replace the node’s value with the smallest value from the right subtree. You
                then call remove on the right child to remove this swapped value.**/
                val successor = min(node.rightChildNode)
                /**simply removing*/
                node.rightChildNode = remove(node.rightChildNode, successor)
                node.value = successor
            }
            /**if the received and root value is not same so recursively find that value*/
            value<node.value->node.leftChildNode = remove(node.leftChildNode,value)
            else->node.rightChildNode = remove(node.rightChildNode,value)
        }
        return node
    }
/**taking a node and storing it in a variable so can reassign it, traversing until left child node
 is null and making node more forward left*/
    fun min(root: BinaryTreeNode<T>?): T {
        var minV = root
        while(minV!!.leftChildNode!=null){
            minV = minV.leftChildNode
        }
        return minV.value
    }




}

fun main(){

    val bst1 = BinarySearchTree<Int>()
    bst1.insert(9)
    bst1.insert(4)
    bst1.insert(13)

    val bst2 = BinarySearchTree<Int>()
    bst2.insert(9)
    bst2.insert(4)
    bst2.insert(12)



}


