package com.github.jnexil.skribe

import com.github.jnexil.skribe.adapter.*
import com.github.jnexil.skribe.adapter.Extension.*
import mu.*
import org.junit.runner.*
import org.junit.runner.Description.*
import java.util.*

class SkribeSuite(description: String, override var extensions: Sequence<Extension>): ExtensibleSuite, Describable {
    constructor(description: String, vararg extensions: Extension): this(description, extensions.asSequence())

    private val description: Description = createSuiteDescription(description)

    init {
        logger.debug { "Created description($description)" }
    }
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
        logger.debug { "Created new suite '$name' at $this" }
        SkribeSuite(name, extensions)
    }

    override fun case(name: String, test: () -> Unit): SkribeCase = child {
        logger.debug { "Created new case '$name' at $this" }
        SkribeCase(name, test, this)
    }

    private inline fun <D: Describable> child(describable: () -> D): D {
        val element = describable()
        elements.add(element)
        description.addChild(element.description)
        return element
    }

    override fun getDescription(): Description = description

    override fun toString(): String = "SkribeSuite(description=$description)"

    private companion object: KLogging()
}