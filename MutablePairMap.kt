class MutablePairMap<K1, K2, V> : Iterable<Triple<K1, K2, V>> {
    private val map: MutableMap<K1, MutableMap<K2, V>> = mutableMapOf()

    /***
     *
     * @param key1 一番目のキー
     * @param key2 二番目のキー
     * @param value 置き換える値
     * @return 古い値
     */
    fun put(key1: K1, key2: K2, value: V): V? {

        val secondMap: MutableMap<K2, V> = map[key1] ?: mutableMapOf()
        return try {
            secondMap.put(key2, value)
        } finally {
            map.put(key1, secondMap)
        }
    }

    fun get(key1: K1): MutableMap<K2, V>? = map[key1]

    fun get(key1: K1, key2: K2): V? = map[key1]?.get(key2)

    fun keys() = map.keys

    override fun toString(): String = StringBuilder().apply {

        for ((key1, secondMap) in map) {
            for ((key2, value) in secondMap) {
                append("$key1 : $key2 : $value").append("\n")
            }
        }

    }.toString()

    override fun iterator() = MutablePairMapIterator(map)

    class MutablePairMapIterator<out K1, K2, V>(pairMap: MutableMap<K1, MutableMap<K2, V>>) : Iterator<Triple<K1, K2, V>> {

        private val rootIterator = pairMap.iterator()
        private var rootMap: MutableMap.MutableEntry<K1, MutableMap<K2, V>>? = if (rootIterator.hasNext()) rootIterator.next() else null

        private var secondIterator: MutableIterator<MutableMap.MutableEntry<K2, V>>? = rootMap?.value?.iterator()


        override fun hasNext() = rootIterator.hasNext() || secondIterator?.hasNext() ?: false

        override fun next(): Triple<K1, K2, V> {

            val secondIterator = secondIterator
            when (secondIterator?.hasNext()?.not()) {
                true -> {
                    rootMap = rootIterator.next().also {
                        this.secondIterator = it.value.iterator()
                    }
                    return next()
                }
                false -> {
                    val secondMap = secondIterator.next()
                    return Triple(rootMap!!.key, secondMap.key, secondMap.value)
                }
                null -> throw IllegalStateException()
            }
        }
    }
}