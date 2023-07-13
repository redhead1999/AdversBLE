package com.aold.advers.ble.domain.old

/**
 * @author Kirilin Yury on 10.07.2023.
 */
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import com.aold.advers.ble.handlers.BleGatt

/** A listener containing callback methods to be registered with [ConnectionManager].*/
class ConnectionEventListener {
    var onConnectionSetupComplete: ((BleGatt) -> Unit)? = null
    var onDisconnect: ((BluetoothDevice) -> Unit)? = null
    var onDescriptorRead: ((BluetoothDevice, BluetoothGattDescriptor) -> Unit)? = null
    var onDescriptorWrite: ((BluetoothDevice, BluetoothGattDescriptor) -> Unit)? = null
    var onCharacteristicChanged: ((BluetoothDevice, BluetoothGattCharacteristic) -> Unit)? = null
    var onCharacteristicRead: ((BluetoothDevice, BluetoothGattCharacteristic) -> Unit)? = null
    var onCharacteristicWrite: ((BluetoothDevice, BluetoothGattCharacteristic) -> Unit)? = null
    var onNotificationsEnabled: ((BluetoothDevice, BluetoothGattCharacteristic) -> Unit)? = null
    var onNotificationsDisabled: ((BluetoothDevice, BluetoothGattCharacteristic) -> Unit)? = null
    var onMtuChanged: ((BluetoothDevice, Int) -> Unit)? = null
}