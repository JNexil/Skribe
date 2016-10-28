package su.jfdev.skribe.expect

import com.winterbe.expekt.*
import java.lang.reflect.*
import java.util.*
import kotlin.reflect.*

internal object UnsafeExpect {
    internal fun <T, S, C: ExpectAny<S>> ExpectAny<T>.child(function: KFunction1<T, S?>, calling: (S?) -> C): C = child(function.name, function, calling)
    internal fun <T, S, C: ExpectAny<S>> ExpectAny<T>.child(property: KProperty1<T, S?>, calling: (S?) -> C): C = child(property.name, property, calling)
    internal fun <T, S, C: ExpectAny<S>> ExpectAny<T>.child(text: String, extractor: T.() -> S?, calling: (S?) -> C): C {
        val child = findSubject().extractor()
        return calling(child).apply {
            transferWords(this@child, "$text $child")
        }
    }

    private fun ExpectAny<*>.transferWords(from: ExpectAny<*>, text: String) {
        val childWords = findWords()
        childWords.clear()
        childWords += from.findWords()
        childWords += text
    }

    private fun <T> ExpectAny<T>.findSubject(): T = ExpectAny::class.java.getMethod("getSubject").extract(this)

    internal fun ExpectAny<*>.findWords(): MutableList<String> = ExpectAny::class.java.methods
                                                                         .filter { it.returnType == ArrayList::class.java && it.parameterCount == 0 }
                                                                         .map { it.extract<MutableList<String>>(this) }
                                                                         .singleOrNull() ?: error("Words not found")

    @Suppress("UNCHECKED_CAST")
    private fun <T> Method.extract(instance: Any? = null, vararg arguments: Any?): T {
        val old = isAccessible
        isAccessible = true
        return try {
            invoke(instance, *arguments) as T
        } finally {
            isAccessible = old
        }
    }
}