package com.github.jnexil.skribe.stepwise

import com.github.jnexil.skribe.adapter.*

internal class AdaptIntermediate<out Subject>(val suite: SuiteAdapter, val factory: () -> Subject): Intermediate<Subject> {
    override fun test(description: String, action: (Subject) -> Unit) = suite.case(description) {
        factory().run(action)
    }

    override fun <R> then(description: String, action: (Subject) -> R): AdaptIntermediate<R> = AdaptIntermediate(suite = suite.suite(description)) {
        factory().run(action)
    }
}

