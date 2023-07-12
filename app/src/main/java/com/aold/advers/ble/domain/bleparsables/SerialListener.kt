package com.aold.advers.ble.domain.bleparsables

import java.util.ArrayDeque

/**
 * @author Kirilin Yury on 11.07.2023.
 */
internal interface SerialListener {
    fun onSerialConnect()
    fun onSerialConnectError(e: Exception?)
    fun onSerialRead(data: ByteArray?) // socket -> service
    fun onSerialRead(datas: ArrayDeque<ByteArray?>?) // service -> UI thread
    fun onSerialIoError(e: Exception?)
}