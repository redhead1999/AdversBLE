package com.aold.advers.chat.domain.data

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.aold.advers.chat.domain.chat.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}