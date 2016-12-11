package com.github.jnexil.skribe.stepwise

import com.github.jnexil.skribe.adapter.*
import kotlin.reflect.*
import kotlin.test.*

/**
 * When end description contains name of annotated function
 */
annotation class Described

/**
 * When end description don't contain name of annotated function
 */
annotation class UnDescribed

@UnDescribed fun <S> Stepwise<S>.`checking not`(action: String, block: (S) -> Boolean) = checking(action, block)
@UnDescribed fun <S> Stepwise<S>.checking(action: String, block: (S) -> Boolean) = after(action) {
    it.assert(action, block)
}

@Described
fun <S> Testable<S>.should(action: String, block: (S) -> Boolean): CaseAdapter {
    val message = "should $action"
    return test(message) {
        it.assert(message, block)
    }
}

fun <S> Testable<S>.`should not`(action: String, block: (S) -> Boolean): CaseAdapter {
    val message = "should not $action"
    return test(message) {
        it.negate(message, block)
    }
}


fun <S> Testable<S>.catch(expect: KClass<out Throwable>, way: String, block: () -> Unit): CaseAdapter {
    val message = "should throw ${expect.java.simpleName} when $way"
    return test(message) {
        assertFailsWith(expect, message, block)
    }
}

fun <S> S.assert(message: String, block: (S) -> Boolean) = assertTrue(message) {
    block(this)
}

fun <S> S.negate(message: String, block: (S) -> Boolean) = assertFalse(message) {
    block(this)
}

inline fun SuiteAdapter.stepwise(using: Stepwise<Unit>.() -> Unit): Stepwise<Unit> = stepwise().apply(using)
inline fun <S> Intermediate<S>.stepwise(using: Stepwise<S>.() -> Unit): Stepwise<S> = stepwise().apply(using)

fun SuiteAdapter.stepwise(): Stepwise<Unit> = with { }.stepwise()
fun <S> SuiteAdapter.with(factory: () -> S): Intermediate<S> = AdaptIntermediate(suite = this, factory = factory)
fun <S> Intermediate<S>.stepwise(): Stepwise<S> = AdaptStepwise(this)