package su.jfdev.skribe.expect.dev

import su.jfdev.skribe.expect.Expect.*
import su.jfdev.skribe.expect.dev.ExpectProperty.*

inline fun <T: Any> Backend<T>.assert(condition: Backend<T>.() -> Boolean) = apply {
    val expected = Negative !in properties
    assert(condition() == expected) {
        toString()
    }
}

inline fun <T: Any> Backend<T>.negate(condition: Backend<T>.() -> Boolean) = assert { !condition() }