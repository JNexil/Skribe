package su.jfdv.skribe.stepwise

import org.jetbrains.spek.api.dsl.*
import su.jfdev.skribe.stepwise.*
import su.jfdv.skribe.adapter.*

fun <T> Dsl.with(factory: () -> T): Intermediate<T> = SpekSuite(this).with(factory)
fun <T> SubjectDsl<T>.withSubject(): Intermediate<T> = SpekSuite(this).with { subject }