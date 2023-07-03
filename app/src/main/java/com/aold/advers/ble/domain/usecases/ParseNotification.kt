package com.aold.advers.ble.domain.usecases

import android.bluetooth.BluetoothGattCharacteristic
import com.aold.advers.ble.domain.models.DeviceService
import com.aold.advers.ble.domain.models.updateNotification
import timber.log.Timber

class ParseNotification() {

    operator fun invoke(
        deviceDetails: List<DeviceService>,
        characteristic: BluetoothGattCharacteristic,
        value: ByteArray
    ): List<DeviceService> {

        val newList = deviceDetails.map { svc ->
            svc.copy(characteristics =
            svc.characteristics.map { char ->
                if (char.uuid == characteristic.uuid.toString()) {
                    char.updateNotification(value)
                } else
                    char
            })
        }

        Timber.d("newList: $newList")

        return newList
    }

}