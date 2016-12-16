/**
 * Functions where used lambda 'this' instead 'it'
 */
@file:Suppress("NOTHING_TO_INLINE")

package com.github.jnexil.skribe.util

import com.github.jnexil.skribe.testable.*
import kotlin.reflect.*

@Undescribed
inline fun <S> Testable<S>.testR(action: String, noinline block: S.() -> Unit) = test(action, block)

@Described
inline fun <S> Testable<S>.shouldR(action: String, noinline block: S.() -> Boolean) = should(action, block)

@Described
inline fun <S> Testable<S>.`shouldR not`(action: String, noinline block: S.() -> Boolean) = `should not`(action, block)

@Described
inline fun <S> Testable<S>.catchR(expect: KClass<out Throwable>, way: String, noinline block: S.() -> Unit) = catch(expect, way, block)

@Undescribed
inline fun <S, R> Intermediate<S>.moveR(description: String, noinline action: S.() -> R) = move(description, action)

@Undescribed
inline fun <S, R> Intermediate<S>.moveR(noinline action: S.() -> R) = move(action)

@Undescribed
inline fun <S, R> Intermediate<S>.moveCallingR(noinline action: S.() -> R) = moveCalling(action)

@Undescribed
inline fun <S, R> Stepwise<S>.stepCallingR(noinline action: S.() -> R) = stepCalling(action)

@Undescribed
inline fun <S> Stepwise<S>.stepR(description: String, noinline action: S.() -> Unit) = step(description, action)

@Undescribed
inline fun <S> Stepwise<S>.stepR(noinline action: S.() -> Unit) = step(action)

@Undescribed
inline fun <S> Stepwise<S>.movingStepR(noinline action: S.() -> Unit) = movingStep(action)

@Undescribed
inline fun <S> Stepwise<S>.movingStepR(description: String, noinline action: S.() -> Unit) = movingStep(description, action)
