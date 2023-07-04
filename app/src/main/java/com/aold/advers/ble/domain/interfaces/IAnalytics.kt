package com.aold.advers.ble.domain.interfaces

import com.aold.advers.ble.utils.logging.CharacteristicEvent

interface IAnalytics {

    fun logCharacteristicEvent(analyticsEvent: CharacteristicEvent)
    //fun logScreenEvent(analyticsEvent: CharacteristicEvent)

}