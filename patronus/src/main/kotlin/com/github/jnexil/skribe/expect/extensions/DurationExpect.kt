package com.github.jnexil.skribe.expect.extensions

import com.github.jnexil.skribe.calling.*
import com.github.jnexil.skribe.expect.*
import com.github.jnexil.skribe.expect.dev.*

val Expect<Duration>.nanos: Expect<Long> get() = via(Duration::nanos)
val Expect<Duration>.micros: Expect<Long> get() = via(Duration::micros)
val Expect<Duration>.millis: Expect<Long> get() = via(Duration::millis)
val Expect<Duration>.seconds: Expect<Long> get() = via(Duration::seconds)
val Expect<Duration>.minutes: Expect<Long> get() = via(Duration::minutes)
val Expect<Duration>.hours: Expect<Long> get() = via(Duration::hours)
val Expect<Duration>.days: Expect<Long> get() = via(Duration::days)
val Expect<Duration>.weeks: Expect<Long> get() = via(Duration::weeks)