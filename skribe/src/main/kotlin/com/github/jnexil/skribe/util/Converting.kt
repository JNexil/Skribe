package com.github.jnexil.skribe.util

import com.github.jnexil.skribe.testable.*

@Undescribed
val <S> Intermediate<S>.stepwise: Stepwise<S> get() = InterStepwise(this)

@Undescribed
inline fun <S> Intermediate<S>.stepwise(description: String, using: Stepwise<S>.() -> Unit) = stepwise(description).apply(using)

@Undescribed
inline fun <S> Intermediate<S>.stepwise(using: Stepwise<S>.() -> Unit): Stepwise<S> = stepwise.apply(using)

@Undescribed
fun <S> Intermediate<S>.stepwise(description: String) = share(description).stepwise

fun <S, R> Stepwise<S>.movingStep(description: String, action: (S) -> R): Intermediate<R> {
    step(description).step { action(it) }
    return move(description, action)
}