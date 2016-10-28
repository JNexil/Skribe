package su.jfdev.skribe.expect.extensions

import su.jfdev.skribe.expect.*
import su.jfdev.skribe.expect.dev.*

fun <C: Comparable<C>> Expect<C>.within(range: ClosedRange<C>) = within(range.start, range.endInclusive)
fun <C: Comparable<C>> Expect<C>.within(min: C, max: C): Expect<Comparable<C>> = backend()
        .append("within $min..$max")
        .assert { real >= min && real <= max }

fun <C: Comparable<C>> Expect<C>.most(other: C): Expect<Comparable<C>> = backend()
        .append("most $other")
        .assert { real <= other }


fun <C: Comparable<C>> Expect<C>.least(other: C): Expect<Comparable<C>> = backend()
        .append("least $other")
        .assert { real >= other }


fun <C: Comparable<C>> Expect<C>.above(other: C): Expect<Comparable<C>> = backend()
        .append("above $other")
        .assert { real > other }


fun <C: Comparable<C>> Expect<C>.below(other: C): Expect<Comparable<C>> = backend()
        .append("below $other")
        .assert { real < other }
