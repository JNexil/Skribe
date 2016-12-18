package com.github.jnexil.skribe

import com.github.jnexil.skribe.adapter.Extension.*
import mu.*
import org.junit.runner.*
import org.junit.runner.notification.*

class SkribeRunner(testClass: Class<Skribe>): Runner() {
    private val skribe: SkribeSuite = testClass.newInstance().root
    override fun run(notifier: RunNotifier) {
        logger.info { "Testing elements: ${skribe.elements}" }
        notifier.testSuite(skribe)
    }

    private fun RunNotifier.testSuite(suite: SkribeSuite) {
        for (element in suite.elements) when (element) {
            is SkribeCase  -> testCase(element)
            is SkribeSuite -> testSuite(element)
        }
    }

    private fun RunNotifier.testCase(element: SkribeCase) {
        val description = element.description
        logger.debug { "Started: $description" }
        when (Ignored) {
            in element.suite.extensions -> {
                fireTestIgnored(description)
            }
            else                        -> {
                fireTestStarted(description)
                try {
                    element.doTest()
                    logger.debug { "Finished: $description" }
                } catch (e: Throwable) {
                    logger.debug { "Failed: $description" }
                    fireTestFailure(Failure(description, e))
                }
                fireTestFinished(description)
            }
        }
    }

    private fun SkribeCase.doTest() {
        for (extension in suite.extensions) {
            if (extension is ActionBeforeEach) {
                extension.action()
            }
        }
        test()
        for (extension in suite.extensions) {
            if (extension is ActionAfterEach) {
                extension.action()
            }
        }
    }

    override fun getDescription(): Description {
        logger.debug {
            buildString {
                appendln("Runner request description:")
                log(0, skribe.description)
            }
        }
        return skribe.description
    }

    private fun StringBuilder.log(depth: Int, description: Description) {
        append("desc ")
        repeat(depth) { append('-') }
        appendln(description)
        for (child in description.children) {
            log(depth + 1, child)
        }
    }

    private companion object: KLogging()
}
