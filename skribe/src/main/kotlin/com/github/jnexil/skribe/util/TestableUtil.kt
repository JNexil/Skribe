package com.github.jnexil.skribe.util

import com.github.jnexil.skribe.adapter.*
import com.github.jnexil.skribe.testable.*
import kotlin.reflect.*
import kotlin.test.*

@Described
fun <S> Testable<S>.should(action: String, block: (S) -> Boolean): CaseAdapter {
    val message = "should $action"
    return test(message) {
        it.assert(message, block)
    }
}

@Described
fun <S> Testable<S>.`should not`(action: String, block: (S) -> Boolean): CaseAdapter {
    val message = "should not $action"
    return test(message) {
        it.negate(message, block)
    }
}

@Described
fun <S> Testable<S>.catch(expect: KClass<out Throwable>, way: String, block: (S) -> Unit): CaseAdapter {
    val message = "should throw ${expect.java.simpleName} when $way"
    return test(message) {
        assertFailsWith(expect, message) { it.run(block) }
    }
}

