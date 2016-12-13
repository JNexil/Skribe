package com.github.jnexil.skribe.testable

import com.github.jnexil.skribe.adapter.*

interface Testable<out Subject> {
    /**
     * Execute test intermediate [Subject]
     */
    fun test(description: String, action: (Subject) -> Unit): CaseAdapter
}

