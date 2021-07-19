package aVLTree

import stack.Stack1
import kotlin.math.max
import kotlin.math.pow

class AVLTree<T: Comparable<T>> {

    var root: AVLNode<T>? = null

    /**The procedures used to balance a binary search tree are known as rotations. There
    are four rotations in total, one for each way that a tree can become unbalanced.
    These are known as left rotation, left-right rotation, right rotation and right-left
    rotation.*/
    /**        X
              / \
             A   y
                / \
               B   Z
                  / \
                 C   D   */

    fun leftRotation(node: AVLNode<T>): AVLNode<T> {
        /**The right child is chosen as the pivot. This node replaces the rotated node as the
        root of the subtree (it moves up a level).*/
        val pivot = node.rightChild
        /**The node to be rotated becomes the left child of the pivot (it moves down a
        level). This means that the current left child of the pivot must be moved
        elsewhere.In the generic example shown in the earlier image, this is node b. Because b is
        smaller than y but greater than x, it can replace y as the right child of x. So you
        update the rotated node’s rightChild to the pivot’s leftChild.*/
        node.rightChild = pivot?.leftChild
        /**The pivot’s leftChild can now be set to the rotated node.*/
        pivot?.leftChild = node
        /**You update the heights of the rotated node and the pivot.*/
        node.height = max(node.leftHeight, node.rightHeight) + 1
        pivot?.height = max(pivot!!.leftHeight, pivot.rightHeight) + 1
        return pivot
    }

    /**Right rotation is the symmetrical opposite of left rotation. When a series of left
    children is causing an imbalance, it’s time for a right rotation.*/
    /**  X
        / \
       Y   D
      / \
     Z   C
    / \
   A   B    */

    /**This is nearly identical to the implementation of leftRotate(), except the
    references to the left and right children have been swapped.*/
    fun rotateRight(node: AVLNode<T>): AVLNode<T> {
        val pivot = node.leftChild
        node.leftChild = pivot?.rightChild
        pivot?.rightChild = node
        node.height = max(node.leftHeight, node.rightHeight) + 1
        pivot?.height = max(pivot!!.leftHeight, pivot.rightHeight) + 1
        return pivot
    }

    fun rightLeftRotate(node: AVLNode<T>): AVLNode<T> {
        val rightChild = node.rightChild ?: node
        node.rightChild = rotateRight(rightChild)
        return leftRotation(node)
    }

    fun leftRightRotate(node: AVLNode<T>): AVLNode<T> {
        val leftChild = node.leftChild ?: node
        node.leftChild = leftRotation(node)
        return rotateRight(node)
    }

    /**Next, you’ll figure out when to apply these rotations at the
    correct location. The next task is to design a method that uses balanceFactor to decide whether a
    node requires balancing or not. balanced() inspects the balanceFactor to
    determine the proper course of action. All that’s left is to call balanced() at the proper spot.*/
    fun balance(node: AVLNode<T>): AVLNode<T> {
        return when (node.balancedFactor) {
            /**A balanceFactor of 2 suggests that the left child is heavier (that is, contains
            more nodes) than the right child. This means that you want to use either right or
            left-right rotations.*/
            2 -> {
                if (node.leftChild?.balancedFactor == -1) {
                    leftRightRotate(node)
                } else {
                    rotateRight(node)
                }
            }
            /**A balanceFactor of -2 suggests that the right child is heavier than the left child.
            This means that you want to use either left or right-left rotations.
             */
            -2 -> {
                if (node.rightChild?.balancedFactor == 1) {
                    rightLeftRotate(node)
                } else {
                    leftRotation(node)
                }
            }
            /**The default case suggests that the particular node is balanced. There’s nothing to
            do here except to return the node.*/
            else -> node
        }
    }
    fun insert(value: T) {
        root = insert(root, value)
    }

    private fun insert(node: AVLNode<T>?, value: T): AVLNode<T> {
        node ?: return AVLNode(value)
        if (value < node.value) {
            node.leftChild = insert(node.leftChild, value)
        } else {
            node.rightChild = insert(node.rightChild, value)
        }
        val balancedNode = balance(node)
        balancedNode.height = max(balancedNode.leftHeight, balancedNode.rightHeight) + 1
        return balancedNode
    }

    fun remove(value: T) {
        root = remove(root, value)
    }

    private fun remove(node: AVLNode<T>?, value: T): AVLNode<T>? {
        node ?: return null

        when {
            value == node.value -> {
                if (node.leftChild == null && node.rightChild == null) {
                    return null
                }
                if (node.leftChild == null) {
                    return node.rightChild
                }
                if (node.rightChild == null) {
                    return node.leftChild
                }
                node.rightChild?.min?.value?.let {
                    node.value = it
                }
                node.rightChild = remove(node.rightChild, node.value)
            }
            value < node.value -> node.leftChild = remove(node.leftChild, value)
            else -> node.rightChild = remove(node.rightChild, value)
        }
        val balancedNode = balance(node)
        balancedNode.height = max(balancedNode.leftHeight, balancedNode.rightHeight) + 1
        return balancedNode
    }

    fun totalLeaves(tree: AVLTree<T>?):Int{
        return 2.0.pow(tree?.root!!.height).toInt()
    }

    fun totalNodes(tree: AVLTree<T>):Int{
        val leaves = Stack1<T>()
        tree.root?.inOrderTraversal {
            leaves.push(it)
        }
        return leaves.count
    }

    override fun toString() = root.toString() ?: "empty tree"

}
    fun main(){
    val tree = AVLTree<Int>()
    (0..20).forEach {
        tree.insert(it)
    }
    tree.root?.inOrderTraversal {
        println(it)
    }
}
