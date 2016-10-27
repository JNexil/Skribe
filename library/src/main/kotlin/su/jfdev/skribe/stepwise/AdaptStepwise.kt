package su.jfdev.skribe.stepwise

internal class AdaptStepwise<out Subject>(private var last: Intermediate<Subject>): Stepwise<Subject> {
    override fun test(description: String, action: (Subject) -> Unit) = last.test(description, action)
    override fun <R> then(description: String, action: (Subject) -> R) = last.then(description, action)

    override fun <R> after(description: String, action: (Subject) -> R): Intermediate<R> {
        after(description) { action(it) }
        return then(description, action)
    }

    override fun after(description: String, action: (Subject) -> Unit) {
        last = last.then(description) {
            it.apply { action(it) }
        }
    }
}