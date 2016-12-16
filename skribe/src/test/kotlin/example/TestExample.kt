@file:Suppress("unused")

package example

import com.github.jnexil.skribe.*
import com.github.jnexil.skribe.util.*
import org.junit.runner.*
import java.util.concurrent.atomic.*

@RunWith(SkribeRunner::class)
class TestExample: Skribe("Test") {
    init {
        describe("it's unit test")
                .intermediate
                .move("create atomic int") { AtomicInteger(0) }
                .stepwise {
                    step("add 5") { it.getAndAdd(5) }
                    should("have 5") { it.get() == 5 }
                }
    }
}

