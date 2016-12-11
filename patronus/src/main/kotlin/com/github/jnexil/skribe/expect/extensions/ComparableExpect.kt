package com.github.jnexil.skribe.expect.extensions

import com.github.jnexil.skribe.expect.*
import com.github.jnexil.skribe.expect.dev.*

fun <C: Comparable<C>> Expect<C>.within(range: ClosedRange<C>) = within(range.start, range.endInclusive)
fun <C: Comparable<C>> Expect<C>.within(min: C, max: C): Expect<Comparable<C>> = backend()
        .append("within $min..$max")
        .verify { real >= min && real <= max }

fun <C: Comparable<C>> Expect<C>.most(other: C): Expect<Comparable<C>> = backend()
        .append("most $other")
        .verify { real <= other }


fun <C: Comparable<C>> Expect<C>.least(other: C): Expect<Comparable<C>> = backend()
        .append("least $other")
        .verify { real >= other }


fun <C: Comparable<C>> Expect<C>.above(other: C): Expect<Comparable<C>> = backend()
        .append("above $other")
        .verify { real > other }


fun <C: Comparable<C>> Expect<C>.below(other: C): Expect<Comparable<C>> = backend()
        .append("below $other")
        .verify { real < other }
