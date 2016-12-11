package com.github.jnexil.skribe.expect.extensions

import com.github.jnexil.skribe.expect.*
import com.github.jnexil.skribe.expect.dev.*
import kotlin.reflect.*

val <S: Any> Expect<S>.toString: Expect<String> get() = via(Any::toString)
val <S: Any> Expect<S>.hashCode: Expect<Int> get() = via(Any::hashCode)

val <S: Any> Expect<S>.`null`: Expect<S> get() = backend()
        .sameAssertion(null)

fun <S: Any> Expect<S>.satisfy(name: String = "condition", condition: (S) -> Boolean): Expect<S> = backend()
        .append("satisfy")
        .append(name)
        .verify { condition(real) }

fun <S: Any> Expect<S>.equal(other: S?): Expect<S> = backend()
        .equalAssertion(other, "equal $other")

fun <S: Any> Expect<S>.identity(other: S?): Expect<S> = backend()
        .sameAssertion(other, "identity $other")

inline fun <reified S: Any> Expect<S>.instanceOf(type: KClass<S> = S::class): Expect<S> = instanceOf(type.java)
fun <S: Any> Expect<S>.instanceOf(type: Class<S>): Expect<S> = backend().instanceAssertion(type, "instance of ${type.simpleName}")