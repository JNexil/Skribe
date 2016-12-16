package com.github.jnexil.skribe.adapter

interface ExtensibleSuite: SuiteAdapter {
    var extensions: Sequence<Extension>
    override fun suite(name: String, action: SuiteAdapter.() -> Unit): ExtensibleSuite
    override fun suite(name: String): ExtensibleSuite
    override fun case(name: String, test: () -> Unit): ExtensibleCase
}