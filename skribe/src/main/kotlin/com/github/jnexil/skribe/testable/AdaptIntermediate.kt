package com.github.jnexil.skribe.testable

import com.github.jnexil.skribe.adapter.*

internal class AdaptIntermediate<out Subject>(val suite: SuiteAdapter, val factory: () -> Subject): Intermediate<Subject> {
    override fun test(description: String, action: (Subject) -> Unit) = suite.case(description) {
        factory().run(action)
    }

    override fun <R> move(description: String, action: (Subject) -> R): Intermediate<R> = AdaptIntermediate(suite = suite.suite(description)) {
        factory().run(action)
    }

    override fun share(description: String): Intermediate<Subject> {
        return AdaptIntermediate(suite = suite.suite(description), factory = factory)
    }

    override fun <R> move(action: (Subject) -> R): Intermediate<R> = AdaptIntermediate(suite) {
        factory().run(action)
    }
}