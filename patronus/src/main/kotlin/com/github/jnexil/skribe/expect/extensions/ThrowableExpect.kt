package com.github.jnexil.skribe.expect.extensions

import com.github.jnexil.skribe.expect.*
import com.github.jnexil.skribe.expect.dev.*

fun <T: Throwable> Expect<T>.message(message: CharSequence, ignoreCase: Boolean = true): Expect<T> = backend().assertion("message $message") {
    real.message?.equals(message.toString(), ignoreCase = ignoreCase) ?: false
}

val <T: Throwable> Expect<T>.message: Expect<String> get() = via(Throwable::message)
val <T: Throwable> Expect<T>.cause: Expect<Throwable> get() = via(Throwable::cause)
val <T: Throwable> Expect<T>.stacktrace: Expect<Collection<StackTraceElement>> get() = backend().mapping("stacktrace") {
    real.stackTrace.toList()
}