package com.aold.advers.data

/**
 * @author Kirilin Yury on 09.06.2023.
 */

data class TempHumidityResult(
    val temperature:Float,
    val humidity:Float,
    val connectionState: ConnectionState
)