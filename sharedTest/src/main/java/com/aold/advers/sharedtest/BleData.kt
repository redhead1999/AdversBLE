package com.aold.advers.sharedtest

import com.aold.advers.ble.local.entities.Company
import com.aold.advers.ble.local.entities.MicrosoftDevice
import com.aold.advers.ble.local.entities.Service

val companies = listOf(
    Company(0, "Адверс"),
    Company(6, "Microsoft"),
)

val services = listOf(
    Service(
        name = "Human Interface Device",
        identifier = "org.bluetooth.service.human_interface_device",
        uuid = "1812", source = "gss"
    ),

    //todo добавить данные юарта

    Service(
        name = "Autoterm",
        identifier = "com.nordicsemi.service.uart",
        uuid = "D973F2E1-B19E-11E2-9E96-0800200C9A66",
        source = "nordic"
    ),
)

val msDevices = listOf(
    MicrosoftDevice(9, "Windows 10 Desktop")
)