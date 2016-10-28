package su.jfdev.skribe.expect

import com.winterbe.expekt.*
import java.time.*

class ExpectDuration(subject: Duration?, flavor: Flavor): AbstractExpect<Duration, ExpectDuration>(subject, flavor) {
    val nanos by property {
        get(Duration::toNanos)
    }

    val millis by property {
        get(Duration::toMillis)
    }

    val seconds by property {
        get(Duration::getSeconds)
    }

    val days by property {
        get(Duration::toDays)
    }

    val hours by property {
        get(Duration::toHours)
    }

    val minutes by property {
        get(Duration::toMinutes)
    }

    val negative by assert(Duration::isNegative)
    val positive by negate(Duration::isNegative)
    val zero by assert(Duration::isZero)
}