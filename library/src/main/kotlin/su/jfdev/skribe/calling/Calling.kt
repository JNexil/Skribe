package su.jfdev.skribe.calling

import java.time.*
import kotlin.system.*

class Calling<R>(action: () -> R) {
    val duration: Duration
    var exception: Exception? = null; private set
    var result: R? = null; private set

    init {
        val time = measureNanoTime {
            try {
                result = action()
            } catch (e: Exception) {
                exception = e
            }
        }
        duration = Duration.ofNanos(time)
    }

    val failCause: Throwable? get() = exception!!.cause
    val failMessage: String? get() = exception!!.message
    val isFail: Boolean get() = exception == null

    override fun equals(other: Any?): Boolean = (this === other) ||
                                                (other is Calling<*>) &&
                                                (duration == other.duration) &&
                                                (exception == other.exception) &&
                                                (result == other.result)

    override fun hashCode(): Int {
        var result1 = duration.hashCode()
        result1 = 31 * result1 + (exception?.hashCode() ?: 0)
        result1 = 31 * result1 + (result?.hashCode() ?: 0)
        return result1
    }

    override fun toString(): String = when {
        isFail -> "[Fail since $duration -> $exception]"
        else   -> "[Since $duration -> $result]"
    }
}