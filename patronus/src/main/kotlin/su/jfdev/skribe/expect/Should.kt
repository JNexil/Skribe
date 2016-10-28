package su.jfdev.skribe.expect

val <T: Any> T?.should: Expect<T> get() = Expect.Backend(lazy = lazyOf(this), line = "$this should")
fun <T: Any> expect(it: T?): Expect<T> = Expect.Backend(lazy = lazyOf(it), line = "expect $it")
fun <T: Any> assert(it: T?): Expect<T> = Expect.Backend(lazy = lazyOf(it), line = "assert $it")

fun <T: Any> expect(factory: () -> T?): Expect<T> = Expect.Backend(lazy = lazy(factory), line = "expect $factory")
fun <T: Any> assert(factory: () -> T?): Expect<T> = Expect.Backend(lazy = lazy(factory), line = "assert $factory")