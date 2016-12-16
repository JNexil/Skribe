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
inline fun <S, R> Intermediate<S>.skribeR(description: String, noinline action: S.() -> R) = move(description, action)

@Undescribed
inline fun <S, R> Intermediate<S>.skribeRCalling(description: String, noinline action: (S) -> R) = skribeCalling(description, action)