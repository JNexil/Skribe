package com.github.jnexil.skribe.adapter

import com.github.jnexil.skribe.testable.*

interface SuiteAdapter: Testable<Unit> {
    fun beforeEach(action: () -> Unit)
    fun afterEach(action: () -> Unit)
    fun suite(name: String, action: SuiteAdapter.() -> Unit): SuiteAdapter
    fun suite(name: String): SuiteAdapter
    fun case(name: String, test: () -> Unit): CaseAdapter
    override fun test(description: String, action: (Unit) -> Unit): CaseAdapter = case(description) {}
}