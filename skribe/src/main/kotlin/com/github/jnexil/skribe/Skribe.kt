package com.github.jnexil.skribe

import com.github.jnexil.skribe.adapter.*
import org.junit.runner.*


@RunWith(SkribeRunner::class)
abstract class Skribe(description: String) {
    val root = SkribeSuite(description)
    fun describe(description: String, vararg extensions: Extension) = root
            .suite(description)
            .apply { this.extensions += extensions }
}