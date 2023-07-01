package com.aold.advers.ble

/**
 * @author Kirilin Yury on 02.07.2023.
 */
data class TempHumidityResult(
    val temperature:Float,
    val humidity:Float,
    val connectionState: ConnectionState
)