package com.aold.advers.ble.utils.logging

import android.os.Bundle
import com.aold.advers.ble.domain.interfaces.IAnalytics
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.component.KoinComponent


enum class AnalyticsEventType {
    READ_CHARACTERISTIC,
    WRITE_CHARACTERISTIC,
    CONTROL_ADVERSBLEDOM
}

enum class AnalyticsContentType {
    BLE_CHARACTERISTIC,
    CONTROL_EVENT
}

data class CharacteristicEvent(
    val eventName: String,
    val uuid: String
)

data class ScreenEvent(
    val eventName: String,
    val uuid: String
)

class Analytics(private val firebaseAnalytics: FirebaseAnalytics) :
    KoinComponent, IAnalytics {

    override fun logCharacteristicEvent(analyticsEvent: CharacteristicEvent) {

        val params = Bundle()
        params.putString("uuid", analyticsEvent.uuid)

        firebaseAnalytics.logEvent(
            analyticsEvent.eventName,
            params)

    }
}