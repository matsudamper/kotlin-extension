/* forEachFinally */
fun <T> Array<T>.forEachFinally(main: (T) -> Unit, finally: (T) -> Unit) {

    iterator().forEachFinally(main, finally)
}

fun <T> Iterable<T>.forEachFinally(main: (T) -> Unit, finally: (T) -> Unit) {

    iterator().forEachFinally(main, finally)
}

fun <T> Iterator<T>.forEachFinally(main: (T) -> Unit, finally: (T) -> Unit) {

    for (item in this) {
        main(item)
        if (this.hasNext().not()) finally(item)
    }
}