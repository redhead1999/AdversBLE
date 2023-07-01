package com.aold.advers.ble

/**
 * @author Kirilin Yury on 01.07.2023.
 */
import android.bluetooth.BluetoothDevice
import android.content.Context
import java.util.UUID

/** Абстрактный запечатанный класс, представляющий тип операции BLE */
sealed class BleOperationType {
    abstract val device: BluetoothDevice
}

/** Подключение к [device] и выполнить обнаружение службы */
data class Connect(override val device: BluetoothDevice, val context: Context) : BleOperationType()

/** Отключение  от [device] и освобождение всех ресурсов подключения */
data class Disconnect(override val device: BluetoothDevice) : BleOperationType()

/** Запись [payload] как значение характеристики, представленной [characteristic Uuid] */
data class CharacteristicWrite(
    override val device: BluetoothDevice,
    val characteristicUuid: UUID,
    val writeType: Int,
    val payload: ByteArray
) : BleOperationType() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharacteristicWrite

        if (device != other.device) return false
        if (characteristicUuid != other.characteristicUuid) return false
        if (writeType != other.writeType) return false
        if (!payload.contentEquals(other.payload)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = device.hashCode()
        result = 31 * result + characteristicUuid.hashCode()
        result = 31 * result + writeType
        result = 31 * result + payload.contentHashCode()
        return result
    }
}

/** Считывает значение характеристики, представленной [characteristicUuid] */
data class CharacteristicRead(
    override val device: BluetoothDevice,
    val characteristicUuid: UUID
) : BleOperationType()

/** Запись [payload] как значение дескриптора, представленного [descriptorUuid] */
data class DescriptorWrite(
    override val device: BluetoothDevice,
    val descriptorUuid: UUID,
    val payload: ByteArray
) : BleOperationType() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DescriptorWrite

        if (device != other.device) return false
        if (descriptorUuid != other.descriptorUuid) return false
        if (!payload.contentEquals(other.payload)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = device.hashCode()
        result = 31 * result + descriptorUuid.hashCode()
        result = 31 * result + payload.contentHashCode()
        return result
    }
}

/** Считайте значение дескриптора, представленного [descriptorUuid] */
data class DescriptorRead(
    override val device: BluetoothDevice,
    val descriptorUuid: UUID
) : BleOperationType()

/** Включить уведомления/указания по характеристике, представленной [characteristicUuid] */
data class EnableNotifications(
    override val device: BluetoothDevice,
    val characteristicUuid: UUID
) : BleOperationType()

/** Отключить уведомления/индикации по характеристике, представленной [characteristicUuid] */
data class DisableNotifications(
    override val device: BluetoothDevice,
    val characteristicUuid: UUID
) : BleOperationType()

/** Запрос на MTU в размере [mtu] */
data class MtuRequest(
    override val device: BluetoothDevice,
    val mtu: Int
) : BleOperationType()
