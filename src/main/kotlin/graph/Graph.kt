package graph

import queue.Queue
import queue.StackQueue
import stack.Stack1


interface Graph<T: Any>{
    // Creates a vertex and adds it to the graph.
    fun createVertex(data: T): Vertex<T>

    // Adds a directed edge between two vertices
    fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?)

    /**Adds an undirected (or bi-directional) edge between two vertices*/
    fun addUndirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?)

    /**Uses EdgeType to add either a directed or undirected edge between two
    vertices.*/
    fun add(edge: EdgeType, source: Vertex<T>, destination: Vertex<T>, weight: Double)

    /** Returns a list of outgoing edges from a specific vertex.*/
    fun edges(source: Vertex<T>): ArrayList<Edge<T>>

    /**Returns the weight of the edge between two vertices.*/
    fun weight(source: Vertex<T>, destination: Vertex<T>): Double?


    enum class EdgeType{
        DIRECTED,
        UNDIRECTED
    }

    fun breadthFirstSearch(source: Vertex<T>): ArrayList<Vertex<T>>{
        //queue: Keeps track of the neighboring vertices to visit next.
        val queue = StackQueue<Vertex<T>>()
        /**enqueued: Remembers which vertices have been enqueued, so you don’t enqueue
        the same vertex twice.*/
        val enqueued = mutableSetOf<Vertex<T>>()
        //visited: An array list that stores the order in which the vertices were explored.
        val visited = ArrayList<Vertex<T>>()
        /**You initiate the BFS algorithm by first enqueuing the source vertex.*/
        queue.enqueue(source)
        enqueued.add(source)
        while (true){
            /**You continue to dequeue a vertex from the queue until the queue is empty.*/
            val vertex = queue.dequeue() ?: break
            /**Every time you dequeue a vertex from the queue, you add it to the
             list of visited vertices*/
            visited.add(vertex as Vertex<T>)
            /**You then find all edges that start from the current
             vertex and iterate over them*/
            val neighbourEdges = edges(vertex)
            /**For each edge, you check to see if its destination vertex has been enqueued
            before, and if not, you add it to the code.*/
            neighbourEdges.forEach {
                if (!enqueued.contains(it.destination)){
                    queue.enqueue(it.destination)
                    enqueued.add(it.destination)
                }
            }
        }
        return visited
    }

    fun depthFirstSearch(source: Vertex<T>): ArrayList<Vertex<T>>{
        //stack: Used to store your path through the graph.
        val stack = Stack1<Vertex<T>>()
        /**pushed: Remembers which vertices were already pushed so that you don’t visit
        the same vertex twice. It's a MutableSet to ensure fast O(1) lookup*/
        val visited = arrayListOf<Vertex<T>>()
        //visited: A list that stores the order in which the vertices were visited.
        val pushed = mutableListOf<Vertex<T>>()
        stack.push(source)
        pushed.add(source)
        visited.add(source)
        /**In the first step you insert the vertex passed as parameter to the three data
        structures. You do this because this is the first to be visited and it's the starting point
        in order to navigate the neighbors.*/
        outer@ while (true){
            if (stack.isEmpty) break
            /**You continue to check the top of the stack for a vertex until the stack is empty.
            You’ve labeled this loop outer so that you have a way to continue to the next
            vertex, even within nested loops.*/
            val vertex = stack.peek()
            //You find all the neighboring edges for the current vertex.
            val neighbours = edges(vertex!!)
            /**If there are no edges, you pop the vertex off the stack and continue to the next
            one.*/
            if (neighbours.isEmpty()){
                stack.pop()
                continue
            }
            /**Here, you loop through every edge connected to the current vertex and check to
            see if the neighboring vertex has been seen. If not, you push it onto the stack and
            add it to the visited list.*/
            for (i in 0 until neighbours.size){
                val destination = neighbours[i].destination
                if (destination !in pushed){
                    stack.push(destination)
                    pushed.add(destination)
                    visited.add(destination)
                    /**Now that you’ve found a neighbor to visit, you continue the outer loop and move
                    to the newly pushed neighbor*/
                    continue@outer
                }
            }
            /**If the current vertex did not have any unvisited neighbors, you know that you
            reached a dead end and can pop it off the stack.*/
            stack.pop()
        }
        return visited
    }
}
/**Here, you’ve defined a generic Vertex class. A vertex has a unique index within its
graph and holds a piece of data.
You defined Vertex as a data class because it will be used as a key in a Map later, and
a data class gives you equals() and hashCode() implementations, without having
to write them yourself.*/
data class Vertex<T>(val index: Int, val data: T)

//An Edge connects two vertices and has an optional weight.
data class Edge<T>(val source: Vertex<T>, val destination: Vertex<T>, val weight: Double? = null)