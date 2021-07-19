package graph

class AdjacencyMatrix<T: Any>: Graph<T> {

    /**Here, you’ve defined an AdjacencyMatrix that contains an array of vertices and an
    adjacency matrix to keep track of the edges and their weights*/
    private val vertices = arrayListOf<Vertex<T>>()
    private val weights = arrayListOf<ArrayList<Double?>>()

    override fun createVertex(data: T): Vertex<T> {
        val vertex = Vertex(vertices.count(), data)
        //Add a new vertex to the array.
        vertices.add(vertex)
        /**Append a null weight to every row in the matrix, as none of the current vertices
        have an edge to the new vertex.*/
        weights.forEach {
            it.add(null)
        }
        /**Add a new row to the matrix. This row holds the outgoing edges for the new
        vertex. You put a null value in this row for each vertex that your graph stores.*/
        val row = ArrayList<Double?>(vertices.count())
        repeat(vertices.count()){
            row.add(null)
        }
        weights.add(row)
        return vertex
    }

    override fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
        weights[source.index][destination.index] = weight
    }

    /**To retrieve the outgoing edges for a vertex, you search the row for this vertex in the
    matrix for weights that are not null.
    Every non-null weight corresponds with an outgoing edge. The destination is the
    vertex that corresponds with the column in which the weight was found.*/
    override fun edges(source: Vertex<T>): ArrayList<Edge<T>> {
        val edges = arrayListOf<Edge<T>>()
        (0 until weights.size).forEach {
            column -> val weight = weights[source.index][column]
            if (weight!=null){
                edges.add(Edge(source, vertices[column], weight))
            }
        }
        return  edges
    }

    override fun weight(source: Vertex<T>, destination: Vertex<T>): Double? {
        return weights[source.index][destination.index]
    }

    override fun add(edge: Graph.EdgeType, source: Vertex<T>, destination: Vertex<T>, weight: Double) {
        when(edge){
            Graph.EdgeType.DIRECTED -> addDirectedEdge(source, destination, weight)
            Graph.EdgeType.UNDIRECTED -> addUndirectedEdge(source, destination, weight)
        }
    }

    override fun addUndirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
        addDirectedEdge(source, destination, weight)
        addDirectedEdge(destination, source, weight)
    }

    override fun toString(): String {
        val verticesDescription = vertices
            .joinToString(separator = "\n") { "${it.index}: ${it.data}" }
        val grid = weights.map { row ->
            buildString {
                (0 until weights.size).forEach { columnIndex ->
                    val value = row[columnIndex]
                    if (value!=null) append("$value\t")
                    else{append("ø\t\t")}
                }
            }
        }
        val edgesDescription = grid.joinToString("\n")
        return "$verticesDescription\n\n$edgesDescription"
    }

}

fun main(){

    val graph = AdjacencyMatrix<String>()

    val singapore = graph.createVertex("Singapore")
    val tokyo = graph.createVertex("Tokyo")
    val hongKong = graph.createVertex("Hong Kong")
    val detroit = graph.createVertex("Detroit")
    val sanFrancisco = graph.createVertex("San Francisco")
    val washingtonDC = graph.createVertex("Washington, DC")
    val austinTexas = graph.createVertex("Austin, Texas")
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