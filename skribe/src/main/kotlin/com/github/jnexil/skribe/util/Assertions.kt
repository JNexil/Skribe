package com.github.jnexil.skribe.util

import kotlin.test.*

@Undescribed
fun <S> S.assert(message: String, block: (S) -> Boolean) = assertTrue(message) {
    block(this)
}

@Undescribed
fun <S> S.negate(message: String, block: (S) -> Boolean) = assertFalse(message) {
    block(this)
}