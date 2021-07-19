package trie

class Trie<Key> {
    private val root = TrieNode<Key>(word = null, parent = null)

    fun insert(list: List<Key>) {

        var current = root
        list.forEach { element ->
            if (current.children[element] == null) {
                current.children[element] = TrieNode(element, current)
            }
            current = current.children[element]!!
        }
        current.isWord = true
    }

    fun contains(list: List<Key>): Boolean {
        var current = root
        list.forEach { element->
            if (current.children[element]==null) return false
            current = current.children[element]!!
        }
        return current.isWord
    }

    fun remove(collection: List<Key?>) {

        var current = root
        collection.forEach {element->
            if (current.children[element]==null) return
            current = current.children[element]!!
        }
        if (!current.isWord) return
        current.isWord = false
        val parent = current.parent
        while (current.parent!=null && current.children.isEmpty() && !current.isWord) {
           current.parent!!.children.remove(current.word)
           current = current.parent!!
        }
    }

    fun autoComplete(prefix: List<Key>):List<List<Key>>{
        var currentNode = root
        prefix.forEach { element->
            if (currentNode.children[element]==null) return emptyList()
            currentNode = currentNode.children[element]!!
        }
        return autoComplete(prefix, currentNode)
    }

    private fun autoComplete(prefix: List<Key>, node: TrieNode<Key>):List<List<Key>>{
        val results = mutableListOf<List<Key>>()
        if (node.isWord)
            results.add(prefix)
        node.children.forEach{(key, node)->
            results.addAll(autoComplete(prefix+key, node))
        }
        return results
    }
}

fun main(){
    val trie = Trie<Char>()
    trie.insert("apple")
    trie.insert("azure")
    trie.insert("avant")
    trie.insert("zora")
    trie.insert("rude")

    println((trie.autoComplete("a")))


}


fun Trie<Char>.insert(string: String) {
    insert(string.toList())
}
fun Trie<Char>.contains(string: String): Boolean {
    return contains(string.toList())
}

fun Trie<Char>.remove(string: String) {
    remove(string.toList())
}

fun Trie<Char>.autoComplete(prefix: String): List<String> {
    return autoComplete(prefix.toList()).map()
    { it.joinToString(separator = "") }
}










