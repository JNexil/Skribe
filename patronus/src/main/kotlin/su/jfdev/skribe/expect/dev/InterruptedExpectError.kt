package su.jfdev.skribe.expect.dev

import su.jfdev.skribe.expect.Expect.*

class InterruptedExpectError private constructor(message: String, cause: Throwable?): AssertionError(message, cause) {
    init {
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
        if (shortTrace && cause != null) {
            (this as java.lang.Throwable).stackTrace = emptyArray()
        }
    }

    override var message: String = message; private set
    private operator fun plus(upper: Any) = apply {
        message = "$upper\nWHERE $message"
    }

    companion object {
        private val shortTrace: Boolean
        const val ENABLED_SHORT_TRACE = "true"
        const val SHORT_TRACE = "expect.shortTrace"

        init {
            val shortTrace = System.getProperty(SHORT_TRACE, ENABLED_SHORT_TRACE)
            this.shortTrace = ENABLED_SHORT_TRACE.equals(shortTrace, ignoreCase = true)
        }

        fun inspectionFail(backend: Backend<*>, cause: Throwable? = null): Nothing = throw when (cause) {
            is InterruptedExpectError -> cause + backend.line
            null                      -> fail(backend.line, null)
            else                      -> fail("$backend BUT:", cause)
        }

        fun fail(message: String, cause: Throwable? = null): Nothing = throw when {
            shortTrace -> InterruptedExpectError(message, cause)
            else       -> AssertionError(message, cause)
        }
    }
}