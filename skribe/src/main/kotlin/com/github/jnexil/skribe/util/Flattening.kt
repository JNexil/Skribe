package com.github.jnexil.skribe.util

import com.github.jnexil.skribe.multi.*
import com.github.jnexil.skribe.testable.*

private val describeToString = Any?::toString

fun <I, V, O> Intermediate<I>.couple(values: Iterable<V>, descriptor: (V) -> String = describeToString, zipper: (I, V) -> O) = couple(
        values = values.asSequence(),
        descriptor = descriptor,
        zipper = zipper
                                                                                                                                     )

fun <I, V, O> Intermediate<I>.couple(vararg values: V, descriptor: (V) -> String = describeToString, zipper: (I, V) -> O) = couple(
        values = values.asSequence(),
        descriptor = descriptor,
        zipper = zipper
                                                                                                                                  )

fun <I, V, O> Intermediate<I>.couple(values: Sequence<V>, descriptor: (V) -> String = describeToString, zipper: (I, V) -> O) = values.map { value ->
    move(description = descriptor(value)) { input ->
        zipper(input, value)
    }
}.flatten()

fun <T> Sequence<Intermediate<T>>.flatten(): Intermediate<T> = ExternalFlatIntermediate(this)