package su.jfdev.skribe.expect.dev

import su.jfdev.skribe.calling.*
import su.jfdev.skribe.expect.*
import su.jfdev.skribe.expect.Expect.*

fun <F: Any, T: Any> Backend<F>.map(transforming: Backend<F>.() -> T?): Expect<T> = Backend(lazy = kotlin.lazy {
    val calling = Calling { transforming() }
    negate { calling.isFail }
    calling.result
}, properties = properties, line = line)

internal fun <T: Any> Expect<T>.backend() = this as Backend<T>