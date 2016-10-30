package su.jfdev.skribe.calling

import kotlin.system.*

class Calling<R>(action: () -> R) {
    val duration: Duration
    var throwable: Throwable? = null; private set
    var result: R? = null; private set

    init {
        val time = measureNanoTime {
            try {
                result = action()
            } catch (e: Throwable) {
                throwable = e
            }
        }
        duration = Duration.nanos(time)
    }

    val failCause: Throwable? get() = throwable!!.cause
    val failMessage: String? get() = throwable!!.message
    val isFail: Boolean get() = throwable != null
    val isDone: Boolean get() = throwable == null

    override fun equals(other: Any?): Boolean = (this === other) ||
                                                (other is Calling<*>) &&
                                                (duration == other.duration) &&
                                                (throwable == other.throwable) &&
                                                (result == other.result)

    override fun hashCode(): Int {
        var result1 = duration.hashCode()
        result1 = 31 * result1 + (throwable?.hashCode() ?: 0)
        result1 = 31 * result1 + (result?.hashCode() ?: 0)
        return result1
    }

    override fun toString(): String = when {
        isFail -> "[Fail since $duration -> $throwable]"
        else   -> "[Since $duration -> $result]"
    }
}