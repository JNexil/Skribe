package su.jfdev.skribe.expect

import com.winterbe.expekt.*
import com.winterbe.expekt.Flavor.*
import su.jfdev.skribe.expect.UnsafeExpect.child

class ExpectThrowable<T: Throwable>(subject: T?, flavor: Flavor): AbstractExpect<T, ExpectThrowable<T>>(subject, flavor) {
    val stackTrace by property {
        child(it, extractor = { stackTrace.toList() }) {
            ExpectCollection(it, EXPECT)
        }
    }

    val cause by property {
        via(Throwable::cause)
    }

    val message by property {
        via(Throwable::message)
    }
}