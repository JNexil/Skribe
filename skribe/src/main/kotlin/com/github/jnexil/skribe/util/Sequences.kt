package com.github.jnexil.skribe.util

import java.util.*

fun <T> Sequence<T>.buffered(list: MutableCollection<T> = ArrayList()): Sequence<T> = (list + this).asSequence()