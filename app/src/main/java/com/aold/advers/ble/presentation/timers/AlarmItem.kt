package com.aold.advers.ble.presentation.timers

import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val message: String
)