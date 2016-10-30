package su.jfdev.skribe.expect.extensions

import su.jfdev.skribe.calling.*
import su.jfdev.skribe.expect.*
import su.jfdev.skribe.expect.dev.*
import kotlin.reflect.*

val <R: Any> Expect<Calling<R>>.result: Expect<R> get() = via(Calling<R>::result)
val <R> Expect<Calling<R>>.duration: Expect<Duration> get() = via(Calling<R>::duration)
val <R> Expect<Calling<R>>.throwable: Expect<Throwable> get() = via(Calling<R>::throwable)

val <R> Expect<Calling<R>>.fails: Expect<Calling<R>> get() = backend().assertion("fails") { real.isFail }
val <R> Expect<Calling<R>>.done: Expect<Calling<R>> get() = backend().negation("done") { real.isFail }
val <R> Expect<Calling<R>>.success: Expect<Calling<R>> get() = backend().negation("success") { real.isFail }

val <R> Expect<Calling<R>>.assertion: Expect<Calling<R>> get() = backend().negation("assertion") { real.throwable is AssertionError }

fun <R> Expect<Calling<R>>.throwable(type: KClass<out Throwable>): Expect<Calling<R>> = backend().instanceAssertion(type.java, "throwable ${type.simpleName}")
fun <R> Expect<Calling<R>>.error(type: KClass<out Error>): Expect<Calling<R>> = backend().instanceAssertion(type.java, "error ${type.simpleName}")
fun <R> Expect<Calling<R>>.exception(type: KClass<out Exception>): Expect<Calling<R>> = backend().instanceAssertion(type.java, "exception ${type.simpleName}")
