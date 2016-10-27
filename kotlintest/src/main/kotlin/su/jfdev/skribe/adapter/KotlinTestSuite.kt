package su.jfdev.skribe.adapter

import io.kotlintest.*
import java.util.*

class KotlinTestSuite private constructor(private val suite: TestSuite,
                                          beforeEach: List<() -> Unit>,
                                          afterEach: List<() -> Unit>): SuiteAdapter {
    constructor(suite: TestSuite): this(suite,
                                        beforeEach = emptyList(),
                                        afterEach = emptyList())

    private val beforeEach = LinkedList<() -> Unit>(beforeEach)
    private val afterEach = LinkedList<() -> Unit>(afterEach)

    override fun beforeEach(action: () -> Unit) {
        beforeEach += action
    }

    override fun afterEach(action: () -> Unit) {
        afterEach += action
    }

    override fun suite(name: String, action: SuiteAdapter.() -> Unit): SuiteAdapter = suite(name).apply(action)

    override fun suite(name: String): SuiteAdapter {
        val child = TestSuite.empty(name)
        suite.nestedSuites += child
        return KotlinTestSuite(child, beforeEach, afterEach)
    }

    override fun case(name: String, test: () -> Unit): CaseAdapter = KotlinTestCase(case = TestCase(suite, name, config = TestConfig(), test = {
        beforeEach()
        test()
        afterEach()
    }))

    private operator fun Iterable<() -> Unit>.invoke() = forEach { it() }
}