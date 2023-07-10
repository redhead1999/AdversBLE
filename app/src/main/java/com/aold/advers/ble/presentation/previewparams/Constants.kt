package com.aold.advers.ble.presentation.previewparams

import com.aold.advers.ble.domain.bleparsables.CCCD
import com.aold.advers.ble.domain.models.BleProperties
import com.aold.advers.ble.domain.models.BleWriteTypes
import com.aold.advers.ble.domain.models.DeviceCharacteristics
import com.aold.advers.ble.domain.models.DeviceDescriptor
import com.aold.advers.ble.domain.models.DeviceDetail
import com.aold.advers.ble.domain.models.DeviceService
import com.aold.advers.ble.local.entities.ScannedDevice


val devices = listOf(
    ScannedDevice(
        deviceId = 41,
        deviceName = "EASYWAY-BLE",
        address = "93:58:00:27:XX:00",
        rssi = -93,
        manufacturer = "Ericsson Technology Licensing",
        services = listOf("Heart Rate"),
        extra = null,
        lastSeen = 1675293173796,
        customName = null,
        baseRssi = -55,
        favorite = false,
        forget = false
    ),
    ScannedDevice(
        deviceId = 0,
        deviceName = "Autoterm",
        address = "31:F1:1E:1D:10:86",
        rssi = -77,
        manufacturer = null,
        services = listOf("[Human Interface Device"),
        extra = null,
        lastSeen = 1674510398719,
        customName = null,
        baseRssi = -88,
        favorite = false,
        forget = false
    )
)

val deviceDetail = DeviceDetail(
    scannedDevice =
    ScannedDevice(
        deviceId = 41,
        deviceName = "Autoterm",
        address = "31:F1:1E:1D:10:86",
        rssi = -93,
        manufacturer = "Ericsson Technology Licensing",
        services = listOf("Heart Rate"),
        extra = null,
        lastSeen = 1675293173796,
        customName = null,
        baseRssi = -55,
        favorite = false,
        forget = false
    ),
    services = listOf(
        DeviceService(
            uuid = "1800",
            name = "Generic Access",
            characteristics = listOf(
                DeviceCharacteristics(
                    uuid = "00002a00-0000-1000-8000-00805f9b34fb",
                    name = "Device Name",
                    descriptor = null,
                    permissions = 0,
                    properties = listOf(BleProperties.PROPERTY_READ),
                    writeTypes = listOf(BleWriteTypes.WRITE_TYPE_DEFAULT),
                    descriptors = listOf(
                        DeviceDescriptor(
                            CCCD.uuid,
                            "Client Characteristic Configuration", "",
                            emptyList(), BleProperties.PROPERTY_INDICATE, null
                        )
                    ),
                    canRead = true,
                    canWrite = false,
                    readBytes = null,
                    notificationBytes = null
                ),
                DeviceCharacteristics(
                    uuid = "00002a00-0000-1000-8000-00805f9b34fb",
                    name = "Appearance",
                    descriptor = null,
                    permissions = 0,
                    properties = listOf(BleProperties.PROPERTY_READ),
                    writeTypes = listOf(BleWriteTypes.WRITE_TYPE_DEFAULT),
                    descriptors = emptyList(),
                    canRead = true,
                    canWrite = false,
                    readBytes = null,
                    notificationBytes = null
                )
            )
        ),
        DeviceService(
            uuid = "1801",
            name = "Generic Attribute",
            characteristics = listOf(
                DeviceCharacteristics(
                    uuid = "00002a05-0000-1000-8000-00805f9b34fb",
                    name = "Service Changed",
                    descriptor = null,
                    permissions = 0,
                    properties = listOf(BleProperties.PROPERTY_READ),
                    writeTypes = listOf(BleWriteTypes.WRITE_TYPE_DEFAULT),
                    descriptors = emptyList(),
                    canRead = true,
                    canWrite = false,
                    readBytes = byteArrayOf(-60, 3),
                    notificationBytes = null
                )
            )
        ),
        DeviceService(
            uuid = "0000fff3-0000-1000-8000-00805f9b34fb",
            name = "Mfr Service",
            characteristics = listOf(
                DeviceCharacteristics(
                    uuid = "0000fff3-0000-1000-8000-00805f9b34fb",
                    name = "Mfr Characteristic",
                    descriptor = null,
                    permissions = 0,
                    properties = listOf(
                        BleProperties.PROPERTY_READ,
                        BleProperties.PROPERTY_WRITE
                    ),
                    writeTypes = listOf(BleWriteTypes.WRITE_TYPE_DEFAULT),
                    descriptors = emptyList(),
                    canRead = false,
                    canWrite = true,
                    readBytes = null,
                    notificationBytes = null
                ),
                DeviceCharacteristics(
                    uuid = "0000ae02-0000-1000-8000-00805f9b34fb",
                    name = "Mfr Characteristic",
                    descriptor = null,
                    permissions = 0,
                    properties = listOf(BleProperties.PROPERTY_READ),
                    writeTypes = listOf(BleWriteTypes.WRITE_TYPE_DEFAULT),
                    descriptors = emptyList(),
                    canRead = true,
                    canWrite = false,
                    readBytes = null,
                    notificationBytes = null
                ),
                DeviceCharacteristics(
                    uuid = "0000ae03-0000-1000-8000-00805f9b34fb",
                    name = "Mfr Characteristic",
                    descriptor = null,
                    permissions = 0,
                    properties = listOf(
                        BleProperties.PROPERTY_READ,
                        BleProperties.PROPERTY_WRITE
                    ),
                    writeTypes = listOf(BleWriteTypes.WRITE_TYPE_DEFAULT),
                    descriptors = emptyList(),
                    canRead = false,
                    canWrite = false,
                    readBytes = null,
                    notificationBytes = null
                )
            )
        )
    )
)
