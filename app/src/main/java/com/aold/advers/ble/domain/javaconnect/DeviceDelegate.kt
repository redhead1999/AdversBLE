package com.aold.advers.ble.domain.javaconnect

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothGattService

/**
 * @author Kirilin Yury on 12.07.2023.
 */
class DeviceDelegate {
    fun connectCharacteristics(s: BluetoothGattService?): Boolean {
        return true
    }

    // following methods only overwritten for Telit devices
    fun onDescriptorWrite(g: BluetoothGatt?, d: BluetoothGattDescriptor?, status: Int) { /*nop*/
    }

    fun onCharacteristicChanged(g: BluetoothGatt?, c: BluetoothGattCharacteristic?) { /*nop*/
    }

    fun onCharacteristicWrite(
        g: BluetoothGatt?,
        c: BluetoothGattCharacteristic?,
        status: Int,
    ) { /*nop*/
    }

    fun canWrite(): Boolean {
        return true
    }

    fun disconnect() { /*nop*/
    }
}