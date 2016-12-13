package com.github.jnexil.skribe.util

import com.github.jnexil.skribe.adapter.*
import com.github.jnexil.skribe.calling.*
import com.github.jnexil.skribe.testable.*

@Undescribed
val SuiteAdapter.stepwise: Stepwise<Unit> get() = skribe.stepwise
val SuiteAdapter.skribe: Intermediate<Unit> get() = skribe(UnitFactory)

@Undescribed
fun <S> SuiteAdapter.skribeCalling(factory: () -> S): Intermediate<Calling<S>> = skribe {
    Calling(factory)
}

@Undescribed
fun <S> SuiteAdapter.skribe(factory: () -> S): Intermediate<S> = AdaptIntermediate(suite = this, factory = factory)

@Undescribed
inline fun SuiteAdapter.stepwise(using: Stepwise<Unit>.() -> Unit): Stepwise<Unit> = stepwise.apply(using)