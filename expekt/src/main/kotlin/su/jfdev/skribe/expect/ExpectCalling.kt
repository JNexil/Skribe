package su.jfdev.skribe.expect

import com.winterbe.expekt.*
import su.jfdev.skribe.calling.*

class ExpectCalling<R>(subject: Calling<R>?, flavor: Flavor): AbstractExpect<Calling<R>, ExpectCalling<R>>(subject, flavor) {
    val fails by assert(Calling<R>::isFail)
    val successful by negate(Calling<R>::isFail)
    val done by negate(Calling<R>::isFail)

    val throwable by property {
        get(Calling<R>::throwable)
    }

    val result by property {
        get(Calling<R>::result)
    }

    val duration by property {
        get(Calling<R>::duration)
    }

    fun throwable(throwable: Class<out Throwable>) = me {
        words += "throwable"
        words += throwable.toString()
        verifyInstance(throwable, Calling<R>::throwable)
    }

    fun error(error: Class<out Error>) = me {
        words += "error"
        words += error.toString()
        verifyInstance(error, Calling<R>::throwable)
    }

    fun exception(exception: Class<out Exception>) = me {
        words += "exception"
        words += exception.toString()
        verifyInstance(exception, Calling<R>::throwable)
    }

    fun cause(exception: Class<out Exception>) = me {
        words += "cause"
        words += exception.toString()
        verifyInstance(exception, Calling<R>::failCause)
    }

    fun message(message: String) = me {
        words += "message"
        words += message
        verify {
            subject!!.failMessage == message
        }
    }
}