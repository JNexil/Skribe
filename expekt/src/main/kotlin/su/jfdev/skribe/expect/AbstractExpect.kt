package su.jfdev.skribe.expect

import com.winterbe.expekt.*
import su.jfdev.skribe.expect.UnsafeExpect.child
import su.jfdev.skribe.expect.UnsafeExpect.findWords
import java.time.*
import kotlin.properties.*
import kotlin.reflect.*

abstract class AbstractExpect<T, ME: AbstractExpect<T, ME>>(subject: T?, flavor: Flavor): ExpectMe<T, ME>(subject, flavor) {
    protected fun <S> expectAny(text: String, extractor: T.() -> S) = child(text, extractor) { ExpectAny(it, flavor) }
    protected fun <S: Comparable<S>> expectComparable(text: String, extractor: T.() -> S) = child(text, extractor) { ExpectComparable(it, flavor) }
    protected fun expectDuration(text: String, extractor: T.() -> Duration) = child(text, extractor) { ExpectDuration(it, flavor) }

    protected fun verifyInstance(expected: Class<*>, extractor: T.() -> Any?) = verify {
        expected.isInstance(subject!!.extractor()!!)
    }

    protected fun assert(verify: T.() -> Boolean) = meProperty {
        words += it
        verify {
            subject!!.verify()
        }
    }

    protected fun negate(verify: T.() -> Boolean) = meProperty {
        words += it
        verify {
            !subject!!.verify()
        }
    }

    protected val words: MutableList<String> = findWords()

    protected inline fun <R> property(crossinline body: (String) -> R) = object: ReadOnlyProperty<Any?, R> {
        override fun getValue(thisRef: Any?, property: KProperty<*>): R = body(property.name)
    }

    protected inline fun meProperty(crossinline body: (String) -> Unit) = property {
        me {
            body(it)
        }
    }
}