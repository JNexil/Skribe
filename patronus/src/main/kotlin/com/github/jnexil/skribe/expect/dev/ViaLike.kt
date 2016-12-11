package com.github.jnexil.skribe.expect.dev

import com.github.jnexil.skribe.expect.*
import kotlin.reflect.*


fun <F: Any, T: Any> Expect<F>.via(property: KProperty1<F, T?>): Expect<T> = viaCallable(property)
fun <F: Any, T: Any> Expect<F>.via(function: KFunction1<F, T?>): Expect<T> = viaCallable(function)

private fun <F: Any, T: Any> Expect<F>.viaCallable(callable: KCallable<T?>): Expect<T> = backend().mapping(callable.name) {
    val nullable = callable.parameters.first().type.isMarkedNullable
    val value = if (nullable) real else value
    callable.call(value)
}

fun <F: Any, T: Any> Expect<F>.like(clazz: Class<T>): Expect<T> = backend()
        .append("like $clazz")
        .verify { clazz.isInstance(real) }
        .map { clazz.cast(real) }
