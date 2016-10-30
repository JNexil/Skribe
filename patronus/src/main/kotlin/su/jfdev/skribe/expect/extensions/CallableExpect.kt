package su.jfdev.skribe.expect.extensions

import su.jfdev.skribe.calling.*
import su.jfdev.skribe.expect.*
import su.jfdev.skribe.expect.dev.*

val <R: Any> Expect<Calling<R>>.result: Expect<R> get() = via(Calling<R>::result)
val <R> Expect<Calling<R>>.duration: Expect<Duration> get() = via(Calling<R>::duration)
val <R> Expect<Calling<R>>.throwable: Expect<Throwable> get() = via(Calling<R>::throwable)

val <R> Expect<Calling<R>>.fails: Expect<Calling<R>> get() = backend().assertion("fails") { real.isFail }
val <R> Expect<Calling<R>>.done: Expect<Calling<R>> get() = backend().negation("done") { real.isFail }
val <R> Expect<Calling<R>>.success: Expect<Calling<R>> get() = backend().negation("success") { real.isFail }

val <R> Expect<Calling<R>>.assertion: Expect<Calling<R>> get() = backend().negation("assertion") { real.throwable is AssertionError }

fun <R> Expect<Calling<R>>.throwable(type: Class<out Throwable>): Expect<Calling<R>> = backend().negation("throwable $type") {
    real.throwable != null && type.isInstance(real.throwable)
}

fun <R> Expect<Calling<R>>.error(type: Class<out Error>): Expect<Calling<R>> = backend().negation("error $type") {
    real.throwable != null && type.isInstance(real.throwable)
}

fun <R> Expect<Calling<R>>.exception(type: Class<out Exception>): Expect<Calling<R>> = backend().negation("exception $type") {
    real.throwable != null && type.isInstance(real.throwable)
}
