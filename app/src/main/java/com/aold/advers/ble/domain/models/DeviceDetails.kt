package com.aold.advers.ble.domain.models

import com.aold.advers.ble.domain.models.DeviceCharacteristics
import com.aold.advers.ble.local.entities.ScannedDevice

data class DeviceDetail(
    val scannedDevice: ScannedDevice,
    val services: List<DeviceService>
)
data class DeviceService(
    val uuid: String,
    val name: String,
    val characteristics: List<DeviceCharacteristics>
)


