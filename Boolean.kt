inline fun Boolean.doTrue(block: () -> Unit): Boolean {
    if (this) block()
    return this
}

inline fun Boolean.doFalse(block: () -> Unit): Boolean {
    if (this.not()) block()
    return this
}