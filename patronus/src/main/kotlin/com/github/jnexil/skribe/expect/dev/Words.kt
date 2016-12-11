package com.github.jnexil.skribe.expect.dev

import com.github.jnexil.skribe.expect.*
import com.github.jnexil.skribe.expect.dev.ExpectProperty.*


val <S: Any> Expect<S>.a: Expect<S> by Word
val <S: Any> Expect<S>.an: Expect<S> by Word
val <S: Any> Expect<S>.and: Expect<S> by Word
val <S: Any> Expect<S>.at: Expect<S> by Word
val <S: Any> Expect<S>.be: Expect<S> by Word
val <S: Any> Expect<S>.been: Expect<S> by Word
val <S: Any> Expect<S>.has: Expect<S> by Word
val <S: Any> Expect<S>.have: Expect<S> by Word
val <S: Any> Expect<S>.`is`: Expect<S> by Word
val <S: Any> Expect<S>.of: Expect<S> by Word
val <S: Any> Expect<S>.same: Expect<S> by Word
val <S: Any> Expect<S>.that: Expect<S> by Word
val <S: Any> Expect<S>.the: Expect<S> by Word
val <S: Any> Expect<S>.to: Expect<S> by Word
val <S: Any> Expect<S>.which: Expect<S> by Word
val <S: Any> Expect<S>.with: Expect<S> by Word

val <S: Any> Expect<S>.not: Expect<S> get() = backend().append("not").with(Negative)