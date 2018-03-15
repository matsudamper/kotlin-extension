/***
 * Indexと共にMapの内容を返却する
 */
fun <K, V> Map<K, V>.withIndexed(): List<Triple<Int, K, V>> {
    val result = mutableListOf<Triple<Int, K, V>>()
    var counter = 0

    this.forEach { key, value -> result.add(Triple(counter, key, value)); counter++ }
    return result
}