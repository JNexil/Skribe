package su.jfdev.skribe.stepwise

import su.jfdev.skribe.adapter.*

interface Testable<out Subject> {
    /**
     * Execute test with [Subject]
     */
    fun test(description: String, action: (Subject) -> Unit): CaseAdapter
}

