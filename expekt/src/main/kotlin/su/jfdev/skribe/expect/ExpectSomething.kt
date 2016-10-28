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

@JvmName("booleanProperty")
fun <T> ExpectAny<T>.get(property: KProperty1<T, Boolean?>) = UnsafeExpect.run {
    child(property) { ExpectBoolean(it, EXPECT) }
}

@JvmName("doubleProperty")
fun <T> ExpectAny<T>.get(property: KProperty1<T, Double?>) = UnsafeExpect.run {
    child(property) { ExpectDouble(it, EXPECT) }
}

@JvmName("stringProperty")
fun <T> ExpectAny<T>.get(property: KProperty1<T, String?>) = UnsafeExpect.run {
    child(property) { ExpectString(it, EXPECT) }
}

@JvmName("collectionProperty")
fun <T, R> ExpectAny<T>.get(property: KProperty1<T, Collection<R>?>) = UnsafeExpect.run {
    child(property) { ExpectCollection(it, EXPECT) }
}

@JvmName("mapProperty")
fun <T, K, V> ExpectAny<T>.get(property: KProperty1<T, Map<K, V>?>) = UnsafeExpect.run {
    child(property) { ExpectMap(it, EXPECT) }
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

@JvmName("booleanFunction")
fun <T> ExpectAny<T>.get(property: KFunction1<T, Boolean?>) = UnsafeExpect.run {
    child(property) { ExpectBoolean(it, EXPECT) }
}

@JvmName("doubleFunction")
fun <T> ExpectAny<T>.get(property: KFunction1<T, Double?>) = UnsafeExpect.run {
    child(property) { ExpectDouble(it, EXPECT) }
}

@JvmName("stringFunction")
fun <T> ExpectAny<T>.get(property: KFunction1<T, String?>) = UnsafeExpect.run {
    child(property) { ExpectString(it, EXPECT) }
}

@JvmName("collectionFunction")
fun <T, R> ExpectAny<T>.get(property: KFunction1<T, Collection<R>?>) = UnsafeExpect.run {
    child(property) { ExpectCollection(it, EXPECT) }
}

@JvmName("mapFunction")
fun <T, K, V> ExpectAny<T>.get(property: KFunction1<T, Map<K, V>?>) = UnsafeExpect.run {
    child(property) { ExpectMap(it, EXPECT) }
}

