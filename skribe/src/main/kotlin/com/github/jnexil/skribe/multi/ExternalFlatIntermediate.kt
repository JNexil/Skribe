package com.github.jnexil.skribe.multi

import com.github.jnexil.skribe.adapter.*
import com.github.jnexil.skribe.testable.*
import com.github.jnexil.skribe.util.*

internal class ExternalFlatIntermediate<out S>(private val basic: Sequence<Intermediate<S>>): Intermediate<S> {
    override fun share(description: String): Intermediate<S> = basic.map {
        it.share(description)
    }.flatten()

    override fun test(description: String, action: (S) -> Unit): CaseAdapter {
        val cases = basic.map {
            it.test(description, action)
        }.buffered()
        return MultiCase(cases)
    }

    override fun <R> move(description: String, action: (S) -> R): Intermediate<R> = basic.map {
        it.move(description, action)
    }.flatten()

    override fun <R> move(action: (S) -> R): Intermediate<R> = basic.map {
        it.move(action)
    }.flatten()

    inner class MultiCase(val cases: Sequence<CaseAdapter>): CaseAdapter
}