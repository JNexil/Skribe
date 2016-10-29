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