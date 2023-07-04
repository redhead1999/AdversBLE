package com.aold.advers.ble.domain.usecases

import android.annotation.SuppressLint
import android.bluetooth.BluetoothGatt
import com.aold.advers.ble.domain.interfaces.IBleRepository
import com.aold.advers.ble.local.entities.Service
import com.aold.advers.ble.domain.models.BlePermissions
import com.aold.advers.ble.domain.models.BleProperties
import com.aold.advers.ble.domain.models.BleWriteTypes
import com.aold.advers.ble.domain.models.DeviceCharacteristics
import com.aold.advers.ble.domain.models.DeviceDescriptor
import com.aold.advers.ble.domain.models.DeviceService
import com.aold.advers.ble.domain.models.canRead
import com.aold.advers.ble.domain.models.canWriteProperties
import com.aold.advers.ble.utils.toGss
import timber.log.Timber

class ParseService
    (
    private val bleRepository: IBleRepository
) {

    @SuppressLint("MissingPermission")
    suspend operator fun invoke(gatt: BluetoothGatt, status: Int):
            List<DeviceService> {

        val services = mutableListOf<DeviceService>()

        if (status == BluetoothGatt.GATT_SUCCESS) {
            gatt.services?.forEach { gattService ->

                val serviceName =
                    bleRepository.getServiceById(gattService.uuid.toGss())?.name ?: "Mfr Service"

                val service = Service(
                    "",
                    serviceName,
                    "",
                    gattService.uuid.toString().uppercase()
                )

                val characteristics = mutableListOf<DeviceCharacteristics>()

                gattService.characteristics.forEach { char ->
                    val deviceCharacteristic = bleRepository
                        .getCharacteristicById(char.uuid.toGss())

                    val permissions = char.permissions
                    val properties = BleProperties.getAllProperties(char.properties)
                    val writeTypes = BleWriteTypes.getAllTypes(char.writeType)

                    val descriptors = mutableListOf<DeviceDescriptor>()
                    char.descriptors?.forEach { desc ->

                        Timber.d(char.uuid.toString())
                        Timber.d(desc.uuid.toString() + "; " + desc.characteristic.uuid.toString())

                        val deviceDescriptor = bleRepository.getDescriptorById(
                            desc.uuid.toGss()
                        )

                        descriptors.add(
                            DeviceDescriptor(
                                uuid = desc.uuid.toString(),
                                name = deviceDescriptor?.name ?: "Unknown",
                                charUuid = desc.characteristic.uuid.toString(),
                                permissions = BlePermissions.getAllPermissions(desc.permissions),
                                notificationProperty = if (properties.contains(BleProperties.PROPERTY_NOTIFY))
                                    BleProperties.PROPERTY_NOTIFY else if (properties.contains(
                                        BleProperties.PROPERTY_INDICATE
                                    )
                                )
                                    BleProperties.PROPERTY_INDICATE else null,
                                readBytes = null
                            )
                        )
                    }

                    characteristics.add(
                        DeviceCharacteristics(
                            uuid = char.uuid.toString(),
                            name = deviceCharacteristic?.name ?: "Mfr Characteristic",
                            descriptor = null,
                            permissions = permissions,
                            properties = properties,
                            writeTypes = writeTypes,
                            descriptors = descriptors,
                            canRead = properties.canRead(),
                            canWrite = properties.canWriteProperties(),
                            readBytes = null,
                            notificationBytes = null
                        )
                    )
                }

                val deviceService = DeviceService(
                    service.uuid,
                    service.name,
                    characteristics
                )

                services.add(deviceService)
                Timber.d(services.toString())

            }
        }

        return services.toList()

    }

}
