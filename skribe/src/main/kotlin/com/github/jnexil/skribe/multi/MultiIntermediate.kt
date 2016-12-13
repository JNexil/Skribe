package com.github.jnexil.skribe.multi

import com.github.jnexil.skribe.adapter.*
import com.github.jnexil.skribe.testable.*

class MultiIntermediate<out S>(private val basic: Intermediate<Sequence<S>>): Intermediate<S> {
    override fun test(description: String, action: (S) -> Unit): CaseAdapter = basic.test(description) {
        it.forEach(action)
    }

    override fun <R> skribe(description: String, action: (S) -> R): Intermediate<R> {
        val basic = basic.skribe(description) { it.map(action) }
        return MultiIntermediate(basic)
    }
}