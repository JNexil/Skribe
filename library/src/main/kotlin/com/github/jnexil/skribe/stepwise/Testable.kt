package com.github.jnexil.skribe.stepwise

import com.github.jnexil.skribe.adapter.*

interface Testable<out Subject> {
    /**
     * Execute test with [Subject]
     */
    fun test(description: String, action: (Subject) -> Unit): CaseAdapter
}

