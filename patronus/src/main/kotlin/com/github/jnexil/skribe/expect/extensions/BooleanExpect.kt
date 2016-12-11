package com.github.jnexil.skribe.expect.extensions

import com.github.jnexil.skribe.expect.*
import com.github.jnexil.skribe.expect.dev.*

val Expect<Boolean>.`true`: Expect<Boolean> get() = backend().sameAssertion(true)
val Expect<Boolean>.`false`: Expect<Boolean> get() = backend().sameAssertion(false)