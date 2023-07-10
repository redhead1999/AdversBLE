package com.aold.advers.sharedtest

import com.aold.advers.ble.domain.models.BleConnectEvents
import com.aold.advers.ble.domain.models.BleReadWriteCommands
import com.aold.advers.ble.domain.models.ConnectionState
import com.aold.advers.ble.domain.models.DeviceEvents
import com.aold.advers.ble.domain.models.ScanState
import com.aold.advers.ble.domain.models.ScanUI
import com.aold.advers.ble.presentation.previewparams.FeatureParams
import com.aold.advers.ble.presentation.previewparams.devices
import com.aold.advers.ble.presentation.previewparams.portrait
import com.aold.advers.sharedtest.deviceDetail


val scanStatePortrait = FeatureParams(
    ScanState(
    ScanUI(
        devices,
        null,
        ConnectionState.CONNECTING,
        null,
        null),
    BleConnectEvents({}, {}),
    BleReadWriteCommands(
        {},
        { _: String, _: String -> },
        { _: String, _: String -> },
        { _: String, _: String, _: String -> },
    ),
    DeviceEvents({}, {}, {},{},{})
),
devices,
deviceDetail,
portrait
)