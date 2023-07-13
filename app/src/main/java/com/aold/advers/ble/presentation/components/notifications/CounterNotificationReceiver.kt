package com.aold.advers.ble.presentation.components.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
class CounterNotificationReceiver: BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent?) {
        val service = CounterNotificationService(context)
        service.showNotification(++Counter.value)
    }
}