package com.aold.advers.ble.domain.usecases

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import com.aold.advers.ble.domain.models.DeviceService
import com.aold.advers.ble.domain.models.updateBytes
import timber.log.Timber

class ParseRead() {

    operator fun invoke(
        deviceDetails: List<com.aold.advers.ble.domain.models.DeviceService>,
        characteristic: BluetoothGattCharacteristic,
        status: Int
    ): List<com.aold.advers.ble.domain.models.DeviceService> {

        if (status == BluetoothGatt.GATT_SUCCESS) {

            val newList = deviceDetails.map { svc ->
                svc.copy(characteristics =
                svc.characteristics.map { char ->
                    if (char.uuid == characteristic.uuid.toString()) {
                        char.updateBytes(characteristic.value)
                    } else
                        char
                })
            }

            Timber.d("newList: $newList")

            return newList
        } else {
            return deviceDetails
        }

    }
}
