package su.jfdev.skribe.expect

import com.winterbe.expekt.*
import com.winterbe.expekt.Flavor.*
import su.jfdev.skribe.expect.UnsafeExpect.child
import java.time.*
import kotlin.reflect.*

@JvmName("anyProperty")
fun <T, R> ExpectAny<out T>.via(property: KProperty1<T, R?>) = child(property) { ExpectAny(it, EXPECT) }

@JvmName("comparableProperty")
fun <T, R: Comparable<R>> ExpectAny<out T>.via(property: KProperty1<T, R?>) = child(property) { ExpectComparable(it, EXPECT) }

@JvmName("durationProperty")
fun <T> ExpectAny<out T>.via(property: KProperty1<T, Duration?>) = child(property) { ExpectDuration(it, EXPECT) }

@JvmName("booleanProperty")
fun <T> ExpectAny<out T>.via(property: KProperty1<T, Boolean?>) = child(property) { ExpectBoolean(it, EXPECT) }

@JvmName("doubleProperty")
fun <T> ExpectAny<out T>.via(property: KProperty1<T, Double?>) = child(property) { ExpectDouble(it, EXPECT) }

@JvmName("stringProperty")
fun <T> ExpectAny<out T>.via(property: KProperty1<T, String?>) = child(property) { ExpectString(it, EXPECT) }

@JvmName("collectionProperty")
fun <T, R> ExpectAny<out T>.via(property: KProperty1<T, Collection<R>?>) = child(property) { ExpectCollection(it, EXPECT) }

@JvmName("mapProperty")
fun <T, K, V> ExpectAny<out T>.via(property: KProperty1<T, Map<K, V>?>) = child(property) { ExpectMap(it, EXPECT) }

@JvmName("throwableProperty")
fun <T, R: Throwable> ExpectAny<out T>.via(property: KProperty1<T, R?>) = child(property) { ExpectThrowable(it, EXPECT) }