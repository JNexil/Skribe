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
        fireTestStarted(element.description)
        try {
            element.test()
            fireTestFinished(element.description)
        } catch (e: Throwable) {
            fireTestFailure(Failure(element.description, e))
        }
    }

    override fun getDescription(): Description {
        logger.info { "Runner request description: ${skribe.description}" }
        return skribe.description
    }

    companion object: KLogging()
}
