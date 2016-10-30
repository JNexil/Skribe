package su.jfdev.skribe.expect

import su.jfdev.skribe.expect.dev.*
import su.jfdev.skribe.expect.dev.InterruptedExpectError.Companion.fail

@Suppress("unused")
sealed class Expect<out T: Any> {
    /**
     * Use only to declare extensions
     */
    class Backend<T: Any>(val lazy: Lazy<T?>, val line: String = "", val properties: Set<ExpectProperty> = emptySet()): Expect<T>() {
        val value: T? by lazy
        val real: T get() = requireNotNull(value) { fail(line, NullPointerException("Subject of Expect is null")) }

        fun copy(lazy: Lazy<T?> = this.lazy, properties: Set<ExpectProperty> = this.properties, line: String = this.line)
                = Backend(lazy, line, properties)

        fun reverse(property: ExpectProperty) = when (property) {
            in properties -> without(property)
            else          -> with(property)
        }

        fun without(property: ExpectProperty) = copy(properties = properties - property)
        fun with(property: ExpectProperty) = copy(properties = properties + property)
        fun append(word: String) = copy(line = "$line $word")
        fun append(any: Any?) = append(word = any.toString())

        override fun toString() = line
    }

}

