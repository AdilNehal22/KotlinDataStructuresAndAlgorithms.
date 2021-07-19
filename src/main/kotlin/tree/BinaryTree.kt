package tree



import kotlin.math.max

typealias Visitor1<T> = (T) -> Unit

class BinaryTreeNode<T: Comparable<T>>(var value: T){

    var leftChildNode: BinaryTreeNode<T>? = null
    var rightChildNode: BinaryTreeNode<T>? = null

    override fun toString() = diagram(this)
    private fun diagram(node: BinaryTreeNode<T>?,
                        top: String = "",
                        root: String = "",
                        bottom: String = ""): String {
        return node?.let {
            if (node.leftChildNode == null && node.rightChildNode == null) {
                "$root${node.value}\n"
            } else {
                diagram(node.rightChildNode, "$top ", "$top┌──", "$top│ ") +
                        root + "${node.value}\n" + diagram(node.leftChildNode,
                    "$bottom│ ", "$bottom└──", "$bottom ")
            }
        } ?: "${root}null\n"
    }


/**you first traverse to the left-most node before
visiting the value. You then traverse to the right-most node.*/
    fun inOrderTraversal(visit: Visitor1<T>){
        leftChildNode?.inOrderTraversal(visit)
        visit(value)
        rightChildNode?.inOrderTraversal(visit)
    }
/**root/left/right*/
    fun preOrderTraversal(visit: Visitor1<T>){
        visit(value)
        leftChildNode?.preOrderTraversal(visit)
        rightChildNode?.preOrderTraversal(visit)
    }
/**left/right/root*/
    fun postOrderTraversal(visit: Visitor1<T>){
        leftChildNode?.preOrderTraversal(visit)
        rightChildNode?.preOrderTraversal(visit)
        visit(value)
    }

    fun heightOfBinaryTree(node: BinaryTreeNode<T>? = this): Int {
        if(node == null){
            return -1
        }else{
           val leftHeight = heightOfBinaryTree(node.leftChildNode)
           val rightHeight = heightOfBinaryTree(node.rightChildNode)
           return max(leftHeight,rightHeight)+1
        }
    }

    fun isBinarySearchTree(): Boolean {
        return isBST(this, min = null, max = null)
    }
    /**isBST is responsible for recursively traversing through the tree and checking for
    the BST property. It needs to keep track of progress via a reference to a
    BinaryNode and also keep track of the min and max values to verify the BST
    property*/
    private fun isBST(tree: BinaryTreeNode<T>?, min: T?, max: T?): Boolean {
    /*This is the base case. If tree is null, then there are no nodes to inspect.
    A null node is a binary search tree, so you’ll return true in that case.**/
                tree ?: return true
    /**This is essentially a bounds check. If the current value exceeds the bounds of the
    min and max values, the current node does not respect the binary search tree
    rules.*/
        if (min != null && tree.value <= min) {
            return false
        } else if (max != null && tree.value > max) {
            return false
        }
    /**This line contains the recursive calls. When traversing through the left children,
    the current value is passed in as the max value. This is because nodes in the left
    side cannot be greater than the parent. Vice versa, when traversing to the right,
    the min value is updated to the current value. Nodes in the right side must be
    greater than the parent. If any of the recursive calls evaluate false, the false
    value will propagate to the top.*/
        return isBST(tree.leftChildNode, min, tree.value) &&
                isBST(tree.rightChildNode, tree.value, max)
    }
/**Here’s an explanation of the code:
1. equals recursively checks two nodes and their descendants for equality.
2. Here, you check the value of the left and right nodes for equality. You also
recursively check the left children and the right children for equality.*/
/**this is which is calling equal function other is for which the function is called
 (this)root.equals(root2{other}) the other way is written on notes*/
    override fun equals(other: Any?): Boolean {
        return if (other!=null && other is BinaryTreeNode<*>){
            this.value == other.value &&
                    this.leftChildNode == other.leftChildNode &&
                    this.rightChildNode == other.rightChildNode
        }else{
            false
        }
    }
}

fun main() {

    val nine1 = BinaryTreeNode(9)
    val four1 = BinaryTreeNode(4)
    val eleven1 = BinaryTreeNode(11)
    nine1.leftChildNode = four1
    nine1.rightChildNode = eleven1
    val three1 = BinaryTreeNode(3)
    val seven1 = BinaryTreeNode(7)
    val ten1 = BinaryTreeNode(10)
    four1.rightChildNode = seven1
    four1.leftChildNode = three1
    val twelve1 = BinaryTreeNode(12)
    eleven1.rightChildNode = twelve1
    eleven1.leftChildNode = ten1

    val root = nine1

    val nine = BinaryTreeNode(9)
    val four = BinaryTreeNode(4)
    val eleven = BinaryTreeNode(11)
    nine.leftChildNode = four
    nine.rightChildNode = eleven
    val three = BinaryTreeNode(3)
    val seven = BinaryTreeNode(7)
    val ten = BinaryTreeNode(10)
    four.rightChildNode = seven
    four.leftChildNode = three
    val twelve = BinaryTreeNode(12)
    eleven.rightChildNode = twelve
    eleven.leftChildNode = ten

    val root2 = nine

    println(root.equals(root2))


}