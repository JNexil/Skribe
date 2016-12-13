package com.github.jnexil.skribe.testable

import com.github.jnexil.skribe.adapter.*

interface Stepwise<out Subject>: Intermediate<Subject> {
    /**
     * Executes early defined [step] actions and test
     */
    override fun test(description: String, action: (Subject) -> Unit): CaseAdapter

    /**
     * Creates intermediate, that executes early defined [step] and own constructions
     */
    override fun <R> skribe(description: String, action: (Subject) -> R): Intermediate<R>

    fun <R> step(description: String, action: (Subject) -> R): Intermediate<R>
    fun step(description: String, action: (Subject) -> Unit)
}