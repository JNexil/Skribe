package su.jfdev.skribe.expect

import com.winterbe.expekt.*
import com.winterbe.expekt.Flavor.*
import su.jfdev.skribe.expect.UnsafeExpect.cast
import java.time.*

@JvmName("anyLikely")
fun <T, R> ExpectAny<T>.like(property: Class<R?>) = cast(property) { ExpectAny(it, EXPECT) }

@JvmName("comparableLikely")
fun <T, R: Comparable<R>> ExpectAny<T>.like(property: Class<R?>) = cast(property) { ExpectComparable(it, EXPECT) }

@JvmName("durationLikely")
fun <T> ExpectAny<T>.like(property: Class<Duration?>) = cast(property) { ExpectDuration(it, EXPECT) }

@JvmName("booleanLikely")
fun <T> ExpectAny<T>.like(property: Class<Boolean?>) = cast(property) { ExpectBoolean(it, EXPECT) }

@JvmName("doubleLikely")
fun <T> ExpectAny<T>.like(property: Class<Double?>) = cast(property) { ExpectDouble(it, EXPECT) }

@JvmName("stringLikely")
fun <T> ExpectAny<T>.like(property: Class<String?>) = cast(property) { ExpectString(it, EXPECT) }

@JvmName("collectionLikely")
fun <T, R> ExpectAny<T>.like(property: Class<Collection<R>?>) = cast(property) { ExpectCollection(it, EXPECT) }

@JvmName("mapLikely")
fun <T, K, V> ExpectAny<T>.like(property: Class<Map<K, V>?>) = cast(property) { ExpectMap(it, EXPECT) }

fun <T, R> ExpectAny<T>.`as`(type: Class<R>): ExpectAny<R> = cast(type) { ExpectAny(it, EXPECT) }