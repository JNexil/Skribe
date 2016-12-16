package com.github.jnexil.skribe.util

import com.github.jnexil.skribe.adapter.*
import com.github.jnexil.skribe.testable.*

@Undescribed
val SuiteAdapter.stepwise: Stepwise<Unit> get() = intermediate.stepwise
val SuiteAdapter.intermediate: Intermediate<Unit> get() = intermediate(UnitFactory)

@Undescribed
fun <S> SuiteAdapter.intermediate(factory: () -> S): Intermediate<S> = AdaptIntermediate(suite = this, factory = factory)

@Undescribed
inline fun SuiteAdapter.stepwise(using: Stepwise<Unit>.() -> Unit): Stepwise<Unit> = stepwise.apply(using)