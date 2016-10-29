package su.jfdev.skribe.calling

import java.io.*
import java.lang.Math.*
import java.util.concurrent.*

class Duration(val nanos: Long): Comparable<Duration>, Serializable {
    val micros = nanos / 1000
    val millis = micros / 1000
    val seconds = micros / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val weeks = days / 7

    infix fun like(target: TimeUnit): Long = when (target) {
        TimeUnit.NANOSECONDS  -> nanos
        TimeUnit.MICROSECONDS -> micros
        TimeUnit.MILLISECONDS -> millis
        TimeUnit.SECONDS      -> seconds
        TimeUnit.MINUTES      -> minutes
        TimeUnit.HOURS        -> hours
        TimeUnit.DAYS         -> days
    }

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

    @Suppress("NOTHING_TO_INLINE")
    companion object {
        private const val MICROS = 1000L
        private const val MILLIS = MICROS * 1000L
        private const val SECONDS = MILLIS * 1000L
        private const val MINUTES = SECONDS * 60L
        private const val HOURS = MINUTES * 60L
        private const val DAYS = HOURS * 24L
        private const val WEEKS = DAYS * 7L

        private const val MAX_MICROS = Long.MAX_VALUE / MICROS
        private const val MAX_MILLIS = Long.MAX_VALUE / MILLIS
        private const val MAX_SECONDS = Long.MAX_VALUE / SECONDS
        private const val MAX_MINUTES = Long.MAX_VALUE / MINUTES
        private const val MAX_HOURS = Long.MAX_VALUE / HOURS
        private const val MAX_DAYS = Long.MAX_VALUE / DAYS
        private const val MAX_WEEKS = Long.MAX_VALUE / WEEKS


        operator fun get(count: Long, target: TimeUnit) = target.convert(count, TimeUnit.NANOSECONDS)

        fun nanos(nanos: Int) = Duration(nanos.toLong())
        fun micros(micros: Int) = exact(micros, MICROS, MAX_MICROS)
        fun millis(millis: Int) = exact(millis, MILLIS, MAX_MILLIS)
        fun seconds(seconds: Int) = exact(seconds, SECONDS, MAX_SECONDS)
        fun minutes(minutes: Int) = exact(minutes, MINUTES, MAX_MINUTES)
        fun hours(hours: Int) = exact(hours, HOURS, MAX_HOURS)
        fun days(days: Int) = exact(days, DAYS, MAX_DAYS)
        fun weeks(weeks: Int) = exact(weeks, WEEKS, MAX_WEEKS)

        fun nanos(nanos: Long) = Duration(nanos.toLong())
        fun micros(micros: Long) = exact(micros, MICROS, MAX_MICROS)
        fun millis(millis: Long) = exact(millis, MILLIS, MAX_MILLIS)
        fun seconds(seconds: Long) = exact(seconds, SECONDS, MAX_SECONDS)
        fun minutes(minutes: Long) = exact(minutes, MINUTES, MAX_MINUTES)
        fun hours(hours: Long) = exact(hours, HOURS, MAX_HOURS)
        fun days(days: Long) = exact(days, DAYS, MAX_DAYS)
        fun weeks(weeks: Long) = exact(weeks, WEEKS, MAX_WEEKS)

        fun nanos(nanos: Double) = Duration(nanos.toLong())
        fun micros(micros: Double) = exact(micros, MICROS, MAX_MICROS)
        fun millis(millis: Double) = exact(millis, MILLIS, MAX_MILLIS)
        fun seconds(seconds: Double) = exact(seconds, SECONDS, MAX_SECONDS)
        fun minutes(minutes: Double) = exact(minutes, MINUTES, MAX_MINUTES)
        fun hours(hours: Double) = exact(hours, HOURS, MAX_HOURS)
        fun days(days: Double) = exact(days, DAYS, MAX_DAYS)
        fun weeks(weeks: Double) = exact(weeks, WEEKS, MAX_WEEKS)

        fun nanos(nanos: Float) = Duration(nanos.toLong())
        fun micros(micros: Float) = exact(micros, MICROS, MAX_MICROS)
        fun millis(millis: Float) = exact(millis, MILLIS, MAX_MILLIS)
        fun seconds(seconds: Float) = exact(seconds, SECONDS, MAX_SECONDS)
        fun minutes(minutes: Float) = exact(minutes, MINUTES, MAX_MINUTES)
        fun hours(hours: Float) = exact(hours, HOURS, MAX_HOURS)
        fun days(days: Float) = exact(days, DAYS, MAX_DAYS)
        fun weeks(weeks: Float) = exact(weeks, WEEKS, MAX_WEEKS)

        private inline fun exact(count: Int, unit: Long, max: Long): Duration = when {
            abs(count) > max -> overflow(count, unit)
            else             -> Duration((count * unit).toLong())
        }

        private inline fun exact(count: Long, unit: Long, max: Long): Duration = when {
            abs(count) > max -> overflow(count, unit)
            else             -> Duration((count * unit).toLong())
        }

        private inline fun exact(count: Double, unit: Long, max: Long): Duration = when {
            abs(count) > max -> overflow(count, unit)
            else             -> Duration((count * unit).toLong())
        }

        private inline fun exact(count: Float, unit: Long, max: Long) = when {
            abs(count) > max -> overflow(count, unit)
            else             -> Duration((count * unit).toLong())
        }

        private fun overflow(count: Number, unit: Long): Nothing = throw ArithmeticException("Overflow when $count * $unit")
    }
}