package com.github.jnexil.skribe.adapter

interface Extension {
    class ActionBeforeEach(val action: () -> Unit): Extension
    class ActionAfterEach(val action: () -> Unit): Extension
    object Unshared: Extension
    object Ignored: Extension
}