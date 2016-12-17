package com.github.jnexil.skribe.testable

import com.github.jnexil.skribe.adapter.*
import mu.*

internal class AdaptIntermediate<out Subject>(val suite: SuiteAdapter, val factory: () -> Subject): Intermediate<Subject> {
    override fun test(description: String, action: (Subject) -> Unit) = suite.case(description) {
        doAction(description, action)
    }

    override fun <R> move(description: String, action: (Subject) -> R): Intermediate<R> = AdaptIntermediate(suite = suite.suite(description)) {
        doAction(description, action)
    }

    private inline fun <R> doAction(description: String, action: (Subject) -> R): R {
        val subject = factory()
        val mapped = action(subject)
        logger.info {
            buildString {
                append('#')
                append(' ')
                append(description)
                append('\t')
                append('#')
                append(subject.name())
                append(" -> ")
                when (subject) {
                    mapped -> append('|')
                    else   -> append(mapped.name())
                }
            }
        }
        return mapped
    }

    private fun Any?.name(): String = when (this) {
        is Unit -> "Unit"
        null    -> "null"
        else    -> this.javaClass.simpleName
    }

    override fun share(description: String): Intermediate<Subject> {
        return AdaptIntermediate(suite = suite.suite(description), factory = factory)
    }

    override fun <R> move(action: (Subject) -> R): Intermediate<R> = AdaptIntermediate(suite) {
        factory().run(action)
    }

    companion object: NamedKLogging(name = Intermediate::class.java.name)
}