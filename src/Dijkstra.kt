import kotlin.Int.Companion.MAX_VALUE as INF
import kotlin.Int.Companion.MIN_VALUE as NEGATIVE_INF

val graph = arrayOf(
    arrayOf(Pair(1, 25), Pair(2, 75)),
    arrayOf(Pair(2, 25), Pair(3, 100)),
    arrayOf(Pair(3, 25)),
    arrayOf()
)

val allPaths = mutableListOf<Pair<Int, MutableList<Int>>>()

val startNode = 0
val endNode = 3

fun main() {
    dijkstraMin(startNode, endNode)
    println("=============================")
    dijkstraMax(startNode, endNode)
    println("=============================")
    addPaths(0, mutableListOf())
    allPaths.sortedBy {
        it.first
    }.forEach {
        println("Length:${it.first}, path:${it.second.joinToString(",", "(", ")")}")
    }
    println("=============================")
}

fun addPaths(currentNode: Int, path: MutableList<Int>) {
    path.add(currentNode)

    if (currentNode == endNode) {
        var sum = 0

        path.forEachIndexed { index, i ->
            if(index < path.lastIndex) {
                sum += graph[i].first { it.first == path[index + 1] }.second
            }
        }

        allPaths.add(Pair(sum, path))
        return
    }

    graph[currentNode].forEach {
        addPaths(it.first, path.toMutableList())
    }
}

fun dijkstraMin(startNode: Int, endNode: Int) {
    val dist = Array(4) { INF }
    dist[startNode] = 0
    val u = Array(4) { false }
    val path = Array<Int>(4) { -1 }
    for (i in 0 until graph.size) {
        var v = -1
        for (j in 0 until graph.size) {
            if (!u[j] && (v == -1 || dist[j] < dist[v])) {
                v = j
            }
        }

        if (dist[v] == INF) {
            break
        }

        u[v] = true

        for (j in 0 until graph[v].size) {
            val to = graph[v][j].first
            val len = graph[v][j].second
            if (dist[v] + len < dist[to]) {
                dist[to] = dist[v] + len
                path[to] = v
            }
        }
    }

    val builder = StringBuilder()
    builder.append(")")

    var currentNode = endNode
    while (currentNode != -1) {
        builder.append(currentNode)
        if (path[currentNode] != -1) {
            builder.append(",")
        }
        currentNode = path[currentNode]
    }

    builder.append("(")
    println("Min path:${builder.reverse()}")
}

fun dijkstraMax(startNode: Int, endNode: Int) {
    val dist = Array(4) { NEGATIVE_INF }
    dist[startNode] = 0
    val u = Array(4) { false }
    val path = Array<Int>(4) { -1 }
    for (i in 0 until graph.size) {
        var v = -1
        for (j in 0 until graph.size) {
            if (!u[j] && (v == -1 || dist[j] > dist[v])) {
                v = j
            }
        }

        if (dist[v] == NEGATIVE_INF) {
            break
        }

        u[v] = true

        for (j in 0 until graph[v].size) {
            val to = graph[v][j].first
            val len = graph[v][j].second
            if (dist[v] + len > dist[to]) {
                dist[to] = dist[v] + len
                path[to] = v
            }
        }
    }

    val builder = StringBuilder()
    builder.append(")")

    var currentNode = endNode
    while (currentNode != -1) {
        builder.append(currentNode)
        if (path[currentNode] != -1) {
            builder.append(",")
        }
        currentNode = path[currentNode]
    }

    builder.append("(")
    println("Max path:${builder.reverse()}")
}