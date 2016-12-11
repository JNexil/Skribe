package com.github.jnexil.skribe.stepwise

interface Intermediate<out Subject>: Testable<Subject> {
    /**
     * @return Intermediate, whose all tests execute [action] to transform [Subject] to [R]
     */
    fun <R> then(description: String, action: (Subject) -> R): Intermediate<R>
}