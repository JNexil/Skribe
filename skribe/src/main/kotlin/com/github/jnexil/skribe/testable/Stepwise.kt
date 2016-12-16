package com.github.jnexil.skribe.testable

import com.github.jnexil.skribe.adapter.*

interface Stepwise<out Subject>: Intermediate<Subject> {
    /**
     * Executes early defined [step] actions and test
     */
    override fun test(description: String, action: (Subject) -> Unit): CaseAdapter

    fun step(description: String): Stepwise<Subject>
    fun step(action: (Subject) -> Unit): Stepwise<Subject>
    fun step(description: String, action: (Subject) -> Unit): Stepwise<Subject>
}