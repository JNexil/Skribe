package su.jfdev.skribe.expect.extensions

import su.jfdev.skribe.calling.*
import su.jfdev.skribe.expect.*
import su.jfdev.skribe.expect.dev.*

val Expect<Duration>.nanos: Expect<Long> get() = via(Duration::nanos)
val Expect<Duration>.millis: Expect<Long> get() = via(Duration::millis)
val Expect<Duration>.seconds: Expect<Long> get() = via(Duration::seconds)
val Expect<Duration>.minutes: Expect<Long> get() = via(Duration::minutes)
val Expect<Duration>.hours: Expect<Long> get() = via(Duration::hours)
val Expect<Duration>.days: Expect<Long> get() = via(Duration::days)
val Expect<Duration>.weeks: Expect<Long> get() = via(Duration::weeks)