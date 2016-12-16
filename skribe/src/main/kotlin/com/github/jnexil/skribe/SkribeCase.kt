package com.github.jnexil.skribe

import com.github.jnexil.skribe.adapter.*
import org.junit.runner.*

class SkribeCase(val name: String, val test: () -> Unit, val suite: SkribeSuite): ExtensibleCase, Describable {
    override fun getDescription(): Description = Description.createTestDescription(suite.description.className, name)
}