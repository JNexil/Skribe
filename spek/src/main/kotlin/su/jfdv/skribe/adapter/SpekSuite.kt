package su.jfdv.skribe.adapter

import org.jetbrains.spek.api.dsl.*
import su.jfdev.skribe.adapter.*
import java.util.*

class SpekSuite private constructor(): SuiteAdapter {
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

fun Dsl.adapter(): SuiteAdapter = SpekSuite(this)