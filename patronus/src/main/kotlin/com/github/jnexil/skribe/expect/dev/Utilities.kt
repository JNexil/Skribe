package com.github.jnexil.skribe.expect.dev

import com.github.jnexil.skribe.calling.*
import com.github.jnexil.skribe.expect.*
import com.github.jnexil.skribe.expect.Expect.*

fun <F: Any, T: Any> Backend<F>.map(transforming: Backend<F>.() -> T?): Expect<T> = Backend(lazy = lazy {
    val calling = Calling { transforming() }
    negate { calling.isFail }
    calling.result
}, properties = properties, line = line)

internal fun <T: Any> Expect<T>.backend() = this as Backend<T>