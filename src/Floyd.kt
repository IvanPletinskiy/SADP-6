




//
//val INF = 1000
//
//private val g = arrayOf(
//    arrayOf(0, 25, 75, INF),
//    arrayOf(INF, 0, 25, 100),
//    arrayOf(INF, INF, 0, 25),
//    arrayOf(INF, INF, INF, 0)
//)
//
//private val p = arrayOf(
//    arrayOf(-1, -1, -1, -1),
//    arrayOf(-1, -1, -1, -1),
//    arrayOf(-1, -1, -1, -1),
//    arrayOf(-1, -1, -1, -1)
//)
//
//fun main() {
//    val n = 4
//    for (k in 0 until n)
//        for (i in 0 until n)
//            for (j in 0 until n) {
//                if(g[i][j] > (g[i][k] + g[k][j])) {
//                    p[i][j] = k
//                    g[i][j] = g[i][k] + g[k][j]
//                }
//            }
//}