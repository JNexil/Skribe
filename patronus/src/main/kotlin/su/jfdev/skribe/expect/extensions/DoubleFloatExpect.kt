package su.jfdev.skribe.expect.extensions

import su.jfdev.skribe.expect.*
import su.jfdev.skribe.expect.dev.*
import java.lang.Math.*

@get:JvmName("floatNaN")
val Expect<Float>.NaN: Expect<Float> get() = backend()
        .assertion("NaN") { real.isNaN() }

@get:JvmName("floatFinite")
val Expect<Float>.finite: Expect<Float> get() = backend()
        .assertion("finite") { real.isFinite() }

@get:JvmName("floatInfinite")
val Expect<Float>.infinite: Expect<Float> get() = backend()
        .assertion("infinite") { real.isInfinite() }

@get:JvmName("doubleNaN")
val Expect<Double>.NaN: Expect<Double> get() = backend()
        .assertion("NaN") { real.isNaN() }

@get:JvmName("doubleFinite")
val Expect<Double>.finite: Expect<Double> get() = backend()
        .assertion("finite") { real.isFinite() }

@get:JvmName("doubleInfinite")
val Expect<Double>.infinite: Expect<Double> get() = backend()
        .assertion("infinite") { real.isInfinite() }

fun Expect<Double>.closeTo(expected: Double, delta: Double): Expect<Double> = backend()
        .append("close to $expected±$delta")
        .verify {
            abs(expected - real) <= delta
        }

fun Expect<Float>.closeTo(expected: Float, delta: Float): Expect<Float> = backend()
        .append("close to $expected±$delta")
        .verify {
            abs(expected - real) <= delta
        }