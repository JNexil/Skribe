package com.github.jnexil.skribe.testable

internal class AdaptStepwise<out Subject>(private var last: Intermediate<Subject>): Stepwise<Subject> {
    override fun test(description: String, action: (Subject) -> Unit) = last.test(description, action)
    override fun <R> skribe(description: String, action: (Subject) -> R) = last.skribe(description, action)

    override fun <R> step(description: String, action: (Subject) -> R): Intermediate<R> {
        step(description) { action(it) }
        return skribe(description, action)
    }

    override fun step(description: String, action: (Subject) -> Unit) {
        last = last.skribe(description) {
            it.apply { action(it) }
        }
    }
}