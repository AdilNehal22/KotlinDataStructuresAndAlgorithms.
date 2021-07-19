package trie

class TrieNode<Key>(var word: Key?, var parent: TrieNode<Key>?) {
    val children: HashMap<Key, TrieNode<Key>> = HashMap()
    var isWord = false
}
