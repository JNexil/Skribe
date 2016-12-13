package com.github.jnexil.skribe.spek

import com.github.jnexil.skribe.stepwise.*
import org.jetbrains.spek.api.dsl.*

fun <T> Dsl.with(factory: () -> T): Intermediate<T> = SpekSuite(this).with(factory)
fun <T> SubjectDsl<T>.withSubject(): Intermediate<T> = SpekSuite(this).with { subject }