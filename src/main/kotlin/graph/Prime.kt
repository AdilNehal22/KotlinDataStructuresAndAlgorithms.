package graph

import priorityQueue.AbstractPriorityQueue
import kotlin.math.cos
import kotlin.math.roundToInt

object prim
/**This method takes in four parameters:
1. The current vertex.
2. The graph, wherein the current vertex is stored.
3. The vertices that have already been visited.
4. The priority queue to add all potential edges.
Within the function, you do the following:
1. Look at every edge adjacent to the current vertex.
2. Check to see if the destination vertex has already been visited.
3. If it has not been visited, you add the edge to the priority queue.*/
private fun <T: Any> addAvailableEdges(vertex: Vertex<T>,
                                       graph: Graph<T>,
                                       visited: Set<Vertex<T>>,
                                       priorityQueue: AbstractPriorityQueue<Edge<T>>) {

    graph.edges(vertex).forEach { edge ->
        if (edge.destination !in visited) {
            priorityQueue.enqueue(edge)
        }
    }
    /*Here’s what you have so far:
    1. produceMinimumSpanningTree takes an undirected graph and returns a
            minimum spanning tree and its cost.
    2. cost keeps track of the total weight of the edges in the minimum spanning tree.
    3. This is a graph that will become your minimum spanning tree.
    4. visited stores all vertices that have already been visited.
    5. You create the Comparator<Edge<T>> to use for the priority queue.
    6. This is a min-priority queue to store edges.

    1. Copy all the vertices from the original graph to the minimum spanning tree.
    2. Get the starting vertex from the graph.
    3. Mark the starting vertex as visited.
    4. Add all potential edges from the start vertex into the priority queue.

    Continue Prim’s algorithm until the queue of edges is empty.
    2. Get the destination vertex.
    3. If this vertex has been visited, restart the loop and get the next smallest edge.
    4. Mark the destination vertex as visited.
    5. Add the edge’s weight to the total cost.
    6. Add the smallest edge into the minimum spanning tree you are constructing.
    7. Add the available edges from the current vertex.
    8. Once the priorityQueue is empty, return the minimum cost, and minimum
    spanning tree.
    */

    fun <T : Any> produceMinimumSpanningTree(graph: AdjacencyList<T>)
            : Pair<Double, AdjacencyList<T>> {
        var cost = 0.0 // 2
        val mst = AdjacencyList<T>() // 3
        val visited = mutableSetOf<Vertex<T>>() // 4
        val comparator = Comparator<Edge<T>> { first, second -> // 5
            val firstWeight = first.weight ?: 0.0
            val secondWeight = second.weight ?: 0.0
            (secondWeight - firstWeight).roundToInt()
        }
        val priorityQueue = AbstractPriorityQueue.ComparatorPriorityQueueImpl(comparator)
        mst.copyVertices(graph) // 1
        val start = graph.vertices.firstOrNull() ?: return Pair(
            cost,
            mst
        ) // 2
        visited.add(start) // 3
        addAvailableEdges(start, graph, visited, priorityQueue)

        while (true) {
            val smallestEdge = priorityQueue.dequeue() ?: break // 1
            val vertex = smallestEdge.destination // 2
            if (visited.contains(vertex)) continue
            visited.add(vertex)
            cost += smallestEdge.weight ?: 0.0
            mst.add(
                Graph.EdgeType.UNDIRECTED, smallestEdge.source, smallestEdge.destination,
                smallestEdge.weight!!
            )
            addAvailableEdges(vertex, graph, visited, priorityQueue)
        }
        return Pair(cost, mst)
    }

}





