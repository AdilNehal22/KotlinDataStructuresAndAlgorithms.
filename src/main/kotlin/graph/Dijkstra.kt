package graph

import priorityQueue.AbstractPriorityQueue
import queue.ArrayListQueue
import queue.Queue
import queue.StackQueue
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Dijkstra<T : Any>(private val graph: AdjacencyList<T>) {

    private fun route(destination: Vertex<T>, paths: HashMap<Vertex<T>, Visit<T>>): ArrayList<Edge<T>> {
        var vertex = destination
        val path = arrayListOf<Edge<T>>()

        loop@ while (true) {
            val visit = paths[vertex] ?: break
            when (visit.type) {
                VisitType.EDGE -> visit.edge?.let {
                    path.add(it)
                    vertex = it.source
                }
                VisitType.START -> break@loop
            }
        }
        return path
    }

    private fun distance(destination: Vertex<T>, paths: HashMap<Vertex<T>, Visit<T>>): Double {
        val path = route(destination, paths)
        return path.sumByDouble { it.weight ?: 0.0 }
    }

    fun shortestPath(start: Vertex<T>): HashMap<Vertex<T>, Visit<T>> {
        val paths: HashMap<Vertex<T>, Visit<T>> = hashMapOf()
        paths[start] = Visit(VisitType.START)
        val distanceComparator = Comparator<Vertex<T>> { first, second
            ->
            (distance(second, paths) - distance(first, paths)).toInt()
        }
        val priorityQueue = AbstractPriorityQueue.ComparatorPriorityQueueImpl(distanceComparator)
        priorityQueue.enqueue(start)
        while (true) {
            val vertex = priorityQueue.dequeue() ?: break
            val edges = graph.edges(vertex)
            edges.forEach {
                val weight = it.weight ?: return@forEach
                if (paths[it.destination] == null || distance(vertex, paths) + weight < distance(
                        it.destination,
                        paths
                    )
                ) {
                    paths[it.destination] = Visit(VisitType.EDGE, it)
                    priorityQueue.enqueue(it.destination)
                }
            }
        }
        return paths
    }

    fun shortestPath(destination: Vertex<T>, paths: HashMap<Vertex<T>, Visit<T>>): ArrayList<Edge<T>>{
        return route(destination, paths)
    }

}




class Visit<T>(val type: VisitType, val edge: Edge<T>? = null)

enum class VisitType{
    START,
    EDGE
}

