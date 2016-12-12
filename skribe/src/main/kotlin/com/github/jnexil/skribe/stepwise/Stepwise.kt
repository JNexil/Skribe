package com.github.jnexil.skribe.stepwise

import com.github.jnexil.skribe.adapter.*

interface Stepwise<out Subject>: Intermediate<Subject> {
    /**
     * Executes early defined [after] actions and test
     */
    override fun test(description: String, action: (Subject) -> Unit): CaseAdapter

    /**
     * Creates intermediate, that executes early defined [after] and own constructions
     */
    override fun <R> then(description: String, action: (Subject) -> R): Intermediate<R>

    fun <R> after(description: String, action: (Subject) -> R): Intermediate<R>
    fun after(description: String, action: (Subject) -> Unit)
}