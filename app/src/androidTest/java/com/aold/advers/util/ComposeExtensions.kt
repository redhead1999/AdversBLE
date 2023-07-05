package com.aold.advers.util

import com.aold.advers.ble.utils.AsyncTimer
import de.mannodermaus.junit5.compose.ComposeContext

fun ComposeContext.waitUntilTimeout(
    timeoutMillis: Long
) {
    AsyncTimer.start(timeoutMillis)
    this.waitUntil(
        condition = { AsyncTimer.expired },
        timeoutMillis = timeoutMillis + 1000
    )
}