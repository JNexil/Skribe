package com.github.jnexil.skribe.util

import com.github.jnexil.skribe.testable.*

@Undescribed
fun <S> Stepwise<S>.`checking not`(action: String, block: (S) -> Boolean) = step(action) {
    it.negate(action, block)
}

@Undescribed
fun <S> Stepwise<S>.checking(action: String, block: (S) -> Boolean) = step(action) {
    it.assert(action, block)
}

@Undescribed
fun <S> Stepwise<S>.`checkingR not`(action: String, block: S.() -> Boolean) = checking(action, block)

@Undescribed
fun <S> Stepwise<S>.checkingR(action: String, block: S.() -> Boolean) = checking(action, block)