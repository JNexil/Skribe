package su.jfdev.skribe.expect

import com.winterbe.expekt.Flavor.*
import su.jfdev.skribe.calling.*
import java.time.*

fun <R> expect(calling: Calling<R>): ExpectCalling<R> = ExpectCalling(calling, EXPECT)
fun expect(duration: Duration): ExpectDuration = ExpectDuration(duration, EXPECT)

val <R> Calling<R>.should: ExpectCalling<R> get() = ExpectCalling(this, SHOULD)
val Duration.should: ExpectDuration get() = ExpectDuration(this, SHOULD)