package com.github.jnexil.skribe

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
        logger.debug { "Started: ${element.description}" }
        fireTestStarted(element.description)
        try {
            element.test()
            logger.debug { "Finished: ${element.description}" }
            fireTestFinished(element.description)
        } catch (e: Throwable) {
            logger.debug { "Failed: ${element.description}" }
            fireTestFailure(Failure(element.description, e))
        }
    }

    override fun getDescription(): Description {
        logger.debug {
            buildString {
                appendln("Runner request description:")
                log(0, description)
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

    companion object: KLogging()
}
