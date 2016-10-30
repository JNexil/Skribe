package su.jfdev.skribe.expect.dev

import su.jfdev.skribe.calling.*
import su.jfdev.skribe.expect.Expect.*
import su.jfdev.skribe.expect.dev.ExpectProperty.*

/**
 * Create other backend for operation, that exclude words
 */
fun <T: Any> Backend<T>.nested(action: Backend<T>.() -> Unit) = apply {
    val line = when {
        negative -> "$real should not"
        else     -> "$real should"
    }
    copy(line = line).action()
}

fun <T: Any> Backend<T>.`verify done`(action: Backend<T>.() -> Unit) = verify {
    Calling { action() }.isDone
}

fun <T: Any> Backend<T>.negate(condition: Backend<T>.() -> Boolean) = assert {
    inspect(condition) == negative
}

fun <T: Any> Backend<T>.verify(condition: Backend<T>.() -> Boolean) = assert {
    inspect(condition) != negative
}

private inline fun <T: Any> Backend<T>.assert(condition: () -> Boolean) = apply {
    if (!condition()) InterruptedExpectError.error(toString())
}

private fun <T: Any, R> Backend<T>.inspect(extractor: Backend<T>.() -> R): R = try {
    extractor()
} catch (e: Throwable) {
    InterruptedExpectError.error(toString(), e)
}

private val <T: Any> Backend<T>.negative: Boolean get() = Negative in properties