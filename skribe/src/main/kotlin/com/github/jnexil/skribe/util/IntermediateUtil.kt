package com.github.jnexil.skribe.util

import com.github.jnexil.skribe.calling.*
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
fun <S> Intermediate<S>.share(description: String) = move(description) { it }

@Undescribed
inline fun <S> Intermediate<S>.share(description: String, action: Intermediate<S>.() -> Unit) {
    share(description).action()
}

inline fun <S, R> Intermediate<S>.moveToCalling(description: String, crossinline action: (S) -> R) = move(description) {
    Calling { action(it) }
}