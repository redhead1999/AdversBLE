package com.aold.advers.ble.domain.bleparsables

import android.bluetooth.BluetoothGattDescriptor
import com.aold.advers.ble.domain.models.BleProperties
import com.aold.advers.ble.domain.models.UUID_DEFAULT
import com.aold.advers.ble.utils.toHex


object CCCD : ParsableUuid("00002902$UUID_DEFAULT".lowercase()) {

    override fun commands(param: Any?): Array<String> {
        return if (param == BleProperties.PROPERTY_NOTIFY)
            arrayOf(
                "Enable Notifications: ${BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE.toHex()}",
                "Disable Notifications: ${BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE.toHex()}"
            ) else arrayOf(
            "Enable Indications: ${BluetoothGattDescriptor.ENABLE_INDICATION_VALUE.toHex()}",
            "Disable Indications: ${BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE.toHex()}"
        )
    }

    override fun getReadStringFromBytes(byteArray: ByteArray): String {
        return if (byteArray.contentEquals(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE) ||
            byteArray.contentEquals(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE)
        )
            "Enabled."
        else
            "Disabled."
    }
}

