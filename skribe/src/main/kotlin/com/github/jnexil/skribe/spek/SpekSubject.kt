package com.github.jnexil.skribe.spek

import org.jetbrains.spek.api.*
import org.jetbrains.spek.api.dsl.*
import org.jetbrains.spek.api.memoized.*
import kotlin.reflect.*

fun <T, S: SubjectSpek<T>> Dsl.hereBehavesLike(type: Class<S>,
                                               before: SubjectDsl<T>.() -> Unit = {},
                                               after: SubjectDsl<T>.() -> Unit = {}) {
    val spec = type.spec
    describeThrough<T> {
        before()
        spec()
        after()
    }
}

inline fun <T, reified S: SubjectSpek<T>> Dsl.hereBehavesLike(type: KClass<S> = S::class) {
    hereBehavesLike(type.java)
}

fun <T, S: SubjectSpek<T>> Dsl.hereBehavesLike(type: Class<S>) {
    describeThrough(type.spec)
}

private val <S: SubjectSpek<T>, T> Class<S>.spec: SubjectDsl<T>.() -> Unit get() = newInstance().spec

inline fun <T> Dsl.describeThrough(noinline factory: () -> T, action: SubjectDsl<T>.() -> Unit) {
    describeThrough<T> {
        subject(factory = factory)
        action()
    }
}

inline fun <T> Dsl.describeThrough(value: T, action: SubjectDsl<T>.() -> Unit) {
    describeThrough<T> {
        subject { value }
        action()
    }
}

inline fun <T> Dsl.describeThrough(action: SubjectDsl<T>.() -> Unit) {
    describeThrough<T>().action()
}

fun <T> Dsl.describeThrough(): SubjectDsl<T> = SyntheticDsl(this)

private class SyntheticDsl<T>(private val dsl: Dsl): SubjectDsl<T>, Dsl by dsl {
    private lateinit var provider: TestSubject

    override val subject: T get() = provider.value

    override fun subject(mode: CachingMode, factory: () -> T): Subject<T> = TestSubject(mode, factory).apply {
        provider = this
    }

    override fun test(description: String, pending: Pending, body: () -> Unit) = dsl.test(description, pending) {
        provider.reset()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T, K: SubjectSpek<T>> includeSubjectSpec(spec: KClass<K>) = hereBehavesLike(spec.java) {
        subject { this@SyntheticDsl.subject as T }
    }

    private inner class TestSubject(private val mode: CachingMode, private val factory: () -> T): Subject<T> {
        private var provider = lazy(factory)

        internal fun reset() {
            this.provider = lazy(factory)
        }

        val value: T get() = provider.value
        override fun getValue(ref: Any?, property: KProperty<*>): T = provider.value
    }
}