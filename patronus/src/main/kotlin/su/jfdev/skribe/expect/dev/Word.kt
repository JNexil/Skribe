package su.jfdev.skribe.expect.dev

import su.jfdev.skribe.expect.*
import kotlin.reflect.*

object Word {
    operator fun <T: Any> getValue(thisRef: Expect<T>, property: KProperty<*>): Expect<T> = thisRef.backend().append(property.name)
}