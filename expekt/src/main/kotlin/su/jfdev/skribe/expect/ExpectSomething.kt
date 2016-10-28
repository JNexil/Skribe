package su.jfdev.skribe.expect

import com.winterbe.expekt.*
import com.winterbe.expekt.Flavor.*
import java.time.*
import kotlin.reflect.*

@JvmName("anyProperty")
fun <T, R> ExpectAny<T>.get(property: KProperty1<T, R?>) = UnsafeExpect.run {
    child(property) { ExpectAny(it, EXPECT) }
}

@JvmName("comparableProperty")
fun <T, R: Comparable<R>> ExpectAny<T>.get(property: KProperty1<T, R?>) = UnsafeExpect.run {
    child(property) { ExpectComparable(it, EXPECT) }
}

@JvmName("durationProperty")
fun <T> ExpectAny<T>.get(property: KProperty1<T, Duration?>) = UnsafeExpect.run {
    child(property) { ExpectDuration(it, EXPECT) }
}

@JvmName("anyFunction")
fun <T, R> ExpectAny<T>.get(property: KFunction1<T, R?>) = UnsafeExpect.run {
    child(property) { ExpectAny(it, EXPECT) }
}

@JvmName("comparableFunction")
fun <T, R: Comparable<R>> ExpectAny<T>.get(property: KFunction1<T, R?>) = UnsafeExpect.run {
    child(property) { ExpectComparable(it, EXPECT) }
}

@JvmName("durationFunction")
fun <T> ExpectAny<T>.get(property: KFunction1<T, Duration?>) = UnsafeExpect.run {
    child(property) { ExpectDuration(it, EXPECT) }
}

