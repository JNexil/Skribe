package com.github.jnexil.skribe.adapter

interface SuiteAdapter {
    fun beforeEach(action: () -> Unit)
    fun afterEach(action: () -> Unit)
    fun suite(name: String, action: SuiteAdapter.() -> Unit): SuiteAdapter
    fun suite(name: String): SuiteAdapter
    fun case(name: String, test: () -> Unit): CaseAdapter
}