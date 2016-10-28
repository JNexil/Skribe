package su.jfdev.skribe.expect

import com.winterbe.expekt.*
import com.winterbe.expekt.Flavor.*
import su.jfdev.skribe.expect.UnsafeExpect.child
import java.time.*
import kotlin.reflect.*

@JvmName("anyFunction")
fun <T, R> ExpectAny<out T>.via(property: KFunction1<T, R?>) = child(property) { ExpectAny(it, EXPECT) }

@JvmName("comparableFunction")
fun <T, R: Comparable<R>> ExpectAny<out T>.via(property: KFunction1<T, R?>) = child(property) { ExpectComparable(it, EXPECT) }

@JvmName("durationFunction")
fun <T> ExpectAny<out T>.via(property: KFunction1<T, Duration?>) = child(property) { ExpectDuration(it, EXPECT) }

@JvmName("booleanFunction")
fun <T> ExpectAny<out T>.via(property: KFunction1<T, Boolean?>) = child(property) { ExpectBoolean(it, EXPECT) }

@JvmName("doubleFunction")
fun <T> ExpectAny<out T>.via(property: KFunction1<T, Double?>) = child(property) { ExpectDouble(it, EXPECT) }

@JvmName("stringFunction")
fun <T> ExpectAny<out T>.via(property: KFunction1<T, String?>) = child(property) { ExpectString(it, EXPECT) }

@JvmName("collectionFunction")
fun <T, R> ExpectAny<out T>.via(property: KFunction1<T, Collection<R>?>) = child(property) { ExpectCollection(it, EXPECT) }

@JvmName("mapFunction")
fun <T, K, V> ExpectAny<out T>.via(property: KFunction1<T, Map<K, V>?>) = child(property) { ExpectMap(it, EXPECT) }

@JvmName("throwableFunction")
fun <T, R: Throwable> ExpectAny<out T>.via(property: KFunction1<T, R?>) = child(property) { ExpectThrowable(it, EXPECT) }