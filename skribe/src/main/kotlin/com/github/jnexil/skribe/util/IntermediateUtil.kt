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
inline fun <S> Intermediate<S>.share(description: String, action: Intermediate<S>.() -> Unit) {
    share(description).action()
}

inline fun <S, R> Intermediate<S>.moveCalling(crossinline action: (S) -> R) = move {
    Calling { action(it) }
}

inline fun <S, R> Stepwise<S>.stepCalling(crossinline action: (S) -> R) = step {
    Calling { action(it) }
}