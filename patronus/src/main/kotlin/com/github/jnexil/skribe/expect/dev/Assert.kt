package com.github.jnexil.skribe.expect.dev

import com.github.jnexil.skribe.calling.*
import com.github.jnexil.skribe.expect.Expect.*
import com.github.jnexil.skribe.expect.dev.ExpectProperty.*

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
    if (!condition()) InterruptedExpectError.fail(line)
}

private fun <T: Any, R> Backend<T>.inspect(extractor: Backend<T>.() -> R): R = try {
    extractor()
} catch (e: Throwable) {
    InterruptedExpectError.inspectionFail(this, e)
}

private val <T: Any> Backend<T>.negative: Boolean get() = Negative in properties