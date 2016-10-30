package su.jfdev.skribe.expect.dev

class InterruptedExpectError private constructor(message: String, cause: Throwable?): AssertionError(message, cause) {
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

        fun fail(message: String, cause: Throwable? = null): Nothing = throw when (cause) {
            is InterruptedExpectError -> cause + message
            null                      -> proxyError(message, null)
            else                      -> proxyError("$message BUT", cause)
        }

        private fun proxyError(butMessage: String, cause: Throwable?) = when {
            shortTrace -> InterruptedExpectError(butMessage, cause)
            else       -> AssertionError(butMessage, cause)
        }
    }
}