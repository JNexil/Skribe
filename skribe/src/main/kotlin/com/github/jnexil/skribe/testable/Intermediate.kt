package com.github.jnexil.skribe.testable

interface Intermediate<out Subject>: Testable<Subject> {
    /**
     * @return Intermediate, whose all tests execute [action] to transform [Subject] to [R]
     */
    fun <R> skribe(description: String, action: (Subject) -> R): Intermediate<R>
}