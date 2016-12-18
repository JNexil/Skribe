package com.github.jnexil.skribe

import com.github.jnexil.skribe.adapter.*
import mu.*
import org.junit.runner.*

class SkribeCase(val name: String, val test: () -> Unit, val suite: SkribeSuite): ExtensibleCase, Describable {
    private val description = Description.createTestDescription(suite.description.className, name)

    init {
        logger.debug { "Created description($description)" }
    }

    override fun getDescription(): Description = description

    private companion object: KLogging()
}