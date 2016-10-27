package su.jfdv.skribe.adapter

import org.jetbrains.spek.api.dsl.*
import su.jfdev.skribe.adapter.*
import su.jfdev.skribe.stepwise.*
import java.util.*

class SpekSuite private constructor(): SuiteAdapter {
    private var dsl: Dsl? = null

    constructor(dsl: Dsl): this() {
        this.dsl = dsl
    }

    private val tasks = LinkedList<Dsl.() -> Unit>()

    private fun task(action: Dsl.() -> Unit) {
        val dsl = dsl ?: return tasks.addLast(action)
        dsl.action()
    }

    private fun Dsl.init() {
        this@SpekSuite.dsl = this
        for (task in tasks) task(this)
    }

    override fun beforeEach(action: () -> Unit) = task {
        beforeEach(action)
    }

    override fun afterEach(action: () -> Unit) = task {
        afterEach(action)
    }

    override fun case(name: String, test: () -> Unit) = SpekCase().apply {
        task {
            this.test(name, body = test)
        }
    }

    override fun suite(name: String) = SpekSuite().apply {
        task {
            group(name) {
                init()
            }
        }
    }

    override fun suite(name: String, action: SuiteAdapter.() -> Unit) = SpekSuite().apply {
        task {
            group(name) {
                action()
                init()
            }
        }
    }

    inner class SpekCase: CaseAdapter
}

fun <T> Dsl.with(factory: () -> T): Intermediate<T> = SpekSuite(this).with(factory)
fun <T> SubjectDsl<T>.withSubject(): Intermediate<T> = SpekSuite(this).with { subject }