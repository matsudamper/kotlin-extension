/***
 * 全て同じ値を返すかを調べる
 */

inline fun <K, V, R> Map<K, V>.equalAll(predicate: (Map.Entry<K, V>) -> R): Boolean {
    return this.iterator().equalAll { predicate(it) }
}

inline fun <T, R> Array<T>.equalAll(predicate: (T) -> R): Boolean {
    return this.iterator().equalAll { predicate(it) }
}

inline fun <T, R> Iterable<T>.equalAll(predicate: (T) -> R): Boolean {
    return this.iterator().equalAll { predicate(it) }
}

inline fun <T, R> Iterator<T>.equalAll(predicate: (T) -> R): Boolean {

    if (hasNext().not()) {
        return true
    }

    val firstValue = predicate(next())

    while (hasNext()) {
        if (firstValue != predicate(next())) {
            return false
        }
    }

    return true
}