package su.jfdev.skribe.expect

import com.winterbe.expekt.*

abstract class ExpectMe<T, ME: ExpectMe<T, ME>>(subject: T?, flavor: Flavor): ExpectAny<T>(subject, flavor) {
    override val a: ME get() = me { super.a }
    override val an: ME get() = me { super.an }
    override val and: ME get() = me { super.and }
    override val at: ME get() = me { super.at }
    override val be: ME get() = me { super.be }
    override val been: ME get() = me { super.been }
    override val has: ME get() = me { super.has }
    override val have: ME get() = me { super.have }
    override val `is`: ME get() = me { super.`is` }
    override val not: ME get() = me { super.not }
    override val `null`: ME get() = me { super.`null` }
    override val of: ME get() = me { super.of }
    override val same: ME get() = me { super.same }
    override val that: ME get() = me { super.that }
    override val the: ME get() = me { super.the }
    override val to: ME get() = me { super.to }
    override val which: ME get() = me { super.which }
    override val with: ME get() = me { super.with }

    override fun equal(expected: T?): ME = me { super.equal(expected) }
    override fun identity(expected: T?): ME = me { super.identity(expected) }
    override fun <S: T> instanceof(type: Class<S>): ME = me { super.instanceof(type) }
    override fun satisfy(predicate: (T) -> Boolean): ME = me { super.satisfy(predicate) }

    @Suppress("UNCHECKED_CAST")
    protected inline fun me(action: () -> Unit) = apply { action() } as ME
}