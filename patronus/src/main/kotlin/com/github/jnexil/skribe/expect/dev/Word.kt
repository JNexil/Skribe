package com.github.jnexil.skribe.expect.dev

import com.github.jnexil.skribe.expect.*
import kotlin.reflect.*

object Word {
    operator fun <T: Any> getValue(thisRef: Expect<T>, property: KProperty<*>): Expect<T> = thisRef.backend().append(property.name)
}