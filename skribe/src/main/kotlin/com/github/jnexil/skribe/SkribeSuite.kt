package com.github.jnexil.skribe

import com.github.jnexil.skribe.adapter.*
import com.github.jnexil.skribe.adapter.Extension.*
import org.junit.runner.*
import org.junit.runner.Description.*
import java.util.*

class SkribeSuite(description: String, override var extensions: Sequence<Extension>): ExtensibleSuite, Describable {
    constructor(description: String, vararg extensions: Extension): this(description, extensions.asSequence())

    private val description: Description = createSuiteDescription(description)
    internal val elements: MutableSet<Describable> = HashSet()

    override fun beforeEach(action: () -> Unit) {
        extensions += ActionBeforeEach(action)
    }

    override fun afterEach(action: () -> Unit) {
        extensions += ActionAfterEach(action)
    }

    override fun suite(name: String, action: SuiteAdapter.() -> Unit): SkribeSuite {
        return suite(name).apply(action)
    }

    override fun suite(name: String): SkribeSuite = child {
        SkribeSuite(name, extensions)
    }

    override fun case(name: String, test: () -> Unit): SkribeCase = child {
        SkribeCase(name, test, this)
    }

    private inline fun <D: Describable> child(describable: () -> D): D {
        val element = describable()
        elements.add(element)
        description.addChild(element.description)
        return element
    }

    override fun getDescription(): Description = description
}