package com.github.jnexil.skribe.calling

import java.lang.Math.*
import java.util.concurrent.*

enum class DurationUnit(@Suppress("unused") val longName: String, val shortName: String, `count of previous`: Long = 1, previous: DurationUnit? = null) {
    NANO("nanosecond", "ns"),
    MICRO("microsecond", "mcs", `count of previous` = 1000, previous = NANO),
    MILLI("millisecond", "ms", `count of previous` = 1000, previous = MICRO),
    SECOND("second", "s", `count of previous` = 1000, previous = MILLI),
    MINUTE("minute", "m", `count of previous` = 60, previous = SECOND),
    HOUR("hour", "h", `count of previous` = 60, previous = MINUTE),
    DAY("day", "d", `count of previous` = 24, previous = HOUR),
    WEEK("week", "w", `count of previous` = 7, previous = DAY);

    val nanos: Long = when (previous) {
        null -> 1L
        else -> previous.nanos * `count of previous`
    }

    val unit by lazy { Duration(nanos) }

    val max: Long = Long.MAX_VALUE / nanos

    operator fun get(count: Long): Duration = when {
        count == 1L      -> unit
        abs(count) > max -> overflow(count)
        else             -> Duration(nanos * count)
    }

    operator fun get(count: Int): Duration = when {
        count == 1       -> unit
        abs(count) > max -> overflow(count)
        else             -> Duration(nanos * count)
    }

    operator fun get(count: Double): Duration = when {
        count == .0      -> unit
        abs(count) > max -> overflow(count)
        else             -> Duration((nanos * count).toLong())
    }

    operator fun get(count: Float): Duration = when {
        count == 1F      -> unit
        abs(count) > max -> overflow(count)
        else             -> Duration((nanos * count).toLong())
    }

    private fun overflow(count: Number): Nothing = throw ArithmeticException("Overflow when $count * $nanos")

    companion object {
        private val values = values()
        val size: Int get() = values.size
        operator fun get(num: Int) = values[num]
        operator fun get(timeUnit: TimeUnit) = get(timeUnit.ordinal)
    }
}