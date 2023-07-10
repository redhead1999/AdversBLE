package com.aold.advers.ble.presentation.timers

import com.aold.advers.ble.presentation.timers.AlarmItem

interface AlarmScheduler {
    fun schedule(item: AlarmItem)
    fun cancel(item: AlarmItem)
}
