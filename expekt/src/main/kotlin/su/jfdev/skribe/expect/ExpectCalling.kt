package su.jfdev.skribe.expect

import com.winterbe.expekt.*
import su.jfdev.skribe.calling.*

class ExpectCalling<R>(subject: Calling<R>?, flavor: Flavor): AbstractExpect<Calling<R>, ExpectCalling<R>>(subject, flavor) {
    val fails by assert(Calling<R>::isFail)
    val successful by negate(Calling<R>::isFail)
    val done by negate(Calling<R>::isFail)

    val throwable by property {
        via(Calling<R>::throwable)
    }

    val result by property {
        via(Calling<R>::result)
    }

    val duration by property {
        via(Calling<R>::duration)
    }

    val cause by property {
        via(Calling<R>::failCause)
    }

    val message by property {
        via(Calling<R>::failMessage)
    }

    fun throwable(throwable: Class<out Throwable> = Throwable::class.java, message: String? = null) = me {
        words += "throwable"
        words += "[$throwable]"
        verifyThrowable(throwable)
        verifyMessage(message)
    }


    fun error(error: Class<out Error> = Error::class.java, message: String? = null) = me {
        words += "error"
        words += "[$error]"
        verifyThrowable(error)
        verifyMessage(message)
    }

    fun assertion(assertion: Class<out AssertionError> = AssertionError::class.java, message: String? = null) = me {
        words += "assertion [] ->"
        words += "[$assertion]"
        verifyThrowable(assertion)
        verifyMessage(message)
    }

    fun exception(exception: Class<out Exception> = Exception::class.java, message: String? = null) = me {
        words += "exception"
        words += "[$exception]"
        verifyThrowable(exception)
        verifyMessage(message)
    }

    private fun verifyThrowable(throwable: Class<out Throwable>) = verifyInstance(throwable, Calling<R>::throwable)

    private fun verifyMessage(message: String?) {
        if (message != null) verify {
            subject!!.failMessage == message
        }
    }

    fun message(message: String) = me {
        words += "message"
        words += message
        verifyMessage(message)
    }

}