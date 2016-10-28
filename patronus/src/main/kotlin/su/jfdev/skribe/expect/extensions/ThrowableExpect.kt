package su.jfdev.skribe.expect.extensions

import su.jfdev.skribe.expect.*
import su.jfdev.skribe.expect.dev.*

fun <T: Throwable> Expect<T>.message(message: CharSequence, ignoreCase: Boolean = true): Expect<T> = backend().assertion("message $message") {
    real.message?.equals(message.toString(), ignoreCase = ignoreCase) ?: false
}

val <T: Throwable> Expect<T>.message: Expect<String> get() = via(Throwable::message)
val <T: Throwable> Expect<T>.cause: Expect<Throwable> get() = via(Throwable::cause)
val <T: Throwable> Expect<T>.stacktrace: Expect<Collection<StackTraceElement>> get() = backend().mapping("stacktrace") {
    real.stackTrace.toList()
}