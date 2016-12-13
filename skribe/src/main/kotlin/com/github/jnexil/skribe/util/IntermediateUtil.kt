package com.github.jnexil.skribe.util

import com.github.jnexil.skribe.calling.*
import com.github.jnexil.skribe.multi.*
import com.github.jnexil.skribe.testable.*

/**
 * When end description contains name of annotated function
 */
annotation class Described

/**
 * When end description don't contain name of annotated function
 */
annotation class Undescribed

@Undescribed
fun <S> Intermediate<S>.share(description: String) = skribe(description) { it }

@Undescribed
inline fun <S> Intermediate<S>.share(description: String, action: Intermediate<S>.() -> Unit) {
    share(description).action()
}

inline fun <S, R> Intermediate<S>.skribeCalling(description: String, crossinline action: (S) -> R) = skribe(description) {
    Calling { action(it) }
}

fun <T> Intermediate<Sequence<T>>.flatten() = MultiIntermediate(this)
fun <T, Z, O> Intermediate<T>.zip(description: String, zipValues: Sequence<Z>, zipper: (T, Z) -> O) = skribe(description) { t ->
    zipValues.map { zipper(t, it) }
}.flatten()

fun <T, Z, O> Intermediate<T>.zip(description: String, vararg zipValues: Z, zipper: (T, Z) -> O) = zip(description, zipValues.asSequence(), zipper)