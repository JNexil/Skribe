package su.jfdev.skribe.expect.dev

import su.jfdev.skribe.expect.*
import su.jfdev.skribe.expect.Expect.*

fun <F: Any, T: Any> Backend<F>.mapping(text: String, transforming: Backend<F>.() -> T?): Expect<T> = append(text).map(transforming)
fun <T: Any> Backend<T>.equalNegation(expected: T?, text: String = expected.toString()) = assertion(text) { value != expected }
fun <T: Any> Backend<T>.sameNegation(expected: T?, text: String = expected.toString()) = assertion(text) { value !== expected }
fun <T: Any> Backend<T>.instanceNegation(expected: Class<*>, text: String = expected.toString()) = assertion(text) { !expected.isInstance(real) }

fun <T: Any> Backend<T>.equalAssertion(expected: T?, text: String = expected.toString()) = assertion(text) { value == expected }
fun <T: Any> Backend<T>.sameAssertion(expected: T?, text: String = expected.toString()) = assertion(text) { value === expected }
fun <T: Any> Backend<T>.instanceAssertion(expected: Class<*>, text: String = expected.toString()) = assertion(text) { expected.isInstance(real) }

fun <T: Any> Backend<T>.negation(text: String, assert: Backend<T>.() -> Boolean): Expect<T> = append(text).negate(assert)
fun <T: Any> Backend<T>.assertion(text: String, assert: Backend<T>.() -> Boolean): Expect<T> = append(text).verify(assert)