package com.aold.advers.ble.handlers

import android.annotation.SuppressLint
import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothProfile
import android.os.Build
import com.aold.advers.ble.domain.bleparsables.CCCD
import com.aold.advers.ble.domain.bleparsables.SerialListener
import com.aold.advers.ble.domain.models.ConnectionState
import com.aold.advers.ble.domain.models.DeviceService
import com.aold.advers.ble.domain.usecases.ParseDescriptor
import com.aold.advers.ble.domain.usecases.ParseNotification
import com.aold.advers.ble.domain.usecases.ParseRead
import com.aold.advers.ble.domain.usecases.ParseService
import com.aold.advers.ble.utils.print
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import timber.log.Timber
import java.util.UUID


@SuppressLint("MissingPermission")
class BleGatt(
    private val app: Application,
    private val scope: CoroutineScope,
    private val parseService: ParseService,
    private val parseRead: ParseRead,
    private val parseNotification: ParseNotification,
    private val parseDescriptor: ParseDescriptor,
) : KoinComponent {

    private val BLUETOOTH_LE_UUID_CCCD = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
    private val BLUETOOTH_LE_UUID_CC254X_SERVICE =
        UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb")
    private val BLUETOOTH_LE_UUID_CC254X_CHAR_RW =
        UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb")

    private val BLUETOOTH_LE_UUID_NRF_SERVICE =
        UUID.fromString("d973f2e0-b19e-11e2-9e96-0800200c9a66") //"6e400001-b5a3-f393-e0a9-e50e24dcca9e");

    private val BLUETOOTH_LE_UUID_NRF_CHAR_RW2 =
        UUID.fromString("d973f2e1-b19e-11e2-9e96-0800200c9a66") //"6e400002-b5a3-f393-e0a9-e50e24dcca9e"); // read on microbit, write on adafruit

    private val BLUETOOTH_LE_UUID_NRF_CHAR_RW3 =
        UUID.fromString("d973f2e2-b19e-11e2-9e96-0800200c9a66") //"6e400003-b5a3-f393-e0a9-e50e24dcca9e");


    private val BLUETOOTH_LE_UUID_RN4870_SERVICE =
        UUID.fromString("49535343-FE7D-4AE5-8FA9-9FAFD205E455")
    private val BLUETOOTH_LE_UUID_RN4870_CHAR_RW =
        UUID.fromString("49535343-1E4D-4BD9-BA61-23C647249616")

    // https://play.google.com/store/apps/details?id=com.telit.tiosample
    // https://www.telit.com/wp-content/uploads/2017/09/TIO_Implementation_Guide_r6.pdf
    private val BLUETOOTH_LE_UUID_TIO_SERVICE =
        UUID.fromString("0000FEFB-0000-1000-8000-00805F9B34FB")
    private val BLUETOOTH_LE_UUID_TIO_CHAR_TX =
        UUID.fromString("00000001-0000-1000-8000-008025000000") // WNR

    private val BLUETOOTH_LE_UUID_TIO_CHAR_RX =
        UUID.fromString("00000002-0000-1000-8000-008025000000") // N

    private val BLUETOOTH_LE_UUID_TIO_CHAR_TX_CREDITS =
        UUID.fromString("00000003-0000-1000-8000-008025000000") // W

    private val BLUETOOTH_LE_UUID_TIO_CHAR_RX_CREDITS =
        UUID.fromString("00000004-0000-1000-8000-008025000000") // I


    private val MAX_MTU =
        512 // BLE standard does not limit, some BLE 4.2 devices support 251, various source say that Android has max 512

    private val DEFAULT_MTU = 23

    private val listener: SerialListener? = null
    private var writePending = false
    private var canceled = false
    private val connected = false
    private val payloadSize = DEFAULT_MTU - 3
    private var btGatt: BluetoothGatt? = null
    private val btAdapter: BluetoothAdapter = get()

    val connectMessage = MutableStateFlow(ConnectionState.DISCONNECTED)
    val deviceDetails = MutableStateFlow<List<DeviceService>>(emptyList())

    private val bluetoothGattCallback = object : BluetoothGattCallback() {

        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)

            btGatt = gatt
            Timber.d("status: $status")

            when (newState) {
                BluetoothProfile.STATE_CONNECTING -> connectMessage.value =
                    ConnectionState.CONNECTING

                BluetoothProfile.STATE_CONNECTED -> {
                    connectMessage.value = ConnectionState.CONNECTED
                    btGatt?.discoverServices()
                }

                BluetoothProfile.STATE_DISCONNECTING -> connectMessage.value =
                    ConnectionState.DISCONNECTING

                BluetoothProfile.STATE_DISCONNECTED -> connectMessage.value =
                    ConnectionState.DISCONNECTED

                else -> connectMessage.value = ConnectionState.DISCONNECTED
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)
            scope.launch {
                deviceDetails.value = emptyList()
                gatt?.let {
                    deviceDetails.value = parseService(it, status)
                    enableNotificationsAndIndications()
                }
            }
        }

        override fun onCharacteristicRead(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            value: ByteArray,
            status: Int,
        ) {
            super.onCharacteristicRead(gatt, characteristic, value, status)
            deviceDetails.value = parseRead(deviceDetails.value, characteristic, status)
        }

        @Deprecated("Deprecated in Java")
        override fun onCharacteristicRead(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            status: Int,
        ) {
            super.onCharacteristicRead(gatt, characteristic, status)
            deviceDetails.value = parseRead(deviceDetails.value, characteristic, status)
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            value: ByteArray,
        ) {
            super.onCharacteristicChanged(gatt, characteristic, value)
            onSerialRead(value)
            Timber.d("characteristic changed: ${value.print()}")
            deviceDetails.value = parseNotification(deviceDetails.value, characteristic, value)
        }

        @SuppressLint("BinaryOperationInTimber")
        @Deprecated("Deprecated in Java")
        override fun onDescriptorRead(
            gatt: BluetoothGatt?,
            descriptor: BluetoothGattDescriptor,
            status: Int,
        ) {
            super.onDescriptorRead(gatt, descriptor, status)

            Timber.d(
                "descriptor read: ${descriptor.uuid}, " +
                        "${descriptor.characteristic.uuid}, $status, ${descriptor.value.print()}"
            )

            deviceDetails.value =
                parseDescriptor(deviceDetails.value, descriptor, status, descriptor.value)
        }

        @SuppressLint("BinaryOperationInTimber")
        override fun onDescriptorRead(
            gatt: BluetoothGatt,
            descriptor: BluetoothGattDescriptor,
            status: Int,
            value: ByteArray,
        ) {
            super.onDescriptorRead(gatt, descriptor, status, value)

            Timber.d(
                "descriptor read: ${descriptor.uuid}, " +
                        "${descriptor.characteristic.uuid}, $status, ${value.print()}"
            )

            deviceDetails.value = parseDescriptor(deviceDetails.value, descriptor, status, value)
        }

        override fun onCharacteristicWrite(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic,
            status: Int,
        ) {
            super.onCharacteristicWrite(gatt, characteristic, status)
            btGatt?.readCharacteristic(characteristic)
        }

        suspend fun enableNotificationsAndIndications() {

            btGatt?.services?.forEach { gattSvcForNotify ->
                gattSvcForNotify.characteristics?.forEach { svcChar ->

                    svcChar.descriptors.find { desc ->
                        desc.uuid.toString() == CCCD.uuid
                    }?.also { cccd ->
                        val notifyRegistered = btGatt?.setCharacteristicNotification(svcChar, true)

                        if (svcChar.properties and BluetoothGattCharacteristic.PROPERTY_NOTIFY > 0) {
                            cccd.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE)
                            btGatt?.writeDescriptor(cccd)
                        }

                        if (svcChar.properties and BluetoothGattCharacteristic.PROPERTY_INDICATE > 0) {
                            cccd.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE)
                            btGatt?.writeDescriptor(cccd)
                        }

                        // give gatt a little breathing room for writes
                        delay(300L)

                    }

                }
            }
        }
    }

    fun connect(address: String) {
        if (btAdapter.isEnabled) {
            btAdapter.let { adapter ->
                try {
                    connectMessage.value = ConnectionState.CONNECTING
                    val device = adapter.getRemoteDevice(address)
                    device.connectGatt(app, false, bluetoothGattCallback)
                    onSerialConnect()
                } catch (e: Exception) {
                    connectMessage.value = ConnectionState.DISCONNECTED
                    Timber.tag("BTGATT_CONNECT").e(e)
                }
            }
        }
    }

    fun readCharacteristic(uuid: String) {
        btGatt?.services?.flatMap { it.characteristics }?.find { svcChar ->
            svcChar.uuid.toString() == uuid
        }?.also { foundChar ->
            Timber.d("Found Char: " + foundChar.uuid.toString())
            btGatt?.readCharacteristic(foundChar)
        }
    }

    fun readDescriptor(charUuid: String, descUuid: String) {

        val currentCharacteristic =
            btGatt?.services?.flatMap { it.characteristics }?.find { char ->
                char.uuid.toString() == charUuid
            }
        currentCharacteristic?.let { char ->
            char.descriptors.find { desc ->
                desc.uuid.toString() == descUuid
            }?.also { foundDesc ->
                Timber.d("Found Char: $charUuid; " + foundDesc.uuid.toString())
                btGatt?.readDescriptor(foundDesc)
            }
        }

    }

    fun writeBytes(uuid: String, bytes: ByteArray) {
        btGatt?.services?.flatMap { it.characteristics }?.find { svcChar ->
            svcChar.uuid.toString() == uuid
        }?.also { foundChar ->
            Timber.d("Found Char: " + foundChar.uuid.toString())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                btGatt?.writeCharacteristic(
                    foundChar,
                    bytes,
                    BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
                )
            } else {
                foundChar.setValue(bytes)
                btGatt?.writeCharacteristic(foundChar)
            }
        }

    }

    fun writeDescriptor(charUuid: String, uuid: String, bytes: ByteArray) {
        btGatt?.services?.flatMap { it.characteristics }?.flatMap { it.descriptors }
            ?.find { it.characteristic.uuid.toString() == charUuid && it.uuid.toString() == uuid }
            ?.also { foundDescriptor ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    btGatt?.writeDescriptor(foundDescriptor, bytes)
                } else {
                    foundDescriptor.setValue(bytes)
                    btGatt?.writeDescriptor(foundDescriptor)
                }
            }
    }

    fun close() {
        connectMessage.value = ConnectionState.DISCONNECTED
        deviceDetails.value = emptyList()
        try {
            btGatt?.let { gatt ->
                gatt.disconnect()
                gatt.close()
                btGatt = null
            }
        } catch (e: Exception) {
            Timber.tag("BTGATT_CLOSE").e(e)
        } finally {
            btGatt = null
        }
    }

    /**
     * SerialListener
     */
    private fun onSerialConnect() {
        listener?.onSerialConnect()
    }

    private fun onSerialRead(data: ByteArray) {
        listener?.onSerialRead(data)
    }

    private fun onSerialConnectError(e: java.lang.Exception) {
        canceled = true
        listener?.onSerialConnectError(e)
    }
}