package com.github.jnexil.skribe.testable

internal class InterStepwise<out Subject>(private var last: Intermediate<Subject>): Stepwise<Subject> {
    override fun test(description: String, action: (Subject) -> Unit) = last.test(description, action)
    override fun <R> move(description: String, action: (Subject) -> R) = last.move(description, action)
    override fun share(description: String) = last.share(description)
    override fun <R> move(action: (Subject) -> R) = last.move(action)
    override fun move(): Intermediate<Subject> = last

    override fun step(description: String): Stepwise<Subject> = apply {
        last = share(description)
    }

    override fun step(action: (Subject) -> Unit): Stepwise<Subject> = apply {
        last = move(action = selfReturning(action))
    }

    override fun step(description: String, action: (Subject) -> Unit): Stepwise<Subject> = apply {
        last = move(description, selfReturning(action))
    }

    private fun selfReturning(action: (Subject) -> Unit): (Subject) -> Subject = {
        it.apply { action(it) }
    }
}