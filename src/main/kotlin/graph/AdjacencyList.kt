package graph

class AdjacencyList<T: Any>: Graph<T> {

    /**Here, you’ve defined an AdjacencyList that uses a map to store the edges.*/
    private val adjacencies: HashMap<Vertex<T>, ArrayList<Edge<T>>> = HashMap()

    /**Here, you create a new vertex and return it. In the adjacency list, you store an empty
    list of edges for this new vertex.*/
    override fun createVertex(data: T): Vertex<T> {
        val vertex = Vertex(adjacencies.count(), data)
        adjacencies[vertex] = ArrayList()
        return vertex
    }

    //This method creates a new edge and stores it in the adjacency list.
    override fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
        val edge = Edge(source, destination, weight)
        adjacencies[source]?.add(edge)
    }

    override fun addUndirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
        addDirectedEdge(source, destination, weight)
        addDirectedEdge(destination, source, weight)
    }

    override fun add(edge: Graph.EdgeType, source: Vertex<T>, destination: Vertex<T>, weight: Double) {
        when(edge){
            Graph.EdgeType.DIRECTED -> addDirectedEdge(source, destination, weight)
            Graph.EdgeType.UNDIRECTED -> addUndirectedEdge(source, destination, weight)
        }
    }
    /**You either return the stored edges or an
    empty list if the source vertex is unknown.*/
    override fun edges(source: Vertex<T>) = adjacencies[source] ?: arrayListOf()

    /**, you find the first edge from source to destination; if there is one, you return
    its weight.*/
    override fun weight(source: Vertex<T>, destination: Vertex<T>): Double? {
        return edges(source).firstOrNull {
            it.destination == destination
        }?. weight
    }

    override fun toString(): String {
        return buildString {
            adjacencies.forEach {
                (vertex, edges) -> val edgeString = edges.joinToString {
                    it.destination.data.toString()
                }
                append("${vertex.data} ---> [ $edgeString ]\n")
            }
        }
    }

    val vertices: Set<Vertex<T>>
        get() = adjacencies.keys

    fun copyVertices(graph: AdjacencyList<T>){
        graph.vertices.forEach {
            adjacencies[it] = arrayListOf()
        }
    }

}

fun main(){

    val graph = AdjacencyList<String>()
    val singapore = graph.createVertex("Singapore")
    val tokyo = graph.createVertex("Tokyo")
    val hongKong = graph.createVertex("Hong Kong")
    val detroit = graph.createVertex("Detroit")
    val sanFrancisco = graph.createVertex("San Francisco")
    val washingtonDC = graph.createVertex("Washington DC")
    val austinTexas = graph.createVertex("Austin Texas")
    val seattle = graph.createVertex("Seattle")

    graph.add(Graph.EdgeType.UNDIRECTED, singapore, hongKong, 300.0)
    graph.add(Graph.EdgeType.UNDIRECTED, singapore, tokyo, 500.0)
    graph.add(Graph.EdgeType.UNDIRECTED, hongKong, tokyo, 250.0)
    graph.add(Graph.EdgeType.UNDIRECTED, tokyo, detroit, 450.0)
    graph.add(Graph.EdgeType.UNDIRECTED, tokyo, washingtonDC, 300.0)
    graph.add(Graph.EdgeType.UNDIRECTED, hongKong, sanFrancisco, 600.0)
    graph.add(Graph.EdgeType.UNDIRECTED, detroit, austinTexas, 50.0)
    graph.add(Graph.EdgeType.UNDIRECTED, austinTexas, washingtonDC, 292.0)
    graph.add(Graph.EdgeType.UNDIRECTED, sanFrancisco, washingtonDC, 337.0)
    graph.add(Graph.EdgeType.UNDIRECTED, washingtonDC, seattle, 277.0)
    graph.add(Graph.EdgeType.UNDIRECTED, sanFrancisco, seattle, 218.0)
    graph.add(Graph.EdgeType.UNDIRECTED, austinTexas, sanFrancisco, 297.0)

    println(graph)


}