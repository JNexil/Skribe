package su.jfdev.skribe.expect

import com.winterbe.expekt.*
import su.jfdev.skribe.calling.*

class ExpectCalling<R>(subject: Calling<R>?, flavor: Flavor): AbstractExpect<Calling<R>, ExpectCalling<R>>(subject, flavor) {
    val fails by assert(Calling<R>::isFail)
    val successful by negate(Calling<R>::isFail)
    val done by negate(Calling<R>::isFail)

    val exception by property {
        get(Calling<R>::exception)
    }

    val result by property {
        get(Calling<R>::result)
    }

    val duration by property {
        get(Calling<R>::duration)
    }

    fun exception(exception: Class<out Exception>) = me {
        words += "exception"
        words += exception.toString()
        verifyInstance(exception, Calling<R>::exception)
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