package com.aold.advers.ble

import com.aold.advers.ble.utils.Resource
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * @author Kirilin Yury on 02.07.2023.
 */
interface TemperatureAndHumidityReceiveManager {

    val data: MutableSharedFlow<Resource<TempHumidityResult>>

    fun reconnect()

    fun disconnect()

    fun startReceiving()

    fun closeConnection()

}