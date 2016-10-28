package su.jfdev.skribe.expect.extensions

import su.jfdev.skribe.expect.*
import su.jfdev.skribe.expect.dev.*

val Expect<Boolean>.`true`: Expect<Boolean> get() = backend().sameAssertion(true)
val Expect<Boolean>.`false`: Expect<Boolean> get() = backend().sameAssertion(false)