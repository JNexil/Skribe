package su.jfdev.skribe.calling

import java.lang.Math.*
import java.util.concurrent.*

enum class DurationUnit(@Suppress("unused") val longName: String, val shortName: String, prev: Long) {
    NANO("nanosecond", "ns", 1),
    MICRO("microsecond", "mcs", 1000),
    MILLI("millisecond", "ms", 1000),
    SECOND("second", "s", 1000),
    MINUTE("minute", "m", 60),
    HOUR("hour", "h", 60),
    DAY("day", "d", 24),
    WEEK("week", "w", 7);

    val nanos: Long = when (ordinal) {
        0    -> 1L
        else -> values()[ordinal - 1].nanos * prev
    }

    val unit = Duration(nanos)

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