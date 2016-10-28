package su.jfdev.skribe.expect

import com.winterbe.expekt.*

abstract class ExpectMe<T, ME: ExpectMe<T, ME>>(subject: T?, flavor: Flavor): ExpectAny<T>(subject, flavor) {
    override val a: ExpectAny<T> get() = me { super.a }
    override val an: ExpectAny<T> get() = me { super.an }
    override val and: ExpectAny<T> get() = me { super.and }
    override val at: ExpectAny<T> get() = me { super.at }
    override val be: ExpectAny<T> get() = me { super.be }
    override val been: ExpectAny<T> get() = me { super.been }
    override val has: ExpectAny<T> get() = me { super.has }
    override val have: ExpectAny<T> get() = me { super.have }
    override val `is`: ExpectAny<T> get() = me { super.`is` }
    override val not: ExpectAny<T> get() = me { super.not }
    override val `null`: ExpectAny<T> get() = me { super.`null` }
    override val of: ExpectAny<T> get() = me { super.of }
    override val same: ExpectAny<T> get() = me { super.same }
    override val that: ExpectAny<T> get() = me { super.that }
    override val the: ExpectAny<T> get() = me { super.the }
    override val to: ExpectAny<T> get() = me { super.to }
    override val which: ExpectAny<T> get() = me { super.which }
    override val with: ExpectAny<T> get() = me { super.with }

    override fun equal(expected: T?): ExpectAny<T> = me { super.equal(expected) }
    override fun identity(expected: T?): ExpectAny<T> = me { super.identity(expected) }
    override fun <S: T> instanceof(type: Class<S>): ExpectAny<T> = me { super.instanceof(type) }
    override fun satisfy(predicate: (T) -> Boolean): ExpectAny<T> = me { super.satisfy(predicate) }

    @Suppress("UNCHECKED_CAST")
    protected inline fun me(action: () -> Unit) = apply { action() } as ME
}