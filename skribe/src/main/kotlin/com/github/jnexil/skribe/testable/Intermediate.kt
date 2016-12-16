package com.github.jnexil.skribe.testable

interface Intermediate<out Subject>: Testable<Subject> {
    /**
     * @return Intermediate, whose all tests execute [action] to transform [Subject] to [R]
     */
    fun <R> move(description: String, action: (Subject) -> R): Intermediate<R>

    /**
     * @return this intermediate
     */
    fun move(): Intermediate<Subject> = this

    /**
     * @return new intermediate with {it} action and [description]
     */
    infix fun share(description: String): Intermediate<Subject>

    /**
     * @return new intermediate [action] without description
     */
    infix fun <R> move(action: (Subject) -> R): Intermediate<R>
}