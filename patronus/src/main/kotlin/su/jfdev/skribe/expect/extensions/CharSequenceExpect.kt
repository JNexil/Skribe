package su.jfdev.skribe.expect.extensions

import su.jfdev.skribe.expect.*
import su.jfdev.skribe.expect.dev.*

val <S: CharSequence> Expect<S>.empty: Expect<S> get() = backend().assertion("empty") { real.isEmpty() }
val <S: CharSequence> Expect<S>.length: Expect<Int> get() = via(CharSequence::length)
fun <S: CharSequence> Expect<S>.subSequence(range: IntRange): Expect<CharSequence> = backend()
        .map { real.subSequence(range) }

fun <S: CharSequence> Expect<S>.match(regex: Regex): Expect<S> = backend().assertion("match ${regex.pattern}") {
    real.matches(regex)
}

fun <S: CharSequence> Expect<S>.length(length: Int): Expect<S> = backend().assertion("length $length") {
    real.length == length
}

fun <S: CharSequence> Expect<S>.equal(chars: CharSequence, ignoreCase: Boolean = false): Expect<S> = backend().assertion("equal $chars") {
    when {
        ignoreCase -> real.toString().equals(other = chars.toString(), ignoreCase = true)
        else       -> real == chars
    }
}

fun <S: CharSequence> Expect<S>.contains(chars: CharSequence): Expect<S> = backend().assertion("contains $chars") {
    chars in real
}

fun <S: CharSequence> Expect<S>.start(chars: CharSequence): Expect<S> = backend().assertion("start $chars") {
    real.startsWith(chars)
}

fun <S: CharSequence> Expect<S>.end(chars: CharSequence): Expect<S> = backend().assertion("end $chars") {
    real.endsWith(chars)
}


