package com.github.jnexil.skribe.spek

import com.github.jnexil.skribe.adapter.*
import com.github.jnexil.skribe.testable.*
import com.github.jnexil.skribe.util.*
import org.jetbrains.spek.api.dsl.*
import java.util.*

internal class SpekSuite private constructor(): SuiteAdapter {
    private var dsl: Dsl? = null

    constructor(dsl: Dsl): this() {
        this.dsl = dsl
    }

    private val tasks = LinkedList<Dsl.() -> Unit>()

    private fun task(action: (Dsl) -> Unit) {
        val dsl = dsl ?: return tasks.addLast(action)
        action(dsl)
    }

    private fun init(dsl: Dsl) {
        this.dsl = dsl
        for (task in tasks) task(dsl)
    }

    override fun beforeEach(action: () -> Unit) = task {
        it.beforeEach(action)
    }

    override fun afterEach(action: () -> Unit) = task {
        it.afterEach(action)
    }

    override fun case(name: String, test: () -> Unit): SpekCase = SpekCase() finally { case ->
        task { it.test(name, body = test) }
    }

    override fun suite(name: String): SpekSuite = SpekSuite() finally { child ->
        task {
            it.group(name) {
                child.init(this)
            }
        }
    }

    override fun suite(name: String, action: SuiteAdapter.() -> Unit): SpekSuite = SpekSuite() finally { child ->
        task {
            it.group(name) {
                action()
                child.init(this)
            }
        }
    }

    private inline infix fun <R> R.finally(action: (R) -> Unit): R = apply(action)
    inner class SpekCase: CaseAdapter
}

val Dsl.adapter: SuiteAdapter get() = SpekSuite(this)
val Dsl.skribe: Intermediate<Unit> get() = adapter.skribe
val <T> SubjectDsl<T>.skribe: Intermediate<T> get() = adapter.skribe { subject }