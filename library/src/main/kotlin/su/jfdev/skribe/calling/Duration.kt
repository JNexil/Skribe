package su.jfdev.skribe.calling

import su.jfdev.skribe.calling.DurationUnit.*
import java.io.*
import java.util.concurrent.*

class Duration(nanos: Long): Comparable<Duration>, Serializable {
    private val array = LongArray(DurationUnit.size) {
        nanos * DurationUnit[it].nanos
    }
    val nanos: Long get() = array[0]
    val micros: Long get() = array[1]
    val millis: Long get() = array[2]
    val seconds: Long get() = array[3]
    val minutes: Long get() = array[4]
    val hours: Long get() = array[5]
    val days: Long get() = array[6]
    val weeks: Long get() = array[7]

    infix fun like(target: TimeUnit): Long = array[target.ordinal]
    infix fun like(target: DurationUnit): Long = array[target.ordinal]

    override fun compareTo(other: Duration): Int = nanos.compareTo(other.nanos)

    operator fun times(scalar: Int) = Duration(nanos * scalar)
    operator fun div(scalar: Int) = Duration(nanos / scalar)
    operator fun mod(scalar: Int) = Duration(nanos % scalar)
    operator fun plus(scalar: Int) = Duration(nanos + scalar)
    operator fun minus(scalar: Int) = Duration(nanos - scalar)

    operator fun times(scalar: Long) = Duration(nanos * scalar)
    operator fun div(scalar: Long) = Duration(nanos / scalar)
    operator fun mod(scalar: Long) = Duration(nanos % scalar)
    operator fun plus(scalar: Long) = Duration(nanos + scalar)
    operator fun minus(scalar: Long) = Duration(nanos - scalar)

    operator fun times(scalar: Duration) = Duration(nanos * scalar.nanos)
    operator fun div(scalar: Duration) = Duration(nanos / scalar.nanos)
    operator fun mod(scalar: Duration) = Duration(nanos % scalar.nanos)
    operator fun plus(scalar: Duration) = Duration(nanos + scalar.nanos)
    operator fun minus(scalar: Duration) = Duration(nanos - scalar.nanos)

    override fun equals(other: Any?) = this === other || other is Duration && other.nanos == nanos
    override fun hashCode(): Int = nanos.hashCode()
    override fun toString(): String = buildString {
        var remainder = nanos
        with(WEEK, DAY, HOUR, MINUTE, SECOND, MILLI, MICRO) {
            remainder = nextRemainder(it, remainder)
        }
        append(remainder, NANO)
    }

    private fun StringBuilder.nextRemainder(unit: DurationUnit, remainder: Long): Long {
        val count = remainder / unit.nanos
        if (count < 1) return remainder
        else {
            append(count, unit)
            return remainder % unit.nanos
        }
    }

    private fun StringBuilder.append(count: Long, unit: DurationUnit) = append("$count ${unit.shortName}")
    private inline fun with(vararg units: DurationUnit, receiver: (DurationUnit) -> Unit) = units.forEach(receiver)
}

